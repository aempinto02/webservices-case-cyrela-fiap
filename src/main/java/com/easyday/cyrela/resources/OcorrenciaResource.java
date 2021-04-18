package com.easyday.cyrela.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.easyday.cyrela.domain.Cliente;
import com.easyday.cyrela.domain.Ocorrencia;
import com.easyday.cyrela.services.ClienteService;
import com.easyday.cyrela.services.OcorrenciaService;

@RestController
@RequestMapping(value="/ocorrencias")
public class OcorrenciaResource {

	@Autowired
	private OcorrenciaService service;
	
	@Autowired
	private ClienteService cliService;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Ocorrencia> find(@PathVariable Integer id) {
		Ocorrencia ocorrencia = service.find(id);
		return ResponseEntity.ok().body(ocorrencia);
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Ocorrencia>> findAll() {
		List<Ocorrencia> lista = service.findAll();
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<Ocorrencia>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="numeroOcorrencia") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Ocorrencia> pagina = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(pagina);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Ocorrencia ocorrencia) {
		Cliente cliente = cliService.find(ocorrencia.getClienteDaUnidade().getId());
		ocorrencia.setClienteDaUnidade(cliente);
		ocorrencia = service.insert(ocorrencia);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(ocorrencia.getNumeroOcorrencia()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Ocorrencia ocorrencia, @PathVariable Integer id) {
		ocorrencia.setNumeroOcorrencia(id);
		ocorrencia = service.update(ocorrencia);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}