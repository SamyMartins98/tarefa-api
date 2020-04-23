package br.com.estudos.tarefas.response;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class ResponseApi<T> {
	T data;
	Error error;
	
	
	public ResponseApi<T> data(T data){
		this.data =data;
		return this;
	}
	

	public ResponseApi<T> error(Error error){
		this.error =error;
		return this;
	}

}

@Getter
@Setter
class Error{
	Long codigo;
	String mensagem;
	
}