insert into estudos.usuario (select nextval('estudos.usuario_id_seq'), 'Samanta', 'samymartins98@gmail.com','1234','teste',current_date, null);

insert into estudos.tarefa (select nextval('estudos.tarefa_id_seq'), 'teste','teste da api', current_date, null, null, 'CRIADO', 1);