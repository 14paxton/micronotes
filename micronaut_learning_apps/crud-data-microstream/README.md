1) setup in aws-db readme



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

#### create with microstream
```bash
curl -i -d '{"name":"tulip"}' -H "Content-Type: application/json" -X POST http://localhost:8082/microstream/person

```

#### list microstream
```bash
curl -i localhost:8082/microstream/person
```