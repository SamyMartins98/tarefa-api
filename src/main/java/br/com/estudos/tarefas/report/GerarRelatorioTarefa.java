package br.com.estudos.tarefas.report;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.estudos.tarefas.factory.RelatorioTarefaFactory;
import br.com.estudos.tarefas.model.Tarefa;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class GerarRelatorioTarefa extends Relatorio{
	
	public byte[] toPDF(List<Tarefa> tarefas) {
		return gerarPDF("/relatorios/relatorio-tarefa.jasper", null, new JRBeanCollectionDataSource(RelatorioTarefaFactory.createListaRelatorioTarefa(tarefas)));
	}

	public byte[] toXLS(List<Tarefa> tarefas) {
		return gerarPDF("/relatorios/relatorio-tarefa.jasper", null, new JRBeanCollectionDataSource(RelatorioTarefaFactory.createListaRelatorioTarefa(tarefas)));
	}
}
