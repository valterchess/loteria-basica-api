create table if not exists cliente (
	id bigint not null auto_increment,
	nome varchar(60) not null,
	email varchar(255) not null,
	
	primary key(id)
	);