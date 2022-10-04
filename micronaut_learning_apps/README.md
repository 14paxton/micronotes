
```bash
export AWS_ACCESS_KEY_ID=AKIASC5Z6WSK4I6MHICM;
export AWS_SECRET_ACCESS_KEY=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;
```

- hosts need to be set to work- use after setting up db instance

```bash
export MYSQL_HOST=$(aws rds describe-db-instances --query 'DBInstances[?DBInstanceIdentifier==`crud-data-aws-db-create-micro-person`].Endpoint.Address' --output text) 
```

#### custom env variables add to .yml if choos

```bash
export JDBC_URL=jdbc:mysql://${MYSQL_HOST}:3306/micro_person
export JDBC_USER=admin
export JDBC_PASSWORD=secret99
```

#### micronaut specific env variables

- specific to micronaut
- connection pools https://micronaut-projects.github.io/micronaut-sql/latest/guide/#jdbc-connection-pools
-

```bash
export DATASOURCES_DEFAULT_URL=jdbc:mysql://${MYSQL_HOST}:3306/micro_person
export DATASOURCES_DEFAULT_USERNAME=admin
export DATASOURCES_DEFAULT_PASSWORD=secret99
```
> Micronaut Framework populates the properties datasources.default.url, datasources.default.username and datasources.default.password with those environment variables' values. Learn more about JDBC Connection Pools.
>
>You can run the application and test the API as it was described in the previous sections. However, when you run the application, Micronaut Test Resources does not start a MySQL container because you have provided values for datasources.default.*
> properties.

## Start script to use amazon db
```bash
export AWS_ACCESS_KEY_ID=AKIASC5Z6WSK4I6MHICM;
export AWS_SECRET_ACCESS_KEY=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;
export MYSQL_HOST=$(aws rds describe-db-instances --query 'DBInstances[?DBInstanceIdentifier==`crud-data-aws-db-create-micro-person`].Endpoint.Address' --output text);
export JDBC_URL=jdbc:mysql://${MYSQL_HOST}:3306/micronaut;
export JDBC_USER=admin;
export JDBC_PASSWORD=secret99;
export DATASOURCES_DEFAULT_URL=jdbc:mysql://${MYSQL_HOST}:3306/micronaut;
export DATASOURCES_DEFAULT_USERNAME=admin;
export DATASOURCES_DEFAULT_PASSWORD=secret99;
```