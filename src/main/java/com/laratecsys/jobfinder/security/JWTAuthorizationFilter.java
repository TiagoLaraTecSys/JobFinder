package com.laratecsys.jobfinder.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{


	private JWTUtil jwtUtil;	
	private UserDetailsService userDetailsService;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService) {
		super(authenticationManager);
		
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
		
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//super.doFilterInternal(request, response, chain);
		
		String authToken = request.getHeader("Authorization");
		//System.out.println(authToken);
		if (authToken != null && authToken.startsWith("Bearer ")) {
			
			UsernamePasswordAuthenticationToken auth = getAuthentication(request, authToken.substring(7));
			
			if(auth != null) {
				//System.out.println(auth.getName());
				SecurityContextHolder.getContext().setAuthentication(auth);
				System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
			}
		}
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, String token) {
		
		if (jwtUtil.tokenValido(token)) {
			System.out.println(token);
			String username = jwtUtil.getUsername(token);
			
			UserDetails user = userDetailsService.loadUserByUsername(username);
			
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		}
		return null;
	}
	
	

}
