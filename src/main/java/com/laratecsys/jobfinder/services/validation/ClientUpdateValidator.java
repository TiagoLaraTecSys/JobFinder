package com.laratecsys.jobfinder.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.laratecsys.jobfinder.domain.Cliente;
import com.laratecsys.jobfinder.dto.ClienteDTO;
import com.laratecsys.jobfinder.repositories.ClienteRepositories;
import com.laratecsys.jobfinder.resources.exception.FieldMessage;

public class ClientUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private ClienteRepositories repo;
	
	@Override
	public void initialize(ClienteUpdate ann) {
		
	}
	@Override
	public boolean isValid(ClienteDTO objDTO, ConstraintValidatorContext context) {

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		Integer mapId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> errors = new ArrayList<>();
		
		Cliente aux = repo.findByEmail(objDTO.getEmail());
		if (aux != null && !aux.getId().equals(mapId)) {
			errors.add(new FieldMessage("Email", "Email ja existe!", objDTO.getEmail()));  
		}
		
		for (FieldMessage fieldMessage : errors) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage()).addPropertyNode(fieldMessage.getFieldName())
			.addConstraintViolation();
			
			
		}
		return errors.isEmpty();
	}
	
	

}
