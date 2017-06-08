# spark-kotlin

A Spark DSL in idiomatic kotlin.

Authors:
--------
- Per Wendel, @perwendel
- Love Löfdahl, @lallemupp

Dependency:
-----------
Maven:
```xml
<dependency>
    <groupId>com.sparkjava</groupId>
    <artifactId>spark-kotlin</artifactId>
    <version>1.0.0-alpha</version>
</dependency>
```

Gradle:
```groovy
compile "com.sparkjava:spark-kotlin:1.0.0-alpha"
```

Syntax:
-------

```kotlin
http.get("/hello") {
    "Hello Spark Kotlin"
}

http.get("/nothing") {
    status(404)
    "Oops, we couldn't find what you're looking for"
}

http.get("/saymy/:name") {
    params(":name")
}

http.get("/redirect") {
    redirect("/hello")
}
```
