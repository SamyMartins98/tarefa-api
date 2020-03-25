package br.com.estudos.tarefas.factory;

import java.util.ArrayList;
import java.util.List;

import br.com.estudos.tarefas.model.Tarefa;
import br.com.estudos.tarefas.util.DataConverter;
import br.com.estudos.tarefas.vo.RelatorioTarefa;

public class RelatorioTarefaFactory {
	
	public static List<RelatorioTarefa> createListaRelatorioTarefa(List<Tarefa> tarefas){
		List<RelatorioTarefa> lista = new ArrayList<RelatorioTarefa>();
		
		tarefas.forEach(tarefa -> {
			RelatorioTarefa relatorio = new RelatorioTarefa();
			relatorio.setNome(tarefa.getNome());
			relatorio.setDescricao(tarefa.getDescricao());
			relatorio.setPrioridade(tarefa.getSituacao().name());
			relatorio.setDataCriacao(DataConverter.localDateToString(tarefa.getDataCriacao(),"dd/MM/yyyy"));
			relatorio.setDataAlteracao(DataConverter.localDateToString(tarefa.getDataAlteracao(),"dd/MM/yyyy"));
			
			lista.add(relatorio);
		});
		
		return lista;
	}

}
