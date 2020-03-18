package com.laratecsys.jobfinder.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.laratecsys.jobfinder.domain.Categoria;
import com.laratecsys.jobfinder.domain.Produto;
import com.laratecsys.jobfinder.repositories.CategoriaRepositories;
import com.laratecsys.jobfinder.repositories.ProdutoRepositories;
import com.laratecsys.jobfinder.services.exceptions.DataIntegrityException7;
import com.laratecsys.jobfinder.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepositories repo;
	@Autowired
	private CategoriaRepositories categoriaRepo;

	public Produto find(Integer id) {

		Optional<Produto> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrador. ID:" + id + ", Tipo:" + Produto.class.getName()));
	}

	public Produto insert(Produto obj) {

		obj.setId(null);
		return repo.save(obj);
	}

	public Produto update(Produto obj) {

		Produto newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);

	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException7("Não é possível deletar categoria que possui produtos");
		}
	}

	public List<Produto> findAll() {

		return repo.findAll();
	}
	
	public Page<Produto> search (String nome, List<Integer> ids,Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepo.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome,categorias,pageRequest);
		
	}

	public Page<Produto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

//	public Produto fromDTO(ProdutoDTO obj) {
//
//		return new Produto(obj.getId(), obj.getNome());
//	}

	private void updateData(Produto newObj, Produto obj) {

		newObj.setNome(obj.getNome());

	}
}
