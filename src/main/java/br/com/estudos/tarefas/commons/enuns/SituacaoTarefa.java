package br.com.estudos.tarefas.commons.enuns;

public enum SituacaoTarefa {
	CRIADO,
	ALTERADO,
	ENCERRADO,
	TODOS;
	
	public static SituacaoTarefa getSituacaoTarefa(String situacaoTexto) {
		for(SituacaoTarefa situacao : SituacaoTarefa.values()) {
			if(situacao.name().equals(situacaoTexto)) {
				return situacao;
			}
		}
		
		return TODOS;
	}

}
