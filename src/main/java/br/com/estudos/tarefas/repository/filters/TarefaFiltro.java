package br.com.estudos.tarefas.repository.filters;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.estudos.tarefas.enuns.SituacaoTarefa;
import br.com.estudos.tarefas.enuns.TipoConsulta;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class TarefaFiltro {
	
	private SituacaoTarefa situacao;
	
	private TipoConsulta tipoConsulta;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataCriacao;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataAlteracao;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataEncerrado;

}
