package br.com.estudos.tarefas.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.estudos.tarefas.enuns.SituacaoTarefa;
import br.com.estudos.tarefas.exception.TarefaException;
import br.com.estudos.tarefas.model.Tarefa;
import br.com.estudos.tarefas.repository.TarefaRepository;
import br.com.estudos.tarefas.repository.TarefaRepositoryQuery;
import br.com.estudos.tarefas.repository.filters.TarefaFiltro;
import br.com.estudos.tarefas.vo.TotaisTarefaVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TarefaService {

	private TarefaRepository tarefaRepository;
	private TarefaRepositoryQuery tarefaRepositoryQuery;
	
	@Autowired
	public TarefaService(TarefaRepository tarefaRepository, TarefaRepositoryQuery tarefaRepositoryQuery){
		this.tarefaRepository = tarefaRepository;
		this.tarefaRepositoryQuery = tarefaRepositoryQuery;
	}
	
	public Tarefa salvarTarefa(Tarefa tarefa) {
		log.info("Salvando nova tarefa");
		return tarefaRepository.save(tarefa);
	}
	
	public Tarefa atualizarTarefa(Long tarefaId, Tarefa tarefa) {
		Tarefa tarefaAtualizar = tarefaRepository.findById(tarefaId).orElseThrow(()-> new  TarefaException("Tarefa não foi localizada."));
		tarefaAtualizar.setDescricao(tarefa.getDescricao());
		tarefaAtualizar.setNome(tarefa.getNome());
		
		return tarefaRepository.save(tarefaAtualizar);
	}
	
	public Optional<Tarefa> listarTarefaPorId(Long id) {
		return tarefaRepository.findById(id);
	}
	
	public List<Tarefa> listarTarefas(){
		return tarefaRepository.findAll();
	}
	
	public void deletarTarefa(Long tarefaId) {
		tarefaRepository.deleteById(tarefaId);
	}
	
	public Page<Tarefa> findAllByFiltro(TarefaFiltro tarefaFilter, Pageable pageable){
		log.info("Consultando faturas. page: filtro: {} ", tarefaFilter.toString());
		return tarefaRepositoryQuery.filtrarPage(tarefaFilter, pageable);
    }
	
	public List<Tarefa> getDadosRelatorio(TarefaFiltro filtro){
		log.info("Consultando tarefas. filtro: {}"+filtro.toString());
		return tarefaRepositoryQuery.filtrar(filtro);
	}
	
	public TotaisTarefaVo findTotaisTarefas() {
		return  TotaisTarefaVo.builder()
				.total(tarefaRepository.count())
				.totalCriados(tarefaRepository.countBySituacao(SituacaoTarefa.CRIADO))
				.totalAlterados(tarefaRepository.countBySituacao(SituacaoTarefa.ALTERADO))
				.totalEncerrados(tarefaRepository.countBySituacao(SituacaoTarefa.ENCERRADO))
				.build();
	}
}
