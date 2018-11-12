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

drop table tbl_usuario;

desc tbl_usuario;

select * from tbl_usuario;

delete from tbl_usuario where id = 1;