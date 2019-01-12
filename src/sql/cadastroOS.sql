create database cadastroOS;

use cadastroOS;

create table if not exists tbl_usuario 
(
	id int not null primary key auto_increment,
    nome varchar(50) not null, 
    dataNasc Date not null,
    cpf varchar(15) not null,
	login varchar(20) not null unique,
	senha varchar(15) not null unique,
	perfil varchar(25) not null
);

insert into tbl_usuario (nome, dataNasc, cpf, login, senha, perfil ) 
values('Cristiano Idelfonso da Silva','1990-03-27','12345678900','admin','admin','Admin');


create table if not exists tbl_cliente
(
	idCliente integer not null primary key auto_increment,
    nome varchar(100) not null,
    dataNasc date,
    cpf varchar(15) not null,
    rg varchar(15) not null,
    telefone varchar(20),
    rua varchar(50) not null,
    numero varchar(5) not null,
    bairro varchar(50) not null,
    cidade varchar(100) not null,
    estado varchar(5) not null,
    cep varchar(10) not null
);

alter table tbl_cliente modify estado varchar(5) not null;
alter table tbl_cliente modify dataNasc date not null;

create table if not exists tbl_os
(
	idOS integer not null primary key auto_increment,
    idUsuario int not null,
    nomeUsuario varchar(50),
    idCliente integer not null,
    dataOS datetime default current_timestamp,
    tipo varchar(20) not null,
    situacao varchar(40) not null,
    produto varchar(100),
    descricao varchar(500),
    valor double default 0.00,
    foreign key (idCliente) references tbl_cliente(idCliente)
);


-- ALTER TABLE tbl_cliente DROP nomeCliente; 

-- ALTER TABLE tbl_os ADD nomeCliente char(100) not null AFTER idCliente; 

-- ALTER TABLE child_table_name ADD CONSTRAINT fk_name FOREIGN KEY (child_column_name) 
-- REFERENCES parent_table_name(parent_column_name) ON DELETE CASCADE;

-- alter table tbl_os add constraint idCliente foreign key(idCliente) 
-- references tbl_cliente(idCliente) on delete cascade;

-- alter table tbl_os add tipo varchar(20) not null after dataOS;

insert into tbl_os(idUsuario, idCliente, tipo, situacao, produto, descricao, valor)
values
(1, 1, 'orçamento', 'entrega ok', 'Jogo de Sofá 2 e 3 Lugares', 'Fabricação do jogo de sofá, com tecido tal, na cor tal, etc...', 2340.00),
(1, 2, 'orçamento', 'aguardando aprovação', 'Jogo de Sofá de canto, 3x5 lugares', 'Fabricação do jogo de sofá, com tecido tal, na cor tal, etc...', 4180.00),
(2, 3, 'orçamento', 'orçamento', 'Jogo de Sofá Bicama ', 'Fabricação do jogo de sofá, com tecido tal, na cor tal, etc...', 1890.00);

-- drop table tbl_usuario;
-- drop table tbl_cliente;
 drop table tbl_os;

desc tbl_usuario;
desc tbl_cliente;
desc tbl_os;

select * from tbl_usuario;
select * from tbl_cliente;
select * from tbl_os;
select * from tbl_os where idOS = 1;

select * from tbl_os where idCliente = 3;

update tbl_usuario set login = 'simples', senha = 'simples' where id = 11;

select sum(valor) from tbl_os;

delete from tbl_usuario where id = 1;
select * from tbl_usuario where login like binary 'admin' and senha like binary 'admin';

select idCliente, nome, telefone from tbl_cliente where nome like binary "C%";

select * from tbl_usuario where id = (select max(id) from tbl_usuario);

select tbl_os.idOS, tbl_cliente.nome NOME, tbl_os.dataOS, tipo, situacao, produto, descricao, valor
from tbl_os 
inner join tbl_cliente order by tbl_cliente.nome;
-- on (tbl_os.idCliente = tbl_cliente.idCliente);
-- where idOs = 3;
 update tbl_os set nomeCLiente = 'Jose da Silva' where idOS = 1;

SELECT 
tbl_os.idOS, 
tbl_cliente.nome NOME, tbl_cliente.idCliente ID_Cliente, 
tbl_os.dataOS, tipo, situacao, produto, descricao, valor, idUsuario, nomeUsuario
FROM tbl_os INNER JOIN tbl_cliente ORDER BY tbl_cliente.nome;