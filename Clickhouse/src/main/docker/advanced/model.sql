CREATE DATABASE clickhousedb;
CREATE USER dbuser IDENTIFIED WITH sha256_password BY 'password';
CREATE ROLE clickhousedb_role;
GRANT SELECT, INSERT, ALTER, CREATE, UPDATE, DROP, TRUNCATE, OPTIMIZE ON clickhousedb.* to clickhousedb_role;
GRANT clickhousedb_role to dbuser;
CREATE TABLE clickhousedb.employee
(
    id String,
    dob DateTime64,
    name String
)
    ENGINE = MergeTree()
PRIMARY KEY (id);