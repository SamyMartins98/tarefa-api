package br.com.estudos.tarefas.exception;

public class TarefaException  extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TarefaException(String msg, Throwable ex) {
       super(msg, ex);		
	}
	
	public TarefaException(String msg) {
	       super(msg);		
	}

}
