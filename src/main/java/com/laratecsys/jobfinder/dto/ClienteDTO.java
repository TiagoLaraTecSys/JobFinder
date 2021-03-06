package com.laratecsys.jobfinder.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.laratecsys.jobfinder.domain.Cliente;
import com.laratecsys.jobfinder.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Length(min=5, max=120, message = "Tamanho máximo 120 e mínimo 5")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Email
	private String email;
	
	public ClienteDTO() {
		
	}
	
	public ClienteDTO(Cliente obj) {
		
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
