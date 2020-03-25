package br.com.estudos.tarefas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tarefa_item", schema = "estudos")
public class TarefaItem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="tarefaItemIdSeq", sequenceName="estudos.tarefa_id_seq", allocationSize =1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tarefaItemIdSeq")
	private Long id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="prioridade")
	private String prioridade;

}
