-- public.usuario definição
-- Drop table
-- DROP TABLE public.usuario;

CREATE TABLE public.usuario (
	usucodigo int4 NOT NULL,
	usunome varchar(50) NOT NULL,
	usulogin varchar(60) NULL,
	ususenha varchar(20) NULL,
	usuemail varchar(60) NULL,
	CONSTRAINT usuario_pkey PRIMARY KEY (usucodigo)
);
CREATE UNIQUE INDEX si_usulogin ON public.usuario USING btree (usulogin);

CREATE TABLE public.produto (
	id serial4 NOT NULL,
	descricao varchar(300) NOT NULL,
	precocusto numeric(10, 2) NULL,
	precovenda numeric(10, 2) NULL,
	CONSTRAINT produto_pkey PRIMARY KEY (id)
);


-- lista de inserts
-- usuarios
INSERT INTO public.usuario
(usucodigo, usunome, usulogin, ususenha, usuemail)
VALUES(1, 'admin', 'admin', '123456', 'admin@email.com');

-- produtos
INSERT INTO public.produto
(id, descricao, precocusto, precovenda)
VALUES(1, 'ARROZ', 10.00, 15.00);
INSERT INTO public.produto
(id, descricao, precocusto, precovenda)
VALUES(2, 'FEIJÃO', 4.00, 7.00);
INSERT INTO public.produto
(id, descricao, precocusto, precovenda)
VALUES(3, 'CARNE DE FRANGO', 5.00, 8.00);

