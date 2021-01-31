create table loteria (
	id bigint not null auto_increment,
	cliente_id bigint not null,
	quantidade int not null,
	numeros int not null,
	data_compra datetime not null,
	
	primary key (id)
	);
	
	alter table loteria add constraint fk_loteria_cliente
	foreign key (cliente_id) references cliente (id);