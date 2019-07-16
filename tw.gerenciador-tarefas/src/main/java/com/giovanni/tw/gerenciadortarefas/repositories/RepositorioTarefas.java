package com.giovanni.tw.gerenciadortarefas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.giovanni.tw.gerenciadortarefas.models.Tarefa;

public interface RepositorioTarefas extends JpaRepository<Tarefa, Long> {
	
	@Query("SELECT t FROM Tarefa t WHERE t.usuario.email = :emailUsuario") //jpql java persistence query language
	List<Tarefa> carregarTarefasPorUsuario(@Param("emailUsuario") String email);
	
}
