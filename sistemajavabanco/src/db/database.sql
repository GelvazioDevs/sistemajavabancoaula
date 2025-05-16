/**
 * Author:  Gelvazio Camargo
 */
CREATE TABLE usuario (
    usucodigo int4 NOT NULL,
    usunome varchar(50) NOT NULL,
    usulogin varchar(60) NULL,
    ususenha varchar(20) NULL,
    usuemail varchar(60) NULL,
    CONSTRAINT usuario_pkey PRIMARY KEY (usucodigo)
);

CREATE UNIQUE INDEX si_usulogin ON public.usuario USING btree (usulogin);

INSERT INTO public.usuario(usunome, usulogin, ususenha, usuemail)
VALUES('Gelvazio Camargo', 'gelvazio', '123456', 'gelvazio@email.com');

INSERT INTO public.usuario(usunome, usulogin, ususenha, usuemail)
VALUES('Lorenzo Cellarius', 'lorenzo', '123456', 'lorenzo@email.com');

INSERT INTO public.usuario(usunome, usulogin, ususenha, usuemail)
VALUES('Maico Cellarius', 'maico', '123456', 'maico@email.com');

INSERT INTO public.usuario(usunome, usulogin, ususenha, usuemail)
VALUES('Jessica Moratelli', 'jessica', '123456', 'jessica@email.com');

INSERT INTO public.usuario(usunome, usulogin, ususenha, usuemail)
VALUES('Aldo Rosario', 'aldo', '123456', 'aldo@email.com');

INSERT INTO public.usuario(usunome, usulogin, ususenha, usuemail)
VALUES('Taila Cellarius', 'taila', '123456', 'taila@email.com');


--Pessoa
CREATE TABLE pessoa (
    codigo serial NOT NULL,
    nome varchar(50) NOT NULL,
    endereco varchar(60) NULL,
    cpf varchar(20) NULL,
    CONSTRAINT pessoa_pkey PRIMARY KEY (codigo)
);

-- pessoas 
INSERT INTO public.pessoa
(nome, endereco, cpf)
VALUES('Gelvazio', 'Estrada São Jose, Santana, 540, Rio do Sul, SC', '061.023.145-77');

INSERT INTO public.pessoa
(nome, endereco, cpf)
VALUES('Jessica', 'Estrada São Jose, Santana, 540, Rio do Sul, SC', '061.023.145-77');

INSERT INTO public.pessoa
(nome, endereco, cpf)
VALUES('Debora', 'Estrada São Jose, Santana, 540, Rio do Sul, SC', '061.023.145-77');

INSERT INTO public.pessoa
(nome, endereco, cpf)
VALUES('Taila', 'Estrada São Jose, Santana, 540, Rio do Sul, SC', '061.023.145-77');

INSERT INTO public.pessoa
(nome, endereco, cpf)
VALUES('Maico', 'Estrada São Jose, Santana, 540, Rio do Sul, SC', '061.023.145-77');

INSERT INTO public.pessoa
(nome, endereco, cpf)
VALUES('Aldo', 'Estrada São Jose, Santana, 540, Rio do Sul, SC', '061.023.145-77');

-- define se uma pessoa tambem e vendedor
alter TABLE pessoa add column fg_vendedor int not null default 0;

