package com.laratecsys.jobfinder.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.laratecsys.jobfinder.domain.Cliente;
import com.laratecsys.jobfinder.repositories.ClienteRepositories;
import com.laratecsys.jobfinder.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepositories cliRepo;
	
	@Autowired
	BCryptPasswordEncoder bCrypt;
	
	@Autowired
	EmailService emailService;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		
		Cliente cliente = cliRepo.findByEmail(email);
		
		if (cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		String newPass = newPassword();
		
		cliente.setSenha(bCrypt.encode(newPass));
	
		cliRepo.save(cliente);
		
		emailService.sendNewPasswordEmail(cliente, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		
		for (int i = 0; i < vet.length; i++) {
			
			vet[i] = randomChar();
		}
		
		return new String(vet);
	}

	private char randomChar() {
		 
		int opt = rand.nextInt(3);
		char s = 0;
		switch (opt) {
		case 0:
			
			s = (char) (rand.nextInt(10) + 48);
			
		case 1:
			
			s = (char) (rand.nextInt(26) + 65);
		case 2:
			
			s = (char) (rand.nextInt(26) + 97);

		}
		return s;
		
		
	}
}
