package com.laratecsys.jobfinder.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laratecsys.jobfinder.domain.Categoria;

@Repository
public interface CategoriaRepositories extends JpaRepository<Categoria, Integer>{

	
}
