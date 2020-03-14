package com.laratecsys.jobfinder;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.laratecsys.jobfinder.domain.Categoria;
import com.laratecsys.jobfinder.repositories.CategoriaRepositories;

@SpringBootApplication
public class JobfinderApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepositories categoriaRepositorie;
	
	public static void main(String[] args) {
		SpringApplication.run(JobfinderApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		
			Categoria cat1 = new Categoria(null,"Cocada");
			Categoria cat2 = new Categoria(null,"Gelatina");
		
			categoriaRepositorie.saveAll(Arrays.asList(cat1,cat2));
	}

}
