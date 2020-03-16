package com.laratecsys.jobfinder;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.laratecsys.jobfinder.domain.Categoria;
import com.laratecsys.jobfinder.domain.Cidade;
import com.laratecsys.jobfinder.domain.Estado;
import com.laratecsys.jobfinder.domain.Produto;
import com.laratecsys.jobfinder.repositories.CategoriaRepositories;
import com.laratecsys.jobfinder.repositories.CidadeRepositories;
import com.laratecsys.jobfinder.repositories.EstadoRepositories;
import com.laratecsys.jobfinder.repositories.ProdutoRepositories;

@SpringBootApplication
public class JobfinderApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepositories categoriaRepositorie;
	@Autowired
	private ProdutoRepositories produtoRepositories;
	@Autowired
	private EstadoRepositories estadoRepo;
	@Autowired
	private CidadeRepositories cidadeRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(JobfinderApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		
			Categoria cat1 = new Categoria(null,"Cocada");
			Categoria cat2 = new Categoria(null,"Gelatina");
		
			Produto p1 = new Produto(null, "Cocada Preta", 2.50);
			Produto p2 = new Produto(null, "Cocada Branca", 2.50);
			Produto p3 = new Produto(null, "Cocada Maracujá", 2.50);
			Produto p4 = new Produto(null, "Cocada Mesclada", 2.50);
			Produto p5 = new Produto(null, "Gelatina Morango", 1.0);
			
			cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3,p4));
			cat2.getProdutos().addAll(Arrays.asList(p5));
			
			p1.getCategorias().add(cat1);
			p2.getCategorias().add(cat1);
			p3.getCategorias().add(cat1);
			p4.getCategorias().add(cat1);
			p5.getCategorias().add(cat2);
			
			Estado estado1 = new Estado(null, "Paraná");
			
			Cidade c1 = new Cidade(null, "Curitiba", estado1);
			Cidade c2 = new Cidade(null, "Londrina", estado1);
			Cidade c3 = new Cidade(null, "Matinhos", estado1);
			
			estado1.getCidades().addAll(Arrays.asList(c1,c2,c3));
			
			
			estadoRepo.save((estado1));
			cidadeRepo.saveAll(Arrays.asList(c1,c2,c3));
			categoriaRepositorie.saveAll(Arrays.asList(cat1,cat2));
			produtoRepositories.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
			
	}

}
