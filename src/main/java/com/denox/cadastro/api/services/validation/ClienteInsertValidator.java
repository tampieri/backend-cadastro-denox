package com.denox.cadastro.api.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.denox.cadastro.api.domain.Cliente;
import com.denox.cadastro.api.domain.enums.TipoCliente;
import com.denox.cadastro.api.dto.ClienteNewDTO;
import com.denox.cadastro.api.repositories.ClienteRepository;
import com.denox.cadastro.api.resources.exception.FieldMessage;
import com.denox.cadastro.api.services.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repoCliente;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
 		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
 		
 		Cliente aux = repoCliente.findByCpfOuCnpj(objDto.getCpfOuCnpj());
 		if(aux != null) {
 			list.add(new FieldMessage("CpfOuCnpj", "Cpf ou Cnpj já cadastrado"));
 		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}