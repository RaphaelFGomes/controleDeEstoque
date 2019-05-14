package com.raphael.desafiociandt.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raphael.desafiociandt.domain.Historico;
import com.raphael.desafiociandt.dto.HistoricoDTO;
import com.raphael.desafiociandt.services.HistoricoService;

@RestController
@RequestMapping(value="/historico")
public class HistoricoResources {
	
	@Autowired
	private HistoricoService service;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<HistoricoDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="secao") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Historico> list = service.findPage(page, linesPerPage, direction, orderBy);
		Page<HistoricoDTO> listDto = service.fromHistToDTO(list);
		return ResponseEntity.ok().body(listDto);
	}
	
}
