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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/estoque")
public class EstoqueResources {
	
	@Autowired
	private EstoqueService service;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation(value="Busca uma bebida específica por ID")
	@ApiResponses(value = {@ApiResponse(code = 404, message = "Não existe bebida com o ID específico")})
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<EstoqueDTO> consultaBebiba(@PathVariable Integer id) {
		Estoque obj = service.consultBebibaPorId(id);
		EstoqueDTO objDTO = service.fromDomain(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation(value="Busca todas as bebidas armazenadas em uma determinada seção")
	@ApiResponses(value = {@ApiResponse(code = 404, message = "Não existe bebida cadastrada para a seção específica")})
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstoqueDTO>> consultaEstoquePorSecao(@RequestParam(value="secao") Integer secao) {
		List<Estoque> list = service.consultaEstoquePorSecao(secao);
		List<EstoqueDTO> listDto = service.fromListEstoque(list);
		return ResponseEntity.ok().body(listDto);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation(value="Busca o volume total armazenado no estoque por um determinado tipo de bebida")
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
	@ApiOperation(value="Busca o volume total de um determinado tipo de bebida em uma determinada seção")
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
	@ApiOperation(value="Busca todas as seções disponíveis e seus tipos de bebidas disponíveis para um possível volume de bebida")
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
	@ApiOperation(value="Busca todas as seções disponíveis para venda de um determinado tipo de bebida")
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
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Seção é obrigatória! -ou- Seção deve ser os números inteiros 1, 2, 3, 4 ou 5!"
											 + " ---ou--- Bebida é obrigatório!"
											 + " ---ou--- Tipo da bebida é obrigatório!"
											 + " ---ou--- Tipo da bebida é obrigatório!"
											 + " ---ou--- Tipo da bebida deve ser o número inteiro 1 para alcoólico ou 2 para não alcoólico!"
											 + " ---ou--- O volume deve ser entre o número inteiro 1 e 500 para bebidas alcoólicas!"
											 + " ---ou--- O volume deve ser entre o número inteiro 1 e 400 para bebidas não alcoólicas!"
											 + " ---ou--- Seção específica foi ocupada por outro tipo de bebida hoje, por favor tente no dia seguinte!"
											 + " ---ou--- Não pode armazenar esse volume pois ultrapassa o limite de 500 para bebidas alcoólicas na seção específica!"
											 + " ---ou--- Não pode armazenar esse volume pois ultrapassa o limite de 400 para bebidas não alcoólicas na seção específica!"),})
	@ApiOperation(value="Armazena uma bebida no estoque em uma determinada seção")
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
	@ApiResponses(value = {@ApiResponse(code = 404, message = "Não existe bebida com o ID específico")})
	@ApiOperation(value="Retira uma bebida do estoque")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<RespostaDTO> remove(@PathVariable Integer id) {
		service.deleteById(id);
		RespostaDTO resposta = new RespostaDTO()
				.setIdBebida(id)
				.setMessage("Bebida retirada do estoque com sucesso!");
		return ResponseEntity.ok().body(resposta);
	}
	
}
