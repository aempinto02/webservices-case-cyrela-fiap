package com.easyday.cyrela.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easyday.cyrela.domain.AtividadeAgendada;
import com.easyday.cyrela.repositories.AtividadeAgendadaRepository;
import com.easyday.cyrela.services.exceptions.ObjectNotFoundException;

@Service
public class AtividadeAgendadaService {

	@Autowired
	private AtividadeAgendadaRepository repository;

	public AtividadeAgendada find(Integer id) {
		Optional<AtividadeAgendada> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + AtividadeAgendada.class.getName())); 
	}
	
	@Transactional
	public AtividadeAgendada insert(AtividadeAgendada atividadeAgendada) {
		atividadeAgendada.setId(null);
		atividadeAgendada = repository.save(atividadeAgendada);
		return atividadeAgendada;
	}
	
	@Transactional
	public AtividadeAgendada update(AtividadeAgendada atividadeAgendada) {
		AtividadeAgendada newAtividadeAgendada = find(atividadeAgendada.getId());
		updateData(newAtividadeAgendada, atividadeAgendada);
		return repository.save(newAtividadeAgendada);
	}
	
	public void deleteById(Integer id) {
		find(id);
//		try {
			repository.deleteById(id);
//		} catch(DataIntegrityViolationException e) {
//			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
//		}
	}

	public List<AtividadeAgendada> findAll() {
		return repository.findAll();
	}
	
	public Page<AtividadeAgendada> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	private void updateData(AtividadeAgendada newAtividadeAgendada, AtividadeAgendada atividadeAgendada) {
		newAtividadeAgendada.setTipoAtividade(atividadeAgendada.getTipoAtividade());
		newAtividadeAgendada.setAssunto(atividadeAgendada.getAssunto());
	}
}
