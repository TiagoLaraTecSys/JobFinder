package com.laratecsys.jobfinder.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.laratecsys.jobfinder.domain.Cliente;

@Repository
public interface ClienteRepositories extends JpaRepository<Cliente, Integer>{

	@Transactional(readOnly = true)
	Cliente findByEmail(String email);
}
