use db_blogpessoal;

insert into tb_postagens(data, texto,titulo)
values(CURRENT_TIMESTAMP(), "Primeiro Post está ok.", "Teste servidor");