/*
 * 7.8. Criando migrações complexas com remanejamento de dados
   Migração com remangamento de dados

Este exemplo foram inseridos dados na tabela de cidade, e após a inserção fazer a migração dos dados existentes...
para uma nova tabela e normalizar o relacionamento. isso pode ser útil para casos onde o sitema evolui e a sua estrutura
sofre modificações, desta forma escrevemos um script para fazer a migração segura dos dados e adequação de realidade.

Dados de teste para uso com a versão 2:

INSERT into cidade (nome_cidade, nome_estado) values ('Uberlândia', 'Minas Gerais');
INSERT into cidade (nome_cidade, nome_estado) values ('Belo Horizonte', 'Minas Gerais');
INSERT into cidade (nome_cidade, nome_estado) values ('São Paulo' , 'São Paulo');
INSERT into cidade (nome_cidade, nome_estado) values ('Jaru', 'Rondônia');
INSERT into cidade (nome_cidade, nome_estado) values ('Porto Velho', 'Rondônia');

============

# desnvolvendo migrações

Backup do banco antes de começar mexer... é sempre bom.

mysqldump --host 127.0.0.1 --user root --password --databases algafood > dump.sql
mysql --host 127.0.0.1 -u root --password < dump.sql

*/

-- -----------------------------------------------------
-- Wélyqrson B. Amaral
-- -----------------------------------------------------


USE algafood;

CREATE  TABLE estado(
	id BIGINT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(80) NOT NULL,
	
	PRIMARY KEY (id)
	) ENGINE = innoDB DEFAULT charset = utf8;

-- Inserir dados da coluna nome_estado da tabela cidade para 
-- tabela estado na colula nome 
INSERT  INTO estado (nome) SELECT DISTINCT  nome_estado FROM cidade;

-- Alterar tabela cidade para criar coluna estado_id chave de relacionamento 
-- com a tabela estado estado campo id
ALTER TABLE cidade ADD COLUMN estado_id BIGINT NOT NULL;

-- Inserindo o relacionamento com a chave de estado
UPDATE cidade c SET c.estado_id = ( SELECT e.id 
                                    FROM estado e
                                    WHERE
                                       e.nome = c.nome_estado 
                                   );
									
ALTER  TABLE  cidade  ADD CONSTRAINT fk_cidade_estado 
	FOREIGN KEY (estado_id) REFERENCES estado (id);
	
ALTER TABLE cidade DROP COLUMN nome_estado;

ALTER TABLE cidade  CHANGE nome_cidade nome VARCHAR(80) NOT NULL;	
