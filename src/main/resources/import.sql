insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');
insert into cozinha (id, nome) values (3, 'Brasileira');

insert into restaurante (id, nome, taxa_frete, cozinha_id) values (1, 'Thai Gourmet', 0, 1);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (2, 'Thai Delivery', 9.50, 1);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (3, 'Tuk Tuk Comida Indiana', 15, 2);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (4, 'Fogão de Lenha', 8.50, 3);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (5, 'Panela Mineira', 0, 3);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (6, 'Panela Mineira', 15, 3);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (7, 'Panela Brasil', 0.0, 3);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (8, 'Panela Mineira 2', 0, 3);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (9, 'Rei do rango', 0.0, 3);

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');
insert into estado (id, nome) values (4, 'Rondônia');
insert into estado (id, nome) values (5, 'Acre');
insert into estado (id, nome) values (6, 'Bahia');
insert into estado (id, nome) values (8, 'Distrito Federal');
insert into estado (id, nome) values (9, 'Amazonas');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);
insert into cidade (id, nome, estado_id) values (6, 'Porto Velho', 4);
insert into cidade (id, nome, estado_id) values (7, 'Ariquemes', 4);
insert into cidade (id, nome, estado_id) values (8, 'Jaru', 4);
insert into cidade (id, nome, estado_id) values (9, 'Ji-Paraná', 4);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');
insert into forma_pagamento (id, descricao) values (4, 'Pix');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

