package com.laratecsys.jobfinder.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.mail.internet.ContentType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laratecsys.jobfinder.dto.CredenciaisDTO;
import com.mysql.cj.x.protobuf.MysqlxResultset.ContentType_BYTES;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationManager authenticationManager;
	
	private JWTUtil jwtUtil;
	
	
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		
		setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}


	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,HttpServletResponse res) throws AuthenticationException  {
		
		try {
			CredenciaisDTO creds = new ObjectMapper().readValue(req.getInputStream(), CredenciaisDTO.class);
			
			UsernamePasswordAuthenticationToken authToken = new 
					UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getSenha(), new ArrayList<>());
			
			return authenticationManager.authenticate(authToken);
			
			
			
		} catch (IOException e) {
			
			throw new RuntimeException(e);
		}
		
		
	}
	
	
	@Override
	public void successfulAuthentication(HttpServletRequest req,
			HttpServletResponse res, FilterChain chain, Authentication auth) throws AuthenticationException, IOException  {
		
		String userName = ((UserSS) auth.getPrincipal()).getUsername();
		String token = jwtUtil.generateToke(userName);
		res.setStatus(201);
		res.setContentType("application/json");
		res.getWriter().append("{\"authToken\": \"Bearer " + token + "\"}");
		res.addHeader("Authorization", "Bearer " + token);
		res.addHeader("access-control-expose-headers", "Authorization");
		
	}
	
	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {
		 
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
                throws IOException, ServletException {
            response.setStatus(401);
            response.setContentType("application/json"); 
            response.getWriter().append(json());
        }
        
        private String json() {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", "
                + "\"status\": 401, "
                + "\"error\": \"Não autorizado\", "
                + "\"message\": \"Email ou senha inválidos\", "
                + "\"path\": \"/login\"}";
        }
    }



}
