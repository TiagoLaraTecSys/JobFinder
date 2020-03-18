package com.laratecsys.jobfinder.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.laratecsys.jobfinder.domain.enums.TipoCliente;
import com.laratecsys.jobfinder.dto.ClienteNewDTO;
import com.laratecsys.jobfinder.resources.exception.FieldMessage;
import com.laratecsys.jobfinder.services.validation.util.BR;

public class ClientInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Override
	public void initialize(ClienteInsert ann) {
		
	}
	@Override
	public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {


		List<FieldMessage> errors = new ArrayList<>();
		
		if (objDTO.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod()) && !BR.isValidSsn(objDTO.getCpfOuCnpj())) {
			errors.add(new FieldMessage("CpfOuCnpj", "CPF inválido", objDTO.getCpfOuCnpj()));
		}
		
		if (objDTO.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod()) && !BR.isValidTfn(objDTO.getCpfOuCnpj())) {
			errors.add(new FieldMessage("CpfOuCnpj", "CNPJ inválido", objDTO.getCpfOuCnpj()));
		}
		
		for (FieldMessage fieldMessage : errors) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage()).addPropertyNode(fieldMessage.getFieldName())
			.addConstraintViolation();
			
			
		}
		return errors.isEmpty();
	}
	
	

}
