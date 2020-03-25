package br.com.estudos.tarefas.enuns;

import lombok.Getter;

@Getter
public enum TipoConsulta {
    NUMERO("numero"),
    DT_CRIACAO("dataCriacao"),
    DT_ALTERACAO("dataAlteracao"),
    DT_ENCERRADO("dataEncerrado"),
    NAO_ESPECIFICADO("");
    
    private String campo;
	
	private TipoConsulta(String campo) {
		this.campo = campo;
	}
    
	
	public static TipoConsulta findTipoConsulta(String name) {
		for (TipoConsulta tipo : TipoConsulta.values()) {
			 if(tipo.name().equalsIgnoreCase(name)) {
				 return tipo;
			 }
		}
		
		return NAO_ESPECIFICADO;
	}
	
    
}
