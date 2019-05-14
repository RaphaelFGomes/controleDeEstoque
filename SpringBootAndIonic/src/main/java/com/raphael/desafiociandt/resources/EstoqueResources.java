package com.raphael.desafiociandt.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.raphael.desafiociandt.domain.Estoque;
import com.raphael.desafiociandt.domain.enums.TipoBebida;
import com.raphael.desafiociandt.dto.EstoqueDTO;
import com.raphael.desafiociandt.dto.RespostaDTO;
import com.raphael.desafiociandt.dto.SecaoDisponivelDTO;
import com.raphael.desafiociandt.services.EstoqueService;

@RestController
@RequestMapping(value="/estoque")
public class EstoqueResources {
	
	@Autowired
	private EstoqueService service;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<EstoqueDTO> consultaBebiba(@PathVariable Integer id) {
		Estoque obj = service.consultBebibaPorId(id);
		EstoqueDTO objDTO = service.fromDomain(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstoqueDTO>> consultaEstoquePorSecaoPage(@RequestParam(value="secao") Integer secao) {
		List<Estoque> list = service.consultaEstoquePorSecao(secao);
		List<EstoqueDTO> listDto = service.fromListEstoque(list);
		return ResponseEntity.ok().body(listDto);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/volumetotalestoque", method=RequestMethod.GET)
	public ResponseEntity<RespostaDTO> consultaVolumeTotalEstoquePorTipoBebida(@RequestParam(value="tipobebida") Integer tipoBebida) {
		int volumeTotal = service.consultaVolumeTotalEstoquePorTipoBebida(tipoBebida);
		String tipoBebidaDescricao = tipoBebida == 1 ? TipoBebida.toEnum(1).getDescricao() : TipoBebida.toEnum(2).getDescricao();
		RespostaDTO resposta = new RespostaDTO()
				.setTipoBebida(tipoBebida)
				.setTipoBebidaDescricao(tipoBebidaDescricao)
				.setMessage("Volume total no estoque é " + volumeTotal + " por tipo de bebida " + tipoBebidaDescricao)
				.setVolumeTotal(volumeTotal);
		return ResponseEntity.ok().body(resposta);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/volumetotalsecao", method=RequestMethod.GET)
	public ResponseEntity<RespostaDTO> consultaVolumeByTipoBebidaAndSecao(
			@RequestParam(value="tipobebida") Integer tipoBebida,
			@RequestParam(value="secao") Integer secao) {
		int volumeSecao = service.consultaVolumeByTipoBebidaAndSecao(tipoBebida, secao);
		String tipoBebidaDescricao = tipoBebida == 1 ? TipoBebida.toEnum(1).getDescricao() : TipoBebida.toEnum(2).getDescricao();
		RespostaDTO resposta = new RespostaDTO()
				.setTipoBebida(tipoBebida)
				.setTipoBebidaDescricao(tipoBebidaDescricao)
				.setMessage("Volume da seção " + secao + " é " + volumeSecao + " por tipo de bebida " + tipoBebidaDescricao)
				.setVolumeTotal(volumeSecao);
		return ResponseEntity.ok().body(resposta);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/secoesdisponiveisporvolume", method=RequestMethod.GET)
	public ResponseEntity<RespostaDTO> consultaSecoesDisponiveisPorTipoBebidaSecao(@RequestParam(value="volume") Integer volume) {
		List<SecaoDisponivelDTO> secoesDisponiveis = service.consultaSecoesDisponiveisPorVolume(volume);
		String message = "";
		RespostaDTO resposta = null;
		if (secoesDisponiveis == null || secoesDisponiveis.size() == 0) {
			message = "Não tem seção disponível para o volume de bebida: " + volume;
			resposta = new RespostaDTO()
					.setMessage(message);
		} else {
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
			message = "Seções disponíveis para o volume de bebida " + volume + ": " + secoes;
			resposta = new RespostaDTO()
					.setMessage(message)
					.setSecoesDisponiveis(secoesDisponiveis);
		}
		return ResponseEntity.ok().body(resposta);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/secoesdisponiveisvenda", method = RequestMethod.GET)
	public ResponseEntity<RespostaDTO> consultaSecoesDisponiveisVendaPorTipoBebida(
			@RequestParam(value = "tipobebida") Integer tipoBebida) {
		RespostaDTO resposta = null;
		String message = "";

		
		if (tipoBebida != 1 && tipoBebida != 2) {
			message = "Não tem seção disponível para venda do tipo de bebida " + tipoBebida;
			resposta = new RespostaDTO()
					.setMessage(message);
		} else {
			List<SecaoDisponivelDTO> secoesDisponiveis = service.consultaSecoesDisponiveisVendaPorTipoBebida(tipoBebida);
			String tipoBebidaDescricao = tipoBebida == 1 ? TipoBebida.toEnum(1).getDescricao() : TipoBebida.toEnum(2).getDescricao();

			if (secoesDisponiveis == null || secoesDisponiveis.size() == 0) {
				message = "Não tem seção disponível para venda do tipo de bebida " + tipoBebidaDescricao;
				resposta = new RespostaDTO().setMessage(message);
			} else {
				StringBuilder secoes = new StringBuilder();
				for (int i = 0; i < secoesDisponiveis.size(); i++) {
					if (i == secoesDisponiveis.size() - 1) {
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
				message = "Seções disponíveis para venda do tipo de bebida " + tipoBebidaDescricao + ": " + secoes;
				resposta = new RespostaDTO()
						.setMessage(message)
						.setSecoesDisponiveis(secoesDisponiveis);
			}
		}
		return ResponseEntity.ok().body(resposta);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<RespostaDTO> insere(@Valid @RequestBody EstoqueDTO objDTO) {
		Estoque obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId(), obj.getSecao()).toUri();
		RespostaDTO resposta = new RespostaDTO()
				.setIdBebida(obj.getId())
				.setSecao(obj.getSecao())
				.setMessage("Bebida adicionada ao estoque com sucesso!");
		return ResponseEntity.created(uri).body(resposta);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<RespostaDTO> remove(@PathVariable Integer id) {
		service.deleteById(id);
		RespostaDTO resposta = new RespostaDTO()
				.setIdBebida(id)
				.setMessage("Bebida retirada do estoque com sucesso!");
		return ResponseEntity.ok().body(resposta);
	}
	
}
