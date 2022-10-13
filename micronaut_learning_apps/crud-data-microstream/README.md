1) setup in aws-db readme



## Testing

#### create with microstream
```bash
curl -i -d '{"name":"sonray"}' -H "Content-Type: application/json" -X POST http://localhost:8082/microstream/person

```

#### list microstream
```bash
curl -i localhost:8082/microstream/person
```


### Microstream gui
> add to build.gradle
>> developmentOnly("io.micronaut.microstream:micronaut-microstream-rest")

>  and download client 
>> https://docs.microstream.one/manual/storage/rest-interface/client-gui.html

## Micronaut 3.7.0 Documentation

- [User Guide](https://docs.micronaut.io/3.7.0/guide/index.html)
- [API Reference](https://docs.micronaut.io/3.7.0/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/3.7.0/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

- [Shadow Gradle Plugin](https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow)
## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)


## Feature microstream documentation

- [Micronaut MicroStream documentation](https://micronaut-projects.github.io/micronaut-microstream/latest/guide)

- [https://microstream.one/](https://microstream.one/)