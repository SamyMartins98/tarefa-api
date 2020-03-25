package br.com.estudos.tarefas.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.estudos.tarefas.model.Tarefa;
import br.com.estudos.tarefas.repository.filters.TarefaFiltro;

@Component
public class TarefaRepositoryQueryImpl implements TarefaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Tarefa> filtrarPage(TarefaFiltro tarefaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Tarefa> criteria = builder.createQuery(Tarefa.class);
		Root<Tarefa> root = criteria.from(Tarefa.class);

		criteria.where(criarRestricoes(tarefaFilter, builder, root));
		criteria.orderBy(builder.desc(root.get("dataAlteracao")),builder.desc(root.get("dataCriacao")));
		
		TypedQuery<Tarefa> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(tarefaFilter));
	}

	@Override
	public List<Tarefa> filtrar(TarefaFiltro tarefaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Tarefa> criteria = builder.createQuery(Tarefa.class);
		Root<Tarefa> root = criteria.from(Tarefa.class);
		
		criteria.where(criarRestricoes(tarefaFilter, builder, root));
		criteria.orderBy(builder.desc(root.get("dataAlteracao")), builder.desc(root.get("dataCriacao")));
		
		TypedQuery<Tarefa> query = manager.createQuery(criteria);
				
		return query.getResultList();
	}
	
	private Predicate[] criarRestricoes(TarefaFiltro tarefaFilter, CriteriaBuilder builder,	Root<Tarefa> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(tarefaFilter.getSituacao())) {
			predicates.add(builder.equal(root.get("situacao"), tarefaFilter.getSituacao()));
		}
		
		if (!StringUtils.isEmpty(tarefaFilter.getDataCriacao())) {
			predicates.add(builder.equal(root.get(tarefaFilter.getTipoConsulta().getCampo()), tarefaFilter.getDataCriacao()));
		}
		
		if (!StringUtils.isEmpty(tarefaFilter.getDataAlteracao())) {
			predicates.add(builder.equal(root.get(tarefaFilter.getTipoConsulta().getCampo()), tarefaFilter.getDataAlteracao()));
		}
		
		
		if (!StringUtils.isEmpty(tarefaFilter.getDataEncerrado()) && !StringUtils.isEmpty(tarefaFilter.getDataEncerrado())) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(tarefaFilter.getTipoConsulta().getCampo()), tarefaFilter.getDataEncerrado()));
			predicates.add(builder.lessThanOrEqualTo(root.get(tarefaFilter.getTipoConsulta().getCampo()), tarefaFilter.getDataEncerrado().plusDays(1)));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private Long total(TarefaFiltro tarefaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Tarefa> root = criteria.from(Tarefa.class);
		
		Predicate[] predicates = criarRestricoes(tarefaFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	

}
