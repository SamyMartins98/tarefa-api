package br.com.estudos.tarefas.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "usuario", schema = "estudos")
public class Usuario  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="usuarioIdSeq", sequenceName="estudos.usuario_id_seq",  allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="usuarioIdSeq")
	private Long id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="email")
	private String email;
	
	@Column(name="senha")
	private String senha;
	
	@Column(name="lembrete_senha")
	private String lembreteSenha;
	
	@Column(name="data_criacao")
	private Date dataCriacao;
	
	@ManyToOne
	@JoinColumn(name="tarefa_id")
	@JsonProperty(value = "tarefaId")
	private Tarefa tarefa;

}
