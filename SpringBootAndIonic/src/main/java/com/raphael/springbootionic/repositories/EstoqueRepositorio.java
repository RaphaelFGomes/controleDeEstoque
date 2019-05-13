package com.raphael.springbootionic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.raphael.springbootionic.domain.Estoque;

@Repository
public interface EstoqueRepositorio extends JpaRepository<Estoque, Integer> {

	@Transactional(readOnly = true)
	List<Estoque> findBySecao(Integer secao);
	
	@Transactional(readOnly = true)
	Estoque findByIdAndSecao(Integer id, Integer secao);
	
	@Transactional
	int deleteByIdAndSecao(Integer id, Integer secao);
	
	@Query("SELECT SUM(volume) from Estoque e WHERE e.tipoBebida = :tipo")
	int findVolumeTotalEstoqueByTipoBebida(@Param("tipo") Integer tipo);
	
	@Query("SELECT SUM(volume) from Estoque e WHERE e.tipoBebida = :tipo AND e.secao = :secao")
	int findVolumeByTipoBebidaAndSecao(@Param("tipo") Integer tipo, @Param("secao") Integer secao);

}
