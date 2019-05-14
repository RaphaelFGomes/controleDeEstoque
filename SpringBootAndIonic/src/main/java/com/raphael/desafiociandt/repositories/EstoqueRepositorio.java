package com.raphael.desafiociandt.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.raphael.desafiociandt.domain.Estoque;

@Repository
public interface EstoqueRepositorio extends JpaRepository<Estoque, Integer> {

	@Transactional(readOnly = true)
	List<Estoque> findBySecao(Integer secao);

	@Transactional(readOnly = true)
	Optional<Estoque> findById(Integer id);

	@Transactional
	void deleteById(Integer id);

	@Transactional(readOnly = true)
	@Query("SELECT SUM(volume) from Estoque e WHERE e.tipoBebida = :tipo")
	int findVolumeTotalEstoqueByTipoBebida(@Param("tipo") Integer tipo);
	
	@Transactional(readOnly = true)
	@Query("SELECT SUM(volume) from Estoque e WHERE e.secao = :secao")
	int findVolumeBySecao(@Param("secao") Integer secao);

	@Transactional(readOnly = true)
	@Query("SELECT SUM(volume) from Estoque e WHERE e.tipoBebida = :tipo AND e.secao = :secao")
	int findVolumeByTipoBebidaAndSecao(@Param("tipo") Integer tipo, @Param("secao") Integer secao);
	
	@Transactional(readOnly = true)
	@Query("SELECT tipoBebida from Estoque e WHERE e.secao = :secao")
	int findTipoBebidaBySecao(@Param("secao") Integer secao);

}
