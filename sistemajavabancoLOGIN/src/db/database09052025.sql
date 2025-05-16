/**
 * Author:  Senac
 * Created: 9 de mai. de 2025
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


-- inserts 
INSERT INTO public.usuario
(usucodigo, usunome, usulogin, ususenha, usuemail)
VALUES(1, 'admin', 'admin', '123456', 'admin@email.com');]

INSERT INTO public.usuario
(usucodigo, usunome, usulogin, ususenha, usuemail)
VALUES(2, 'loja', 'loja', '123456', 'loja@email.com');]

INSERT INTO public.usuario
(usucodigo, usunome, usulogin, ususenha, usuemail)
VALUES(3, 'caixa', 'caixa', '123456', 'caixa@email.com');]



