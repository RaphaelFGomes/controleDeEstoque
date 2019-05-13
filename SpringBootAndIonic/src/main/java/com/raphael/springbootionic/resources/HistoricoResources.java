package com.raphael.springbootionic.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raphael.springbootionic.domain.Historico;
import com.raphael.springbootionic.dto.HistoricoDTO;
import com.raphael.springbootionic.services.HistoricoService;

@RestController
@RequestMapping(value="/historico")
public class HistoricoResources {
	
	@Autowired
	private HistoricoService service;
	
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
