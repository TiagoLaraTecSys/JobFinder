package com.laratecsys.jobfinder.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.laratecsys.jobfinder.domain.Categoria;
import com.laratecsys.jobfinder.domain.Cidade;
import com.laratecsys.jobfinder.domain.Cliente;
import com.laratecsys.jobfinder.domain.Endereco;
import com.laratecsys.jobfinder.domain.Estado;
import com.laratecsys.jobfinder.domain.ItemPedido;
import com.laratecsys.jobfinder.domain.Pagamento;
import com.laratecsys.jobfinder.domain.PagamentoComBoleto;
import com.laratecsys.jobfinder.domain.PagamentoComCartao;
import com.laratecsys.jobfinder.domain.Pedido;
import com.laratecsys.jobfinder.domain.Produto;
import com.laratecsys.jobfinder.domain.enums.EstadoPagamento;
import com.laratecsys.jobfinder.domain.enums.Perfil;
import com.laratecsys.jobfinder.domain.enums.TipoCliente;
import com.laratecsys.jobfinder.repositories.CategoriaRepositories;
import com.laratecsys.jobfinder.repositories.CidadeRepositories;
import com.laratecsys.jobfinder.repositories.ClienteRepositories;
import com.laratecsys.jobfinder.repositories.EnderecoRepositories;
import com.laratecsys.jobfinder.repositories.EstadoRepositories;
import com.laratecsys.jobfinder.repositories.ItemPedidoRepositories;
import com.laratecsys.jobfinder.repositories.PagamentoRepositories;
import com.laratecsys.jobfinder.repositories.PedidoRepositories;
import com.laratecsys.jobfinder.repositories.ProdutoRepositories;

@Service
public class DBService {

