package com.giovanni.tw.gerenciadortarefas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giovanni.tw.gerenciadortarefas.models.Tarefa;

public interface RepositorioTarefas extends JpaRepository<Tarefa, Long> {
	
	
}
