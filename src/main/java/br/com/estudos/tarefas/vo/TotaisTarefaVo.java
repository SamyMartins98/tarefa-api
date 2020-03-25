package br.com.estudos.tarefas.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TotaisTarefaVo {
	
	private long total;
	private long totalCriados;
	private long totalAlterados;
	private long totalEncerrados;
}