	@Autowired
	private CategoriaRepositories categoriaRepositorie;
	@Autowired
	private ProdutoRepositories produtoRepositories;
	@Autowired
	private EstadoRepositories estadoRepo;
	@Autowired
	private CidadeRepositories cidadeRepo;
	@Autowired
	private ClienteRepositories clienteRepo;
	@Autowired
	private EnderecoRepositories enderecoRepo;
	@Autowired
	private PedidoRepositories pedidoRepo;
	@Autowired
	private PagamentoRepositories pagamentoRepo;
	@Autowired
	private ItemPedidoRepositories itemPedidoRepo;
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public void instantiateTestDatabase() throws ParseException {
	Categoria cat1 = new Categoria(null,"Cocada");
	Categoria cat2 = new Categoria(null,"Gelatina");
	Categoria cat3 = new Categoria(null,"Biroba");
	Categoria cat4 = new Categoria(null,"Biriba");
	Categoria cat5 = new Categoria(null,"Servicos");
	Categoria cat6 = new Categoria(null,"Curto Prazo");
	Categoria cat7 = new Categoria(null,"Médio Prazo");
	Categoria cat8 = new Categoria(null,"Garçonete");
	
	Produto p1 = new Produto(null, "Programação", 2.50);
	Produto p2 = new Produto(null, "Instalação", 2.50);
	Produto p3 = new Produto(null, "Manutenção", 2.50);
	Produto p4 = new Produto(null, "Garçom", 2.50);
	Produto p5 = new Produto(null, "Cozinha", 1.0);
	Produto p6 = new Produto(null, "Caixa", 2.50);
	Produto p7 = new Produto(null, "Cocada Branca", 2.50);
	Produto p8 = new Produto(null, "Cocada Maracujá", 2.50);
	Produto p9 = new Produto(null, "Cocada Mesclada", 2.50);
	Produto p10 = new Produto(null, "Gelatina Morango", 1.0);
	
	cat1.getProdutos().addAll(Arrays.asList(p6,p7,p8,p9));
	cat2.getProdutos().addAll(Arrays.asList(p5));
	cat5.getProdutos().addAll(Arrays.asList(p1,p2,p3,p4,p5));
	cat6.getProdutos().addAll(Arrays.asList(p1,p2));
	cat7.getProdutos().addAll(Arrays.asList(p3,p4,p5));
	
	p1.getCategorias().add(cat1);
	p2.getCategorias().add(cat1);
	p3.getCategorias().add(cat1);
	p4.getCategorias().add(cat1);
	p5.getCategorias().add(cat2);
	
	categoriaRepositorie.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8));
	produtoRepositories.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
	
	Estado estado1 = new Estado(null, "Paraná");
	
	Cidade c1 = new Cidade(null, "Curitiba", estado1);
	Cidade c2 = new Cidade(null, "Londrina", estado1);
	Cidade c3 = new Cidade(null, "Matinhos", estado1);
	
	estado1.getCidades().addAll(Arrays.asList(c1,c2,c3));
	
	
	estadoRepo.save((estado1));
	cidadeRepo.saveAll(Arrays.asList(c1,c2,c3));
	
	Cliente cli1 = new Cliente(null,"Tiago Ribeiro", "laratecsys@gmail.com","07406192924", TipoCliente.PESSOA_FISICA, pe.encode("1234"));
	Cliente cli2 = new Cliente(null,"Andrea Quadros de Lara", "andreaQlr@gmail.com","07406192924", TipoCliente.PESSOA_FISICA, pe.encode("1234"));
	cli2.addPerfil(Perfil.ADMIN);
	
	cli1.getTelefones().addAll(Arrays.asList("41991021102","4132160318"));
	cli1.getTelefones().addAll(Arrays.asList("41992224242","41991021102"));
	
	Endereco end1 = new Endereco(null, "Sobrado", "33", "D", "Uberaba", "81580120", cli1, c1);
	Endereco end2 = new Endereco(null, "Sobrado", "33", "D", "Wenceslau", "81010010", cli1, c2);
	Endereco end3 = new Endereco(null, "Ap", "102", "", "Uberaba", "81010010", cli2, c2);
	
	cli1.getEnderecos().addAll(Arrays.asList(end1,end2));
	cli2.getEnderecos().addAll(Arrays.asList(end3));
	
	clienteRepo.saveAll(Arrays.asList(cli1,cli2));
	
	enderecoRepo.saveAll(Arrays.asList(end1,end2, end3));
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
	Pedido ped1 = new Pedido(null, sdf.parse("16/03/2020 12:52"), cli1, end1);
	
	Pedido ped2 = new Pedido(null, sdf.parse("17/03/2020 12:52"), cli1, end2);
	
	Pedido ped3 = new Pedido(null, sdf.parse("17/03/2020 12:52"), cli2, end3);
	
	Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 3);
	ped1.setPagamento(pagto1);
	
	Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.QUITADO, ped2, sdf.parse("17/03/2020 12:52"), null);
	ped2.setPagamento(pagto2);
	
	Pagamento pagto3 = new PagamentoComBoleto(null, EstadoPagamento.QUITADO, ped3, sdf.parse("17/03/2020 12:52"), null);
	ped3.setPagamento(pagto3);
	
	cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
	cli2.getPedidos().addAll(Arrays.asList(ped3));
	
	pedidoRepo.saveAll(Arrays.asList(ped1,ped2,ped3));
	pagamentoRepo.saveAll(Arrays.asList(pagto1,pagto2,pagto3));
	
	ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 3, 45.0);
	ItemPedido ip2 = new ItemPedido(ped1, p2, 0.0, 3, 45.0);
	ItemPedido ip3 = new ItemPedido(ped1, p3, 0.0, 3, 45.0);
	
	ped1.getItens().addAll(Arrays.asList(ip1,ip2,ip3));
	
	p1.getItens().add(ip1);
	p2.getItens().add(ip2);
	p3.getItens().add(ip3);
	
	itemPedidoRepo.saveAll(Arrays.asList(ip1,ip2,ip3));
	}
}
