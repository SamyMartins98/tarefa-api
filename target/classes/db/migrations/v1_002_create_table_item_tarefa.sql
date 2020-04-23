create table estudos.item_tarefa(
	id serial not null,
	nome character varying (250) not null,
	prioridade character varying (20), 
	CONSTRAINT item_tarefas_pk PRIMARY KEY (id)
);