create database mercado_bernardo;
use mercado_bernardo;

create table Funcionario(
	id int PRIMARY KEY not null auto_increment,
    nome varchar(40) not null,
    rg varchar(9) not null,
    cpf varchar(11) not null
);

create table Cep(
	idCep int PRIMARY KEY not null auto_increment,
    numCep varchar(8) not null,
    nomeLogradouro varchar(40) not null,
    uf varchar(2) not null,
    cidade varchar(20) not null,
    bairro varchar(20) not null 
);

create table Fornecedor(
	id int PRIMARY KEY not null auto_increment,
	nome varchar(50) not null,
    cnpj varchar(14) not null,
    ie varchar(12),
    fk_idCep int,
    foreign key (fk_idCep) references Cep (idCep),
    complemento varchar(50),
    numeroDoLocal int not null
);

-- -------------------------------------------------------------------------------------------------------------------------------------- --

-- Funcionario 1
INSERT INTO Funcionario(nome, rg, cpf) VALUES ('João Silva', '243459993', '20977331075');

-- Funcionario 2
INSERT INTO Funcionario(nome, rg, cpf) VALUES ('Maria Santos', '287994193', '11837776040');

-- Funcionario 3
INSERT INTO Funcionario(nome, rg, cpf) VALUES ('Pedro Souza', '425401431', '93021594000');

-- Funcionario 4
INSERT INTO Funcionario(nome, rg, cpf) VALUES ('Ana Oliveira', '137967421', '34433612006');

-- Funcionario 5
INSERT INTO Funcionario(nome, rg, cpf) VALUES ('Lucas Pereira', '462376503', '07351043019');

-- -------------------------------------------------------------------------------------------------------------------------------------- --

-- CEP 1
INSERT INTO Cep(numCep, nomeLogradouro, uf, cidade, bairro) VALUES ('01001000', 'Rua Anhangabaú', 'SP', 'São Paulo', 'Centro');

-- CEP 2
INSERT INTO Cep(numCep, nomeLogradouro, uf, cidade, bairro) VALUES ('04023060', 'Alameda dos Maracatins', 'SP', 'São Paulo', 'Moema');

-- CEP 3
INSERT INTO Cep(numCep, nomeLogradouro, uf, cidade, bairro) VALUES ('80010010', 'Rua XV de Novembro', 'PR', 'Curitiba', 'Centro');

-- CEP 4
INSERT INTO Cep(numCep, nomeLogradouro, uf, cidade, bairro) VALUES ('20010010', 'Rua do Rosário', 'RJ', 'Rio de Janeiro', 'Centro');

-- CEP 5
INSERT INTO Cep(numCep, nomeLogradouro, uf, cidade, bairro) VALUES ('60115170', 'Avenida Dom Luís', 'CE', 'Fortaleza', 'Meireles');

-- -------------------------------------------------------------------------------------------------------------------------------------- --

-- Fornecedor 1
INSERT INTO Fornecedor(nome, cnpj, ie, fk_idCep, complemento, numeroDoLocal) VALUES ('Supermercado A', '95670949000105', '987654321345', (SELECT idCep FROM Cep WHERE numCep = '01001000'), 'Complemento 1', 123);

-- Fornecedor 2
INSERT INTO Fornecedor(nome, cnpj, ie, fk_idCep, complemento, numeroDoLocal) VALUES ('Loja de Eletrônicos B', '51339423000171', '123456789345', (SELECT idCep FROM Cep WHERE numCep = '04023060'), 'Complemento 2', 456);

-- Fornecedor 3
INSERT INTO Fornecedor(nome, cnpj, ie, fk_idCep, complemento, numeroDoLocal) VALUES ('Distribuidora C', '18648202000100', '543216789345', (SELECT idCep FROM Cep WHERE numCep = '80010010'), 'Complemento 3', 789);

-- Fornecedor 4
INSERT INTO Fornecedor(nome, cnpj, ie, fk_idCep, complemento, numeroDoLocal) VALUES ('Indústria D', '34890228000116', '987654321563', (SELECT idCep FROM Cep WHERE numCep = '20010010'), 'Complemento 4', 987);

-- Fornecedor 5
INSERT INTO Fornecedor(nome, cnpj, ie, fk_idCep, complemento, numeroDoLocal) VALUES ('Atacadista E', '21227773000185', '123456789465', (SELECT idCep FROM Cep WHERE numCep = '60115170'), 'Complemento 5', 654);

-- -------------------------------------------------------------------------------------------------------------------------------------- --


