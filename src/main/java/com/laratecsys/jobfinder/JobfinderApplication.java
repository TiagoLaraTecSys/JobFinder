package com.laratecsys.jobfinder;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

@SpringBootApplication
public class JobfinderApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JobfinderApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

	}

}
