# SSI - Self Service Integration Notes

<details>
<summary><b>***Content Links***</b></summary>

-   [SSI - Self Service Integration
    Notes](#ssi---self-service-integration-notes)
    -   [TODO](#todo)
    -   [Running](#running)
        -   [Create GraalVM native
            image](#create-graalvm-native-image)
    -   [Testing](#testing)
        -   [IntelliJ HTTP](#intellij-http)
        -   [Terminal](#terminal)
    -   [Lombok](#lombok)
        -   [***Needs to be above micronaut annotations on
            classpath***](#needs-to-be-above-micronaut-annotations-on-classpath)
        -   [***For lombok to recognize micronaut annotations and inject
            into
            constructor***](#for-lombok-to-recognize-micronaut-annotations-and-inject-into-constructor)
    -   [GraalVM](#graalvm)
    -   [Microstream](#microstream)
        -   [GraalVM
            configeration](#graalvm-configeration)
        -   [Microstream gui](#microstream-gui)
    -   [Resources](#resources)
        -   [Micronaut 3.7.2
            Documentation](#micronaut-3.7.2-documentation)
        -   [GraalVM](#graalvm-1)
        -   [Microstream
            documentation](#microstream-documentation)
        -   [HTTP-client
            documentation](#http-client-documentation)
        -   [Security-JWT
            documentation](#security-jwt-documentation)

</details>

## TODO
1) Result Object 
2) clientEntityMap
3) add toJSON for TBEX payload
4) fix unit tests/add spock back
5) Logging Object
6) mysql db for logging with liquibase
7) http to tbex (add http client)
8) handle tbex return
9) branch set up just db to compare profile
10) JWT

## Running
```bash
./gradlew run
```
- ***Use in build.gradle to run in dev***

```groovy
 run {
    systemProperty('micronaut.environments', 'dev')
 }
```

### Create GraalVM native image
```bash
./gradlew nativeCompile
```
#### Run Native Image in Dev
```bash
./build/native/nativeCompile/graal-vm-ssi -Dmicronaut.environments=dev
```


---

---
## Testing
### IntelliJ HTTP
[HTTP Restfull Endpoints File](rest-api.http)

### Terminal

- ***CREATE***
```bash
curl -i -d '{"companyCode": "MICROSTREAM_bpaxton"}' -H "Content-Type: application/json" -X POST POST http://localhost:8082/ssi
```
- ***LIST*** 
```bash
curl -i localhost:8082/ssi/
```


---

---
## Lombok
### ***Needs to be above micronaut annotations on classpath***

### ***For lombok to recognize micronaut annotations and inject into constructor***

> create lombok.config at root, insert line:
> 
>> ```propeties
>>    lombok.copyableannotations += io.micronaut.core.annotation.Nullable
>> ```


---

---
## GraalVM
`To work with microstream add:`

`reflect-config.json to /src/main/resources/META-INF/native-image/[package]`

[Microstream source code for working with GraalVM](https://github.com/microstream-one/example-graalvm-native/tree/master/graalvm-native/src/main/resources/META-INF/native-image)


---

---
## Microstream
### GraalVM configeration
- add a reflect-config.json so Graal recognizes imported classes that need to be reflective
```bash
touch /src/main/resources/META-INF/native-image/com/ssi/reflect-config.json
```
[MicroStream Source Reference](https://gist.github.com/14paxton/d51cc2f493b8d8f4271c0cf55f2aefab)


---

---
### Microstream gui
> add to build.gradle
>> developmentOnly("io.micronaut.microstream:micronaut-microstream-rest")

[and download client ](https://docs.microstream.one/manual/storage/rest-interface/client-gui.html)


---

---





## Resources
### Micronaut 3.7.2 Documentation

- [User Guide](https://docs.micronaut.io/3.7.2/guide/index.html)
- [API Reference](https://docs.micronaut.io/3.7.2/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/3.7.2/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---
- [Shadow Gradle Plugin](https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow)

### GraalVM
- [Micronaut Docs: GraalVM ](https://docs.micronaut.io/latest/guide/index.html#graal)
- [Gradle plugin for GraalVM Native Image building : Config Doc](https://graalvm.github.io/native-build-tools/0.9.13/gradle-plugin.html#configuration-options)
- [Gradle Plugin for Micronaut : io.micronaut.graalvm](https://plugins.gradle.org/plugin/io.micronaut.graalvm)
- [GraalVM Docs : Reflection](https://www.graalvm.org/22.2/reference-manual/native-image/metadata/)
- [reflect-config.json Graal SourceCode](https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/Reflection.md)

### Microstream documentation
- [Micronaut MicroStream documentation](https://micronaut-projects.github.io/micronaut-microstream/latest/guide)
- [https://microstream.one/](https://microstream.one/)

#### Microstream-REST documentation
- [Micronaut MicroStream REST documentation](https://micronaut-projects.github.io/micronaut-microstream/latest/guide/#rest)
- [https://docs.microstream.one/manual/storage/rest-interface/index.html](https://docs.microstream.one/manual/storage/rest-interface/index.html)

### HTTP-client documentation
- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)

### Security-JWT documentation
- [Micronaut Security JWT documentation](https://micronaut-projects.github.io/micronaut-security/latest/guide/index.html)