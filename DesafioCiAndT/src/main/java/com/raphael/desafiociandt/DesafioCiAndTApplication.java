package com.raphael.desafiociandt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.raphael.desafiociandt.domain.Credenciais;
import com.raphael.desafiociandt.domain.enums.Perfil;
import com.raphael.desafiociandt.repositories.CredenciaisRepositorio;

@SpringBootApplication
public class DesafioCiAndTApplication implements CommandLineRunner {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private CredenciaisRepositorio credenciaisRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(DesafioCiAndTApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {	
		Credenciais credenciais = new Credenciais()
				.setEmail("raphaelg9@gmail.com")
				.setSenha(pe.encode("teste"))
				.addPerfil(Perfil.ADMIN);
		
		credenciaisRepositorio.save(credenciais);
	}

}
