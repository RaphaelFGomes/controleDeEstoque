package com.raphael.springbootionic.services;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raphael.springbootionic.domain.Estoque;
import com.raphael.springbootionic.domain.Historico;
import com.raphael.springbootionic.domain.enums.TipoBebida;
import com.raphael.springbootionic.dto.HistoricoDTO;
import com.raphael.springbootionic.repositories.HistoricoRepositorio;

@Service
public class HistoricoService {

	@Autowired
	private HistoricoRepositorio repo;

	@Transactional
	public Historico insert(Estoque estoque, String tipoRequisicao) {
		Date currentDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
		
		Historico historico = new Historico()
				.setTipoRequisicao(tipoRequisicao)
				.setHorario(formatter.format(currentDate))
				.setHorarioLocalDate(LocalDate.now())
				.setResponsavel(estoque.getResponsavel())
				.setVolume(estoque.getVolume())
				.setSecao(estoque.getSecao())
				.setNomeBebida(estoque.getNomeBebida())
				.setMarcaBebida(estoque.getMarcaBebida())
				.setTipoBebida(estoque.getTipoBebida());
		
		return repo.save(historico);
	}
	
	public Page<Historico> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pagerequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pagerequest);
	}	
	
	public Page<HistoricoDTO> fromHistToDTO(Page<Historico> listHistorico) {
		Page<HistoricoDTO> listDto = listHistorico
				.map(obj -> new HistoricoDTO()
							.setHorario(obj.getHorario())
							.setNomeBebida(obj.getNomeBebida())
							.setMarcaBebida(obj.getMarcaBebida())
							.setTipoBebida(obj.getTipoBebida())
							.setTipoBebidaDescricao(obj.getTipoBebida() == 1 ? TipoBebida.toEnum(1).getDescricao()	: TipoBebida.toEnum(2).getDescricao())
							.setTipoRequisicao(obj.getTipoRequisicao()).setVolume(obj.getVolume()).setSecao(obj.getSecao())
							.setResponsavel(obj.getResponsavel()));

		return listDto;
	}

}
