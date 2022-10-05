
USE algafood;

-- Criando tabelas 
/*
 * forma_pagamento
 * grupo
 * grupo_permissao
 * permissao
 * produto
 * restaurante
 * restaurante_forma_pagamento
 * usuario
 * usuario_grupo
 * 
 */
    create table forma_pagamento (
       id bigint not null auto_increment,
        descricao varchar(80) not null,
        primary key (id)
    ) engine = InnoDB DEFAULT CHARSET = utf8;

    create table grupo (
       id bigint not null auto_increment,
        nome varchar(80) not null,
        primary key (id)
    ) engine=InnoDB  DEFAULT CHARSET=utf8;

    create table grupo_permissao (
       grupo_id bigint not null,
        permissao_id bigint not null
    ) engine=InnoDB  DEFAULT CHARSET=utf8;

    create table permissao (
       id bigint not null auto_increment,
        descricao varchar(80) not null,
        nome varchar(80) not null,
        primary key (id)
    ) engine=InnoDB  DEFAULT CHARSET=utf8;

    create table produto (
       id bigint not null auto_increment,
        ativo bit not null,
        descricao varchar(80) not null,
        nome varchar(80) not null,
        preco decimal(19,2) not null,
        restaurante_id bigint not null,
        primary key (id)
    ) engine=InnoDB  DEFAULT CHARSET=utf8;

    create table restaurante (
       id bigint not null auto_increment,
        data_atualizacao datetime not null,
        data_cadastro datetime not null,
        endereco_bairro varchar(80),
        endereco_cep varchar(9),
        endereco_complemento varchar(80),
        endereco_logradouro varchar(80),
        endereco_numero varchar(10),
        nome varchar(80) not null,
        taxa_frete decimal(19,2) not null,
        cozinha_id bigint not null,
        endereco_cidade_id bigint,
        primary key (id)
    ) engine=InnoDB  DEFAULT CHARSET=utf8;

    create table restaurante_forma_pagamento (
       restaurante_id bigint not null,
        forma_pagamento_id bigint not null
    ) engine=InnoDB  DEFAULT CHARSET=utf8;

    create table usuario (
       id bigint not null auto_increment,
        data_cadastro datetime not null,
        email varchar(255) not null,
        nome varchar(80) not null,
        senha varchar(80) not null,
        primary key (id)
    ) engine=InnoDB  DEFAULT CHARSET=utf8;

    create table usuario_grupo (
       usuario_id bigint not null,
        grupo_id bigint not null
    ) engine=InnoDB  DEFAULT CHARSET=utf8;

   /*
    * 
    * Adicionando referencias de integridade
    * 
    */
   
   alter table grupo_permissao 
       add constraint fk_permissao_grupo 
       foreign key (permissao_id) 
       references permissao (id);

    alter table grupo_permissao 
       add constraint fk_grupo_permissao 
       foreign key (grupo_id) 
       references grupo (id);

    alter table produto 
       add constraint fk_restaurante_produto 
       foreign key (restaurante_id) 
       references restaurante (id);

    alter table restaurante 
       add constraint fk_cozinha 
       foreign key (cozinha_id) 
       references cozinha (id);

    alter table restaurante 
       add constraint fk_cidade 
       foreign key (endereco_cidade_id) 
       references cidade (id);

    alter table restaurante_forma_pagamento 
       add constraint fk_forma_pagamento 
       foreign key (forma_pagamento_id) 
       references forma_pagamento (id);

    alter table restaurante_forma_pagamento 
       add constraint fk_restaurante_forma_pagamento 
       foreign key (restaurante_id) 
       references restaurante (id);
      
    alter table usuario_grupo 
       add constraint fk_grupo 
       foreign key (grupo_id) 
       references grupo (id);

    alter table usuario_grupo 
       add constraint fk_usuario 
       foreign key (usuario_id) 
       references usuario (id);
