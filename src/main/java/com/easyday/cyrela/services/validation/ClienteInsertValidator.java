package com.easyday.cyrela.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.easyday.cyrela.domain.Cliente;
import com.easyday.cyrela.domain.enums.TipoCliente;
import com.easyday.cyrela.repositories.ClienteRepository;
import com.easyday.cyrela.resources.exception.FieldMessage;
import com.easyday.cyrela.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, Cliente> {
	
	@Autowired
	private ClienteRepository cliRepository;
	
	@Override
	public void initialize(ClienteInsert ann) {}
	
	@Override
	public boolean isValid(Cliente objeto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(objeto.getTipo().equals(TipoCliente.PESSOAFISICA) && !BR.isValidCPF(objeto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido!"));
		}
		
		if(objeto.getTipo().equals(TipoCliente.PESSOAJURIDICA) && !BR.isValidCNPJ(objeto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido!"));
		}
		
		Cliente aux = cliRepository.findByEmail(objeto.getEmail());
		if(aux != null) {
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