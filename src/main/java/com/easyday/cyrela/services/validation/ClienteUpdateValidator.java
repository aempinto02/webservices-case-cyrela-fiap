package com.easyday.cyrela.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.easyday.cyrela.domain.Cliente;
import com.easyday.cyrela.repositories.ClienteRepository;
import com.easyday.cyrela.resources.exception.FieldMessage;
public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, Cliente> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository repository;
	
	@Override
	public void initialize(ClienteUpdate ann) {}
	
	@Override
	public boolean isValid(Cliente objeto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Cliente aux = repository.findByEmail(objeto.getEmail());
		if(aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
			.addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}