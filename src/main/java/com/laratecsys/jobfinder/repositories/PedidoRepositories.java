package com.laratecsys.jobfinder.repositories;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.laratecsys.jobfinder.domain.Cliente;
import com.laratecsys.jobfinder.domain.Pedido;

@Repository
public interface PedidoRepositories extends JpaRepository<Pedido, Integer>{

	@Transactional(readOnly = true)
	Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);
	
}
