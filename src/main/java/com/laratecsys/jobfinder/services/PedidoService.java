package com.laratecsys.jobfinder.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.laratecsys.jobfinder.domain.Cliente;
import com.laratecsys.jobfinder.domain.ItemPedido;
import com.laratecsys.jobfinder.domain.PagamentoComBoleto;
import com.laratecsys.jobfinder.domain.Pedido;
import com.laratecsys.jobfinder.domain.Produto;
import com.laratecsys.jobfinder.domain.enums.EstadoPagamento;
import com.laratecsys.jobfinder.domain.enums.Perfil;
import com.laratecsys.jobfinder.repositories.ClienteRepositories;
import com.laratecsys.jobfinder.repositories.ItemPedidoRepositories;
import com.laratecsys.jobfinder.repositories.PagamentoRepositories;
import com.laratecsys.jobfinder.repositories.PedidoRepositories;
import com.laratecsys.jobfinder.security.UserSS;
import com.laratecsys.jobfinder.services.exceptions.AuthorizationException;
import com.laratecsys.jobfinder.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepositories repo;

	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private PagamentoRepositories pagamentoRepositorie;
	
	@Autowired
	private ItemPedidoRepositories itemPedidoRepositories;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired(required = true)
	private EmailService emailService;
	
	@Autowired
	private ClienteRepositories clienteRepositories;
	
	public Pedido find(Integer id){
		
		Optional<Pedido> obj = repo.findById(id);
		Integer idUser = obj.get().getCliente().getId();
		
		UserSS userLoged = UserService.authenticated();
		
		if ((userLoged==null || !userLoged.hasHole(Perfil.ADMIN)) && !userLoged.getId().equals(idUser)) {
			throw new AuthorizationException("Usuário não autorizado");
		}
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrador. ID:" + id +", Tipo:" + Pedido.class.getName()) );
	}
	
	public Pedido insert(Pedido obj){
		obj.setId(null);
		obj.setInstantDate(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		obj.setEnderecoEntrega(obj.getEnderecoEntrega());
		
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			
			PagamentoComBoleto pgto = (PagamentoComBoleto) obj.getPagamento();
			
			boletoService.preencherPagamentoComBoleto(pgto, obj.getInstantDate());
			
		}
		
		obj = repo.save(obj);
		pagamentoRepositorie.save(obj.getPagamento());


		for (ItemPedido ip : obj.getItens()) {

			ip.setDesconto(0.0);

			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setProduto(produtoService.find(ip.getProduto().getId())); 
			ip.setPedido(obj);

		}

		itemPedidoRepositories.saveAll(obj.getItens());
		//emailService.sendOrderConfirmationEmail(obj);
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	
		
	}
	
	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		UserSS userLoged = UserService.authenticated();
		if (userLoged==null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Cliente cliente = clienteService.find(userLoged.getId());
		
		return repo.findByCliente(cliente, pageRequest);
	}

}
