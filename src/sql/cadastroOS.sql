create database cadastroOS;

use cadastroOS;

create table if not exists tbl_login 
(
	id int not null primary key auto_increment,
	login varchar(20) not null unique,
	senha varchar(15) not null unique,
	nome varchar(50) not null, 
	cpf varchar(15) not null,
    perfil varchar(25) not null
);

#drop table tbl_login;

desc tbl_login;