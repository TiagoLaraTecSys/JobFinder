package com.laratecsys.jobfinder.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laratecsys.jobfinder.domain.Cidade;

@Repository
public interface CidadeRepositories extends JpaRepository<Cidade, Integer>{

	
}
