package com.raphael.desafiociandt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.raphael.desafiociandt.domain.Historico;

@Repository
public interface HistoricoRepositorio extends JpaRepository<Historico, Integer> {
	
	@Transactional(readOnly = true)
	List<Historico> findBySecao(Integer secao);

}
