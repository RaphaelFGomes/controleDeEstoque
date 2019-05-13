package com.raphael.springbootionic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raphael.springbootionic.domain.Historico;

@Repository
public interface HistoricoRepositorio extends JpaRepository<Historico, Integer> {

}
