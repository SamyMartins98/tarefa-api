package br.com.estudos.tarefas.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.estudos.tarefas.model.Tarefa;
import br.com.estudos.tarefas.repository.filters.TarefaFiltro;

@Repository
public interface TarefaRepositoryQuery {
	
	Page<Tarefa> filtrarPage(TarefaFiltro tarefaFilter, Pageable pageable);
	List<Tarefa> filtrar(TarefaFiltro tarefaFilter);

}
