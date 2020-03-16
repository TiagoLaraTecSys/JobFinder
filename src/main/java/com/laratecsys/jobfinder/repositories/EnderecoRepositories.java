package com.laratecsys.jobfinder.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laratecsys.jobfinder.domain.Endereco;

@Repository
public interface EnderecoRepositories extends JpaRepository<Endereco, Integer>{

	
}
