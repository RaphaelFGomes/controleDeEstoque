package com.raphael.springbootionic.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raphael.springbootionic.domain.Estoque;
import com.raphael.springbootionic.domain.enums.TipoBebida;
import com.raphael.springbootionic.dto.BebidaDTO;
import com.raphael.springbootionic.dto.EstoqueDTO;
import com.raphael.springbootionic.dto.SecaoDisponivelDTO;
import com.raphael.springbootionic.exceptions.ObjectNotFoundException;
import com.raphael.springbootionic.repositories.EstoqueRepositorio;

@Service
public class EstoqueService {

	@Autowired
	private EstoqueRepositorio repo;
	
	@Autowired
	private HistoricoService historicoService;

	@Transactional
	public Estoque insert(Estoque obj) {
		historicoService.insert(obj, "Entrada");
		return repo.save(obj);
	}

	@Transactional
	public void deleteById(Integer id) {
		try {
			Estoque estoque = this.consultBebibaPorId(id);
			repo.deleteById(id);
			historicoService.insert(estoque, "Saída");
		}catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException("Não existe bebida com esse ID: " + id + "!");
		}
	}
	
	public Estoque consultBebibaPorId(Integer id) {
		Optional<Estoque> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Não existe bebida com esse ID: " + id + "!"));
	}
	
	public List<Estoque> consultaEstoquePorSecao(Integer secao) {
		List<Estoque> listEstoque = repo.findBySecao(secao);
		if (listEstoque == null || listEstoque.size() == 0) {
			throw new ObjectNotFoundException("Não existe bebida cadastrada para essa seção: " + secao + "!");
		}
		return listEstoque;
	}
	
	public int consultaVolumeTotalEstoquePorTipoBebida(Integer tipoBebida) {
		int volumeTotal = 0;
		try {
			volumeTotal = repo.findVolumeTotalEstoqueByTipoBebida(tipoBebida);
		} catch (AopInvocationException aie) {
			volumeTotal = 0;
		}
		return volumeTotal;
	 }
	
	public int consultaVolumeByTipoBebidaAndSecao(Integer tipoBebida, Integer secao) {
		int volumeSecao = 0;
		try {
			volumeSecao = repo.findVolumeByTipoBebidaAndSecao(tipoBebida, secao);
		} catch (AopInvocationException aie) {
			volumeSecao = 0;
		}
		return volumeSecao;
	 }
	
	public List<SecaoDisponivelDTO> consultaSecoesDisponiveisPorVolume(Integer volume) {
		List<SecaoDisponivelDTO> secoesDisponiveis = new ArrayList<>();
		SecaoDisponivelDTO secaoDisponivelDTO = null;

		for (int i = 1; i <= 5; i++) {
			if (volume >= 0 && volume <= 500) {
				int volumeSecao = 0;
				try {
					volumeSecao = repo.findVolumeBySecao(i);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				if (volumeSecao == 0) {
					secaoDisponivelDTO = new SecaoDisponivelDTO()
							.setSecao(i)
							.setVolumeAtual(0)
							.setVolumeDisponivelString("500 para bebida Alcoólica e 400 para Não Alcoólica")
							.setTipoBebida(3)
							.setTipoBebidaDescricao(TipoBebida.toEnum(3).getDescricao());
					secoesDisponiveis.add(secaoDisponivelDTO);
				} else {
					int tipoBebidaSecao = 0;
					try {
						tipoBebidaSecao = repo.findTipoBebidaBySecao(i);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					String tipoBebidaDescricao = tipoBebidaSecao == 1 ? TipoBebida.toEnum(1).getDescricao()
							: TipoBebida.toEnum(2).getDescricao();
					// Alcoólica
					if (tipoBebidaSecao == 1 && (volumeSecao + volume) <= 500) {
						secaoDisponivelDTO = new SecaoDisponivelDTO()
								.setSecao(i)
								.setVolumeAtual(volumeSecao)
								.setVolumeDisponivel(500 - volumeSecao)
								.setTipoBebida(tipoBebidaSecao)
								.setTipoBebidaDescricao(tipoBebidaDescricao);
						secoesDisponiveis.add(secaoDisponivelDTO);
					}
					// Não Alcoólica
					else {
						if ((volumeSecao + volume) <= 400) {
							secaoDisponivelDTO = new SecaoDisponivelDTO()
									.setSecao(i).setVolumeAtual(volumeSecao)
									.setVolumeDisponivel(400 - volumeSecao)
									.setTipoBebida(tipoBebidaSecao)
									.setTipoBebidaDescricao(tipoBebidaDescricao);
							secoesDisponiveis.add(secaoDisponivelDTO);
						}
					}
				}
			}
		}
		return secoesDisponiveis;
	}
	
	public List<SecaoDisponivelDTO> consultaSecoesDisponiveisVendaPorTipoBebida(Integer tipoBebida) {
		List<SecaoDisponivelDTO> secoesDisponiveis = new ArrayList<>();
		
		SecaoDisponivelDTO secaoDisponivelDTO = null;
		
		for (int i = 1; i <= 5; i++) {
		try {
			int volumeSecao = repo.findVolumeByTipoBebidaAndSecao(tipoBebida, i);
			// Alcoólica
			if (tipoBebida == 1 && volumeSecao > 0) {
				secaoDisponivelDTO = new SecaoDisponivelDTO()
						.setSecao(i)
						.setVolumeAtual(volumeSecao);
				secoesDisponiveis.add(secaoDisponivelDTO);
			}
			// Não Alcoólica
			else {
				if (volumeSecao > 0) {
					secaoDisponivelDTO = new SecaoDisponivelDTO()
							.setSecao(i)
							.setVolumeAtual(volumeSecao);
					secoesDisponiveis.add(secaoDisponivelDTO);
				} 
			}
		} catch (AopInvocationException aie) {
			System.out.println(aie.getMessage());
			}
		}
		return secoesDisponiveis;
	 }

	public Estoque fromDTO(EstoqueDTO objDTO) {
		return new Estoque().setId(null)
				.setHorario(LocalDate.now())
				.setMarcaBebida(objDTO.getBebida().getMarca())
				.setNomeBebida(objDTO.getBebida().getNome())
				.setTipoBebida(objDTO.getBebida().getTipo())
				.setSecao(objDTO.getSecao())
				.setVolume(objDTO.getVolume())
				.setResponsavel(objDTO.getResponsavel());
	}
	
	public EstoqueDTO fromDomain(Estoque objDTO) {
		BebidaDTO bebida = new BebidaDTO().setNome(objDTO.getNomeBebida())
				.setMarca(objDTO.getMarcaBebida())
				.setTipo(objDTO.getTipoBebida())
				.setTipoDescricao(objDTO.getTipoBebida() == 1 ? TipoBebida.toEnum(1).getDescricao() : TipoBebida.toEnum(2).getDescricao());
		return new EstoqueDTO().setBebida(bebida)
				.setSecao(objDTO.getSecao())
				.setVolume(objDTO.getVolume())
				.setResponsavel(objDTO.getResponsavel());
	}
	
	public List<EstoqueDTO> fromListEstoque(List<Estoque> listEstoque) {
		List<EstoqueDTO> listEstoqueDTO = new ArrayList<>();
		
		for (Estoque estoque : listEstoque) {
			BebidaDTO bebidaDTO = new BebidaDTO()
					.setNome(estoque.getNomeBebida())
					.setMarca(estoque.getMarcaBebida())
					.setTipo(estoque.getTipoBebida())
					.setTipoDescricao(estoque.getTipoBebida() == 1 ? TipoBebida.toEnum(1).getDescricao() : TipoBebida.toEnum(2).getDescricao());
			
			EstoqueDTO estoqueDTO = new EstoqueDTO()
					.setSecao(estoque.getSecao())
					.setBebida(bebidaDTO)
					.setVolume(estoque.getVolume())
					.setResponsavel(estoque.getResponsavel());
			
			listEstoqueDTO.add(estoqueDTO);
		}
		return listEstoqueDTO;
	}
	
}
