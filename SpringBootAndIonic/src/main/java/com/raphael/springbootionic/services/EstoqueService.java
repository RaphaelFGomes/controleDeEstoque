package com.raphael.springbootionic.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Transactional
	public Estoque insert(Estoque obj) {
		return repo.save(obj);
	}

	@Transactional
	public void deleteByIdAndSecao(Integer id, Integer secao) {
		if (repo.deleteByIdAndSecao(id, secao) == 0) {
			throw new ObjectNotFoundException("Não existe o ID dessa bebida na seção: " + secao + "!");
		}
	}
	
	public Estoque consultBebibaPorIdESecao(Integer id, Integer secao) {
		Estoque obj = repo.findByIdAndSecao(id, secao);
		if (obj == null) {
			throw new ObjectNotFoundException("Não existe o ID dessa bebida na seção: " + secao + "!");
		}
		return obj;
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
	
	public List<SecaoDisponivelDTO> consultaSecoesDisponiveisPorTipoBebidaSecao(Integer tipoBebida, Integer volume) {
		List<SecaoDisponivelDTO> secoesDisponiveis = new ArrayList<>();
		
		SecaoDisponivelDTO secaoDisponivelDTO = null;
		
		for (int i = 1; i <= 5; i++) {
		try {
			int volumeSecao1 = repo.findVolumeByTipoBebidaAndSecao(tipoBebida, i);
			// Alcoólica
			if (tipoBebida == 1 && (volumeSecao1 + volume) <= 500) {
				secaoDisponivelDTO = new SecaoDisponivelDTO()
						.setSecao(i)
						.setVolumeAtual(volumeSecao1)
						.setVolumeDisponivel(500 - volumeSecao1);
				secoesDisponiveis.add(secaoDisponivelDTO);
			}
			// Não Alcoólica
			else {
				if ((volumeSecao1 + volume) <= 400) {
					secaoDisponivelDTO = new SecaoDisponivelDTO()
							.setSecao(i)
							.setVolumeAtual(volumeSecao1)
							.setVolumeDisponivel(400 - volumeSecao1);
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

	/*
	 * public Client find(Integer id) { Optional<Client> obj = repo.findById(id);
	 * return obj.orElseThrow(() -> new ObjectNotFoundException(
	 * "Object not found! Id: " + id + ", Type: " + Client.class.getName())); }
	 * 
	 * 
	 * @Transactional public Client insert(Client obj) { obj.setId(null); obj =
	 * repo.save(obj); addressRepository.saveAll(obj.getAddresses()); return obj; }
	 */
	/*
	 * public Client update(Client obj) { Client newObj = find(obj.getId());
	 * updateData(newObj, obj); return repo.save(newObj); }
	 * 
	 * public void delete(Integer id) { find(id); try { repo.deleteById(id); } catch
	 * (DataIntegrityViolationException e) { throw new
	 * DataIntegrityException("Is not possible delete this client that contains requests!"
	 * ); }
	 * 
	 * }
	 * 
	 * public List<Client> findAll() { return repo.findAll(); }
	 * 
	 * public Page<Client> findPage(Integer page, Integer linesPerPage, String
	 * direction, String orderBy) { PageRequest pagerequest = PageRequest.of(page,
	 * linesPerPage, Direction.valueOf(direction), orderBy); return
	 * repo.findAll(pagerequest); }
	 * 
	 * public Client fromDTO(ClientDTO objDTO) { return new Client(objDTO.getId(),
	 * objDTO.getName(), objDTO.getEmail(), null, null); }
	 * 
	 * public Client fromDTO(ClientNewDTO objDTO) { Client cli = new Client(null,
	 * objDTO.getName(), objDTO.getEmail(), objDTO.getCode(),
	 * ClientType.toEnum(objDTO.getType())); City cit = new City(objDTO.getCityId(),
	 * null, null); Address addr = new Address(null, objDTO.getPublicPlace(),
	 * objDTO.getNumber(), objDTO.getComplement(), objDTO.getNeighborhood(),
	 * objDTO.getZipCode(), cli, cit); cli.getAddresses().add(addr);
	 * cli.getPhoneNumbers().add(objDTO.getPhone1()); if (objDTO.getPhone2() !=
	 * null) { cli.getPhoneNumbers().add(objDTO.getPhone2()); }
	 * 
	 * if (objDTO.getPhone3() != null) {
	 * cli.getPhoneNumbers().add(objDTO.getPhone3()); }
	 * 
	 * return cli; }
	 * 
	 * private void updateData(Client newObj, Client obj) {
	 * newObj.setName(obj.getName()); newObj.setEmail(obj.getEmail()); }
	 */
}
