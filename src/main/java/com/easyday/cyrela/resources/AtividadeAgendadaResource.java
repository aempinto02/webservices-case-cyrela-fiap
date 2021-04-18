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

import com.easyday.cyrela.domain.AtividadeAgendada;
import com.easyday.cyrela.services.AtividadeAgendadaService;

@RestController
@RequestMapping(value="/atividadesAgendadas")
public class AtividadeAgendadaResource {

	@Autowired
	private AtividadeAgendadaService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<AtividadeAgendada> find(@PathVariable Integer id) {
		AtividadeAgendada atividadeAgendada = service.find(id);
		return ResponseEntity.ok().body(atividadeAgendada);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<AtividadeAgendada>> findAll() {
		List<AtividadeAgendada> lista = service.findAll();
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<AtividadeAgendada>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="id") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<AtividadeAgendada> pagina = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(pagina);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody AtividadeAgendada atividadeAgendada) {
		atividadeAgendada = service.insert(atividadeAgendada);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(atividadeAgendada.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody AtividadeAgendada atividadeAgendada, @PathVariable Integer id) {
		atividadeAgendada.setId(id);
		atividadeAgendada = service.update(atividadeAgendada);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}