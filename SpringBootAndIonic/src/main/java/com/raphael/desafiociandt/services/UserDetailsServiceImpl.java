package com.raphael.desafiociandt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.raphael.desafiociandt.domain.Credenciais;
import com.raphael.desafiociandt.repositories.CredenciaisRepositorio;
import com.raphael.desafiociandt.security.UserSpringSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CredenciaisRepositorio credenciaisRepositorio;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Credenciais credenciais = credenciaisRepositorio.findByEmail(email);
		if (credenciais == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSpringSecurity(credenciais.getId(), credenciais.getEmail(), credenciais.getSenha(), credenciais.getPerfis());
	}
}
