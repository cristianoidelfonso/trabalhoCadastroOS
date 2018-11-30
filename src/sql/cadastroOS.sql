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

create table if not exists tbl_os
(
	idOS integer not null primary key auto_increment,
    idUsuario int not null,
    idCliente integer not null,
    dataOS timestamp default current_timestamp,
    produto varchar(100),
    descricao text,
    valor double default 0.00,
    foreign key (idUsuario) references tbl_usuario(id),
    foreign key (idCliente) references tbl_cliente(idCliente)
);

drop table tbl_usuario;
drop table tbl_cliente;
drop table tbl_os;

desc tbl_usuario;
desc tbl_cliente;
desc tbl_os;

select * from tbl_usuario;
select * from tbl_cliente;
select * from tbl_os;

delete from tbl_usuario where id = 1;
select * from tbl_usuario where login like binary 'admin' and senha like binary 'admin';