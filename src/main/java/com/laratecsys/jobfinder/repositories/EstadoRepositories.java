package com.laratecsys.jobfinder.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laratecsys.jobfinder.domain.Estado;

@Repository
public interface EstadoRepositories extends JpaRepository<Estado, Integer>{

	
}
