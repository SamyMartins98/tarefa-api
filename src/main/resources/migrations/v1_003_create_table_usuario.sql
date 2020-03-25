CREATE TABLE estudos.usuario
(
  id serial NOT NULL,
  nome character varying(255),
  email character varying(255),
  senha character varying(255),
  lembrete_senha character varying(255),
  data_criacao timestamp without time zone,
  tarefa_id bigint,
  CONSTRAINT usuario_pkey PRIMARY KEY (id)
);
