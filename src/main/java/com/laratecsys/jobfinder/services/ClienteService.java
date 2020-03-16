package com.laratecsys.jobfinder.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laratecsys.jobfinder.domain.Cliente;
import com.laratecsys.jobfinder.repositories.ClienteRepositories;
import com.laratecsys.jobfinder.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepositories repo;

	public Cliente find(Integer id){
		
		Optional<Cliente> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrador. ID:" + id +", Tipo:" + Cliente.class.getName()) );
	}
	
}
