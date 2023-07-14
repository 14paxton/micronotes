# Micronaut
## Reflective Access

- add a reflect-config.json so Graal recognizes imported classes that need to be reflective
```bash
touch /src/main/resources/META-INF/native-image/com/ssi/reflect-config.json
```
[MicroStream Source Reference](https://gist.github.com/14paxton/d51cc2f493b8d8f4271c0cf55f2aefab)

> ex.
```json
{
    "name": "fts.marketing.utils.deserializers.CampaignEmailStatusDeserializer",
    "allDeclaredConstructors" : true,
    "allPublicConstructors" : true,
    "allDeclaredMethods" : true,
    "allPublicMethods" : true,
    "allDeclaredClasses" : true,
    "allPublicClasses" : true
  }
```

## GraalVM annotation processor
> gradle import: 
```groovy
annotationProcessor("io.micronaut:micronaut-graal")
```

***This processor generates additional classes that implement the ***
***[GraalReflectionConfigurer](https://docs.micronaut.io/latest/api/io/micronaut/core/graal/GraalReflectionConfigurer.html) inteface and programmatically register*** ***reflection configuration.***

- For example the following class:
```java 
@ReflectiveAccess
@Serdeable
public class Integration {
      ...
  }
```
>> ***If you have more advanced requirements and only wish to include certain fields or methods, use the annotation on any constructor,*** 
>> ***field or method to include only the specific field, constructor or method.***



# TidBit
## Due to a bug with GraalVM and Java Records it is necessary to include the flag 
```
--report-unsupported-elements-at-runtime when building the native executable. Create the file native-image.properties:
```
at
> src/main/resources/META-INF/native-image/example.micronaut/guide/native-image.properties
```bash
Args = --report-unsupported-elements-at-runtime
```
# Gradle
> The Gradle plugin has a new testNativeImage task that builds the GraalVM Native Image and uses the native application as an embedded 
> server enabling the ability to write native integration tests.

# Plugins
- [Java Assist](https://mvnrepository.com/artifact/org.javassist/javassist)
- [GraalVM Hibernate](https://mvnrepository.com/artifact/org.hibernate/hibernate-graalvm/6.1.5.Final)

# Resources

- [Gradle Plugin](https://micronaut-projects.github.io/micronaut-gradle-plugin/latest/#_micronaut_graalvm_plugin)
- [Micronaut Docs: GraalVM ](https://docs.micronaut.io/latest/guide/index.html#graal)
- [Gradle plugin for GraalVM Native Image building : Config Doc](https://graalvm.github.io/native-build-tools/0.9.13/gradle-plugin.html#configuration-options)
- [Gradle Plugin for Micronaut : io.micronaut.graalvm](https://plugins.gradle.org/plugin/io.micronaut.graalvm)
- [GraalVM Docs : Reflection](https://www.graalvm.org/22.2/reference-manual/native-image/metadata/)
- [reflect-config.json Graal SourceCode](https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/Reflection.md)
- [Micronaut graalvm Tests Source Code](https://github.com/micronaut-graal-tests/micronaut-liquibase-graal/tree/2.3.x_h2)

