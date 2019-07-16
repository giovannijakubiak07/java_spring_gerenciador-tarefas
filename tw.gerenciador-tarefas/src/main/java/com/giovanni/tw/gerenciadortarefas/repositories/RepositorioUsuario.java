package com.giovanni.tw.gerenciadortarefas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giovanni.tw.gerenciadortarefas.models.Usuario;

public interface RepositorioUsuario extends JpaRepository<Usuario , Long> {
	
	
	Usuario findByEmail(String email); //spring gera automaticamente a busca de usuario por email utilizando o padrao findBy
	
	
}
