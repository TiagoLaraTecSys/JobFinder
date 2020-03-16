package com.laratecsys.jobfinder.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laratecsys.jobfinder.domain.ItemPedido;;

@Repository
public interface ItemPedidoRepositories extends JpaRepository<ItemPedido, Integer>{

	
}
