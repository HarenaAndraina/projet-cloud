\c postgres;

drop database projet_cloud;

create database projet_cloud;

\c projet_cloud;


CREATE TABLE utilisateurs (
    id VARCHAR(50) PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    mot_de_passe VARCHAR(255) NOT NULL,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    role VARCHAR(50),
    cree_le TIMESTAMP,
    modifie_le TIMESTAMP
);

CREATE TABLE sessions (
    id VARCHAR(50) PRIMARY KEY,
    token VARCHAR(255),
    expire_le TIMESTAMP,
    cree_le TIMESTAMP,
    id_1 VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_1) REFERENCES utilisateurs(id)
);
