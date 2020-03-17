package com.laratecsys.jobfinder.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laratecsys.jobfinder.domain.Pedido;
import com.laratecsys.jobfinder.repositories.PedidoRepositories;

import com.laratecsys.jobfinder.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepositories repo;

	public Pedido find(Integer id){
		
		Optional<Pedido> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrador. ID:" + id +", Tipo:" + Pedido.class.getName()) );
	}
	
}
