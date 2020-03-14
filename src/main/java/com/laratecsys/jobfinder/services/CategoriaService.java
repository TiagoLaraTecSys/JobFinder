package com.laratecsys.jobfinder.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laratecsys.jobfinder.domain.Categoria;
import com.laratecsys.jobfinder.repositories.CategoriaRepositories;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepositories repo;

	public Categoria find(Integer id) {
		
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
	public Categoria insert(Categoria insert) {
		
		System.out.println(insert.getNome());
		
		Categoria cat = repo.save(insert);
		return cat;
		
	}
}
