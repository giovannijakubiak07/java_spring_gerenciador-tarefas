package com.giovanni.tw.gerenciadortarefas.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.giovanni.tw.gerenciadortarefas.models.Usuario;
import com.giovanni.tw.gerenciadortarefas.repositories.RepositorioTarefas;
import com.giovanni.tw.gerenciadortarefas.repositories.RepositorioUsuario;

@Service
public class ServicoUsuario {
	
	@Autowired
	private RepositorioUsuario repositorioUsuario; //injecao de dependencia
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	public Usuario encontrarPorEmail(String email) {
		
		return repositorioUsuario.findByEmail(email);
		
	}
	
	public void salvar(Usuario usuario) {
		
		//hash unidirecional
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		repositorioUsuario.save(usuario);
		
	}
}
