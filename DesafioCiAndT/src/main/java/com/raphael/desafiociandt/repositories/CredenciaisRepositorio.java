package com.raphael.desafiociandt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.raphael.desafiociandt.domain.Credenciais;

@Repository
public interface CredenciaisRepositorio extends JpaRepository<Credenciais, Integer> {

	@Transactional(readOnly = true)
	Credenciais findByEmail(String email);
	
}
