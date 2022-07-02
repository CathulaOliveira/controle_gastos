# Controle financeiro

## Trabalho final da disciplina de OO2

Aplicação para lançamento de movimentações financeiras. 
O projeto contem cadastro de categorias e contas e o cadastro de movimentação onde podem ser lançadas entradas, saídas e transferencias entre contas.
Contem também métodos que calculam o total de entradas e saídas por mês em cada conta, listagem das movimentações realizadas por mês de e conta de filtro.
O saldo da conta é atualizado ao salvar movimentações através de Aspect.
Ao salvar a conta é setado o usuario logado no sistema como user da conta também através de Aspect.
Possui controle de autenticação com o Spring Security. 

Api desenvolvida em Java com utilização do Spring boot.
O projeto está conectado a um banco de dados PostgreSQL.
Desenvolvido testes unitários e de integração com JUnit utilizando banco de dados em memório M2.
