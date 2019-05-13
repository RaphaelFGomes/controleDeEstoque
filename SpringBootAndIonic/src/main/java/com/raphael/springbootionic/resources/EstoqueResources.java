package com.raphael.springbootionic.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.raphael.springbootionic.domain.Estoque;
import com.raphael.springbootionic.domain.enums.TipoBebida;
import com.raphael.springbootionic.dto.EstoqueDTO;
import com.raphael.springbootionic.dto.RespostaDTO;
import com.raphael.springbootionic.dto.SecaoDisponivelDTO;
import com.raphael.springbootionic.services.EstoqueService;

@RestController
@RequestMapping(value="/estoque")
public class EstoqueResources {
	
	@Autowired
	private EstoqueService service;
	
	/*
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Client> find(@PathVariable Integer id) {
		
		Client obj = service.find(id);
		return ResponseEntity.ok().body(obj);		
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody EstoqueDTO objDTO) {
		Client obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	*/
	
	@RequestMapping(value="/id/{id}/secao/{secao}", method=RequestMethod.GET)
	public ResponseEntity<EstoqueDTO> consultaBebibaPorSecao(@PathVariable Integer id, @PathVariable Integer secao) {
		Estoque obj = service.consultBebibaPorIdESecao(id, secao);
		EstoqueDTO objDTO = service.fromDomain(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@RequestMapping(value="/secao/{secao}", method=RequestMethod.GET)
	public ResponseEntity<List<EstoqueDTO>> consultaEstoquePorSecaoPage(@PathVariable(value="secao") Integer secao) {
		List<Estoque> list = service.consultaEstoquePorSecao(secao);
		List<EstoqueDTO> listDto = service.fromListEstoque(list);
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/volumetotal/tipobebida/{tipoBebida}", method=RequestMethod.GET)
	public ResponseEntity<RespostaDTO> consultaVolumeTotalEstoquePorTipoBebida(@PathVariable(value="tipoBebida") Integer tipoBebida) {
		int volumeTotal = service.consultaVolumeTotalEstoquePorTipoBebida(tipoBebida);
		String tipoBebidaDescricao = tipoBebida == 1 ? TipoBebida.toEnum(1).getDescricao() : TipoBebida.toEnum(2).getDescricao();
		RespostaDTO resposta = new RespostaDTO()
				.setTipoBebida(tipoBebida)
				.setTipoBebidaDescricao(tipoBebidaDescricao)
				.setMessage("Volume total no estoque é " + volumeTotal + " por tipo de bebida " + tipoBebidaDescricao)
				.setVolumeTotal(volumeTotal);
		return ResponseEntity.ok().body(resposta);
	}
	
	@RequestMapping(value="/volumetotal/tipobebida/{tipoBebida}/secao/{secao}", method=RequestMethod.GET)
	public ResponseEntity<RespostaDTO> consultaVolumeByTipoBebidaAndSecao(
			@PathVariable(value="tipoBebida") Integer tipoBebida,
			@PathVariable(value="secao") Integer secao) {
		int volumeSecao = service.consultaVolumeByTipoBebidaAndSecao(tipoBebida, secao);
		String tipoBebidaDescricao = tipoBebida == 1 ? TipoBebida.toEnum(1).getDescricao() : TipoBebida.toEnum(2).getDescricao();
		RespostaDTO resposta = new RespostaDTO()
				.setTipoBebida(tipoBebida)
				.setTipoBebidaDescricao(tipoBebidaDescricao)
				.setMessage("Volume da seção " + secao + " é " + volumeSecao + " por tipo de bebida " + tipoBebidaDescricao)
				.setVolumeTotal(volumeSecao);
		return ResponseEntity.ok().body(resposta);
	}
	
	@RequestMapping(value="/secoesdisponiveis/tipobebida/{tipoBebida}/volume/{volume}", method=RequestMethod.GET)
	public ResponseEntity<RespostaDTO> consultaSecoesDisponiveisPorTipoBebidaSecao(
			@PathVariable(value="tipoBebida") Integer tipoBebida,
			@PathVariable(value="volume") Integer volume) {
		List<SecaoDisponivelDTO> secoesDisponiveis = service.consultaSecoesDisponiveisPorTipoBebidaSecao(tipoBebida, volume);
		String tipoBebidaDescricao = tipoBebida == 1 ? TipoBebida.toEnum(1).getDescricao() : TipoBebida.toEnum(2).getDescricao();
		StringBuilder secoes = new StringBuilder();
		
		for (int i = 0; i < secoesDisponiveis.size(); i++) {
		    if (i == secoesDisponiveis.size()-1) {
		    	if (secoes.length() == 0) {
		    		secoes.append(secoesDisponiveis.get(i).getSecao());
		    	} else {
		    		secoes.append(" e " + secoesDisponiveis.get(i).getSecao());
		    	}
			} else {
				if (secoes.length() == 0) {
					secoes.append(secoesDisponiveis.get(i).getSecao());
				} else {
					secoes.append(", " + secoesDisponiveis.get(i).getSecao());
				}
			}
		}
		
		RespostaDTO resposta = new RespostaDTO()
				.setMessage("Seções disponíveis para o tipo de bebida " + tipoBebidaDescricao + " e volume " + volume + ": " + secoes)
				.setSecoesDisponiveis(secoesDisponiveis);
		return ResponseEntity.ok().body(resposta);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<RespostaDTO> insere(@Valid @RequestBody EstoqueDTO objDTO) {
		Estoque obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id/{id}/secao/{secao}").buildAndExpand(obj.getId(), obj.getSecao()).toUri();
		RespostaDTO resposta = new RespostaDTO()
				.setIdBebida(obj.getId())
				.setSecao(obj.getSecao())
				.setMessage("Bebida adicionada ao estoque com sucesso!");
		return ResponseEntity.created(uri).body(resposta);
	}
	
	@RequestMapping(value="/id/{id}/secao/{secao}", method=RequestMethod.DELETE)
	public ResponseEntity<RespostaDTO> remove(@PathVariable Integer id, @PathVariable Integer secao) {
		service.deleteByIdAndSecao(id, secao);
		RespostaDTO resposta = new RespostaDTO()
				.setIdBebida(id)
				.setSecao(secao)
				.setMessage("Bebida retirada do estoque com sucesso!");
		return ResponseEntity.ok().body(resposta);
	}
	
	/*
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody ClientDTO objDTO) {
		Client obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {		
		service.delete(id);		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClientDTO>> findAll() {		
		List<Client> list = service.findAll();
		List<ClientDTO> listDto = list.stream().map(obj -> new ClientDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ClientDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="name") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {		
		Page<Client> list = service.findPage(page, linesPerPage, direction, orderBy);
		Page<ClientDTO> listDto = list.map(obj -> new ClientDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	*/
}
