package com.laratecsys.jobfinder.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laratecsys.jobfinder.domain.Pagamento;

@Repository
public interface PagamentoRepositories extends JpaRepository<Pagamento, Integer>{

	
}
