package com.laratecsys.jobfinder.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laratecsys.jobfinder.domain.ItemPedido;
import com.laratecsys.jobfinder.domain.PagamentoComBoleto;
import com.laratecsys.jobfinder.domain.Pedido;
import com.laratecsys.jobfinder.domain.enums.EstadoPagamento;
import com.laratecsys.jobfinder.repositories.ItemPedidoRepositories;
import com.laratecsys.jobfinder.repositories.PagamentoRepositories;
import com.laratecsys.jobfinder.repositories.PedidoRepositories;
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
	
	public Pedido find(Integer id){
		
		Optional<Pedido> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrador. ID:" + id +", Tipo:" + Pedido.class.getName()) );
	}
	
	public Pedido insert(Pedido obj) {
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
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	
		
	}
	
}
