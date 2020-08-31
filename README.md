# Proyecto Spring Boot Ejemplo para Pool de Conexiones y JDBC

## Elementos Utilziados
- HirakiConfig
- DataSource
- NamedParameterJdbcTemplate
- DataSourceTransactionManager
- Lombok
- SqlParameterSource
- BeanPropertySqlParameterSource
- MapSqlParameterSource
- RowMapper
- Oracle XE 11g




## Correr Contenedor de Oracle conn el puerto 49161 local
```
docker run -d -p 49161:1521 oracleinanutshell/oracle-xe-11g
```

## Crear una tabla basica
```
CREATE TABLE Customer (
  id int  NOT NULL,
  name varchar(20) DEFAULT NULL,
  age int ,
  PRIMARY KEY (id)
) ;

```

## Crear Secuencia
```
CREATE  sequence customer_seq START WITH 1;
```

## Crear Desencadenador Para Identificador
```
CREATE OR REPLACE  TRIGGER  customer_trigger
BEFORE INSERT ON CUSTOMER
FOR EACH  ROW 
BEGIN 
    SELECT customer_seq.NEXTVAL
    INTO :new.id
    FROM dual;
END;
```
## Insertar Valores 

```
INSERT INTO CUSTOMER(NAME,AGE) values('German',28);
INSERT INTO CUSTOMER(NAME,AGE) values('Pablo',31);
INSERT INTO CUSTOMER(NAME,AGE) values('Juan',54);
```


## Verificacion del pull de Conexiones Inicial

```
SELECT
       SUBSTR(A.SPID,1,9) PID,
       SUBSTR(B.SID,1,5) SID,
       SUBSTR(B.SERIAL#,1,5) SER#,
       SUBSTR(B.MACHINE,1,6) BOX,
       SUBSTR(B.USERNAME,1,10) USERNAME,
--       B.SERVER,
       SUBSTR(B.OSUSER,1,8) OS_USER,
       SUBSTR(B.PROGRAM,1,30) PROGRAM
FROM V$SESSION B, V$PROCESS A
WHERE
B.PADDR = A.ADDR
AND TYPE='USER'
ORDER BY SPID; 
```

## Endpoints:

- Get All http://localhost:9093/customers [GitHub Pages](s)

- Get By Id http://localhost:9093/customers/{id}

- POST Save Customer: http://localhost:9093/customers
```
Body:

{
    "name": "German",
    "age": 58
}
```

- PUT Update customer: http://localhost:9093/customers/{id}
```
Body:

{
    "name": "Alejandra",
    "age": 26
}
```

- Delete Update customer: http://localhost:9093/customers/{id}

