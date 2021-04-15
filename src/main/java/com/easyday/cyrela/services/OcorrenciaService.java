package com.easyday.cyrela.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easyday.cyrela.domain.Ocorrencia;
import com.easyday.cyrela.repositories.OcorrenciaRepository;
import com.easyday.cyrela.services.exceptions.ObjectNotFoundException;

@Service
public class OcorrenciaService {

	@Autowired
	private OcorrenciaRepository repository;

	public Ocorrencia find(Integer id) {
		Optional<Ocorrencia> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Ocorrencia.class.getName())); 
	}
	
	@Transactional
	public Ocorrencia insert(Ocorrencia ocorrencia) {
		ocorrencia.setNumeroOcorrencia(null);
		ocorrencia = repository.save(ocorrencia);
		return ocorrencia;
	}

	public Ocorrencia update(Ocorrencia ocorrencia) {
		Ocorrencia newOcorrencia = find(ocorrencia.getNumeroOcorrencia());
		updateData(newOcorrencia, ocorrencia);
		return repository.save(newOcorrencia);
	}
	
	public void deleteById(Integer id) {
		find(id);
//		try {
			repository.deleteById(id);
//		} catch(DataIntegrityViolationException e) {
//			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
//		}
	}

	public List<Ocorrencia> findAll() {
		return repository.findAll();
	}
	
	public Page<Ocorrencia> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	private void updateData(Ocorrencia newOcorrencia, Ocorrencia ocorrencia) {
		newOcorrencia.setDescricao(ocorrencia.getDescricao());;
	}
}