package com.laratecsys.jobfinder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.laratecsys.jobfinder.domain.Cliente;
import com.laratecsys.jobfinder.repositories.ClienteRepositories;
import com.laratecsys.jobfinder.security.UserSS;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private ClienteRepositories cliService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Cliente newCli = cliService.findByEmail(email);
		
		if (newCli == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(newCli.getId(),newCli.getEmail(),newCli.getSenha(),newCli.getPerfis());
	}

}
