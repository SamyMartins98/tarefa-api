package br.com.estudos.tarefas.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelatorioTarefa {
	private String prioridade;
	private String dataCriacao;
	private String dataAlteracao;
	private String descricao;
	private String nome;
}
