# spark-kotlin

A thin idiomatic kotlin layer for spark

Authors:
@perwendel
@lallemupp

Syntax:

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
```
