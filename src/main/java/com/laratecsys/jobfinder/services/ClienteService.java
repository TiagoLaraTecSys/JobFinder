package com.laratecsys.jobfinder.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.laratecsys.jobfinder.domain.Cliente;
import com.laratecsys.jobfinder.dto.ClienteDTO;
import com.laratecsys.jobfinder.repositories.ClienteRepositories;
import com.laratecsys.jobfinder.services.exceptions.DataIntegrityException7;
import com.laratecsys.jobfinder.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepositories repo;

	public Cliente find(Integer id) {

		Optional<Cliente> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrador. ID:" + id + ", Tipo:" + Cliente.class.getName()));
	}

	public Cliente update(Cliente obj) {

		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);

	}

	public List<Cliente> findAll() {

		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public void delete(Integer id) {

		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {

			throw new DataIntegrityException7("Não é possível deletar Usuário que possui relações em outras tabelas");
		}

	}

	public Cliente fromDTO(ClienteDTO objDTO) {

		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(),null,null);

	}

	private void updateData(Cliente newObj, Cliente obj) {
		
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}
}
