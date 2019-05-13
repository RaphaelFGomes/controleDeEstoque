package com.raphael.springbootionic.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.raphael.springbootionic.domain.Client;
import com.raphael.springbootionic.domain.enums.ClientType;
import com.raphael.springbootionic.dto.ClientNewDTO;
import com.raphael.springbootionic.exception.FieldMessage;
import com.raphael.springbootionic.repositories.ClientRepository;
import com.raphael.springbootionic.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
	
	@Autowired
	private ClientRepository repo;
	
	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getType().equals(ClientType.PERSON.getCod()) && !BR.isValidCPF(objDto.getCode())) {
			list.add(new FieldMessage("code", "Invalid code"));
		}
		
		if (objDto.getType().equals(ClientType.COMPANY.getCod()) && !BR.isValidCNPJ(objDto.getCode())) {
			list.add(new FieldMessage("code", "Invalid code"));
		}
		
		Client aux = repo.findByEmail(objDto.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "Email already exists"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}