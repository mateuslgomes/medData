create table usuarios(

    id binary(16) NOT NULL DEFAULT (UUID_TO_BIN(UUID(), TRUE)),
    login varchar(100) not null,
    senha varchar(255) not null,

    primary key(id)

);