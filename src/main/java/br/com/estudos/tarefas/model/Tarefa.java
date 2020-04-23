package br.com.estudos.tarefas.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.estudos.tarefas.commons.enuns.SituacaoTarefa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tarefa", schema = "estudos")
public class Tarefa implements Serializable {
  	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "tarefaIdSeq", sequenceName = "estudos.tarefa_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tarefaIdSeq")
	private Long id;
	
	@Column(name="nm_tarefa")
	private String nome;
	
	@Column(name="ds_tarefa")
	private String descricao;
	
	@Column(name = "data_criacao")
	private LocalDate dataCriacao;
	
	@Column(name = "data_alteracao")
	private LocalDate dataAlteracao;
	
	@Column(name = "data_encerramento")
	private LocalDate dataEncerramento;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "situacao")
	private SituacaoTarefa situacao;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tarefa_id")
	private List<TarefaItem> items;
	
	/*@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	private Usuario usuarioId;*/
	
}
