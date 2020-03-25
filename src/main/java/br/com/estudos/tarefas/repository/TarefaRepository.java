package br.com.estudos.tarefas.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estudos.tarefas.enuns.SituacaoTarefa;
import br.com.estudos.tarefas.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long>{
	
	Optional<Tarefa> findBySituacao(SituacaoTarefa prioridade);
	
	List<Tarefa> findAll();
	
	long countBySituacao(SituacaoTarefa prioridade);
	
	boolean existsBySituacao(SituacaoTarefa prioridade);
	
}
