1) setup in aws-db readme

## Start script
```bash
export AWS_ACCESS_KEY_ID=AKIASC5Z6WSK3YUJHKLC;
export AWS_SECRET_ACCESS_KEY=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;
export MYSQL_HOST=$(aws rds describe-db-instances --query 'DBInstances[?DBInstanceIdentifier==`crud-data-aws-db-create-micro-person`].Endpoint.Address' --output text);
export JDBC_URL=jdbc:mysql://${MYSQL_HOST}:3306/micronaut;
export JDBC_USER=admin;
export JDBC_PASSWORD=secret99;
export DATASOURCES_DEFAULT_URL=jdbc:mysql://${MYSQL_HOST}:3306/micronaut;
export DATASOURCES_DEFAULT_USERNAME=admin;
export DATASOURCES_DEFAULT_PASSWORD=secret99;
```

## Testing

#### add an object

```bash
curl -X "POST" "http://localhost:8082/person/" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{ "firstName": "itchy", "lastName": "peterson" }'
```

#### list

````bash
curl http://localhost:8082/person/
````

````bash
curl http://localhost:8082/person/record
````