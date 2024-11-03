CREATE TABLE tbl_coletas(
    numero_coleta BIGINT(20) NOT NULL AUTO_INCREMENT,
    nome_motorista VARCHAR(100) NULL,
    veiculo_coleta VARCHAR(100) NULL,
    status_coleta VARCHAR(20) NOT NULL,
    data_coleta DATE NULL,
    PRIMARY KEY (numero_coleta)
);