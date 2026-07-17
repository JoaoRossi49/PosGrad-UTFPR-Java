/*João Vitor de Rossi Figueiredo
Atividade 02
Sistemas de Banco de Dados*/

--2) Criar banco

CREATE DATABASE empresa;
USE empresa;

CREATE TABLE DEPARTAMENTO (
    codigo INT AUTO_INCREMENT PRIMARY KEY,
    nome             VARCHAR(100) NOT NULL
);

CREATE TABLE FUNCIONARIO (
    codigo   INT AUTO_INCREMENT PRIMARY KEY,
    nome              VARCHAR(100) NOT NULL,
    qtd_dependentes   INT NOT NULL DEFAULT 0,
    salario           DECIMAL(10,2) NOT NULL,
    cargo             VARCHAR(50) NOT NULL,
    cod_departamento  INT NOT NULL,
    CONSTRAINT fk_funcionario_departamento
        FOREIGN KEY (cod_departamento) REFERENCES DEPARTAMENTO(codigo)
);

/*3 Popular banco */

INSERT INTO DEPARTAMENTO (nome) VALUES
    ('Logística'),                -- 1
    ('Pesquisa e Desenvolvimento'),-- 2
    ('Atendimento ao Cliente'),   -- 3
    ('Jurídico'),                 -- 4
    ('Operações');                -- 5

INSERT INTO FUNCIONARIO (nome, qtd_dependentes, salario, cargo, cod_departamento) VALUES
    ('Marcos Silva',           1,  9500.00, 'Coordenador', 1),
    ('Ana Júlia Ferreira',     0,  4200.00, 'Auxiliar',    1),
    ('Roberto Mendes',         2,  6000.00, 'Analista',    1),
    ('Laura Carvalho',         0, 16000.00, 'Cientista',   2),
    ('Felipe Moura',           1, 14000.00, 'Engenheiro',  2),
    ('Vinícius Machado',       0,  4100.00, 'Assistente',  4),
    ('Sabrina Melo',           2,  9000.00, 'Advogada',    4),
    ('Gabriel Farias',         1, 11000.00, 'Gerente',     5),
    ('Beatriz Nunes',          0,  5500.00, 'Analista',    5);

/*4 a -> Departamento com o MAIOR número de funcionários e quantidade*/
CREATE OR REPLACE VIEW vw_departamento_mais_FUNCIONARIO AS
SELECT d.nome AS nome_departamento, COUNT(f.cod_funcionario) AS qtd_FUNCIONARIO
FROM DEPARTAMENTO d
JOIN FUNCIONARIO f ON f.cod_departamento = d.cod_departamento
GROUP BY d.cod_departamento, d.nome
ORDER BY qtd_FUNCIONARIO DESC
LIMIT 1;

/*4 b -> Departamento com a MENOR quantidade de funcionários SEM dependentes*/
CREATE OR REPLACE VIEW vw_departamento_menos_FUNCIONARIO_sem_dependentes AS
SELECT d.nome AS nome_departamento, COUNT(f.cod_funcionario) AS qtd_FUNCIONARIO_sem_dependentes
FROM DEPARTAMENTO d
JOIN FUNCIONARIO f ON f.cod_departamento = d.cod_departamento
WHERE f.qtd_dependentes = 0
GROUP BY d.cod_departamento, d.nome
ORDER BY qtd_FUNCIONARIO_sem_dependentes ASC
LIMIT 1;

/*4 c -> DEPARTAMENTO cujo nome começa com "DIR", com seus respectivos funcionários */
CREATE OR REPLACE VIEW vw_DEPARTAMENTO_dir AS
SELECT d.nome AS nome_departamento, f.nome AS nome_funcionario
FROM DEPARTAMENTO d
JOIN FUNCIONARIO f ON f.cod_departamento = d.cod_departamento
WHERE d.nome LIKE 'DIR%';

/*4 d) Funcionário com o MAIOR salário e seu departamento */
CREATE OR REPLACE VIEW vw_funcionario_maior_salario AS
SELECT f.nome AS nome_funcionario, d.nome AS nome_departamento, f.salario
FROM FUNCIONARIO f
JOIN DEPARTAMENTO d ON d.cod_departamento = f.cod_departamento
ORDER BY f.salario DESC
LIMIT 1;

/*4 e) Departamento e funcionário de cada departamento com cargo "Gerente" */
CREATE OR REPLACE VIEW vw_gerentes_por_departamento AS
SELECT d.nome AS nome_departamento, f.nome AS nome_funcionario
FROM DEPARTAMENTO d
JOIN FUNCIONARIO f ON f.cod_departamento = d.cod_departamento
WHERE f.cargo = 'Gerente';

/*5 a) Usuário "funcionario" - acesso limitado (somente leitura nas views) */
CREATE USER 'funcionario'@'localhost' IDENTIFIED BY 'Funcionario#2026';
GRANT SELECT ON empresa.vw_departamento_menos_FUNCIONARIO_sem_dependentes TO 'funcionario'@'localhost';
GRANT SELECT ON empresa.vw_departamento_mais_FUNCIONARIO TO 'funcionario'@'localhost';
GRANT SELECT ON empresa.vw_funcionario_maior_salario TO 'funcionario'@'localhost';
GRANT SELECT ON empresa.vw_DEPARTAMENTO_dir TO 'funcionario'@'localhost';
GRANT SELECT ON empresa.vw_gerentes_por_departamento TO 'funcionario'@'localhost';

/*5 b) Usuário "gerente" - acesso completo ao banco */
CREATE USER 'gerente'@'localhost' IDENTIFIED BY 'Gerente#2026';
GRANT ALL PRIVILEGES ON empresa.* TO 'gerente'@'localhost';

FLUSH PRIVILEGES;