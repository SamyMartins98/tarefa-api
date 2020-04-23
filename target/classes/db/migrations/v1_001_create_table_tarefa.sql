create table estudos.tarefa(
	id serial not null,
	nm_tarefa character varying (250) not null, 
	ds_tarefa character varying (250) not null, 
	data_criacao timestamp without time zone,
	data_alteracao timestamp without time zone,
	data_encerramento timestamp without time zone,
	situacao character varying(20),
	usuario_id bigint,
	CONSTRAINT tarefas_pk PRIMARY KEY (id)
);