/**
 * Author:  Senac
 * Created: 23 de mai. de 2025
 */

--TAREFA - 23-05-2025
--Criar a Consulta de Pessoas com os dados abaixo:
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

/* 
Atividade:
Criar a consulta "consultaPessoa"
o resto igual tabela produto
adicionar a classe de pessoa

adicionar na funcao conexao.executaQuery()  a chamada da classe de pessoa
fazer a consulta semelhante a produtos
Depois configurar cadastro de usuario, produto e pessoas, sera visto nas proximas aulas
*/