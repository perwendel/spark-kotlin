# spark-kotlin

A thin idiomatic kotlin layer for spark

Authors:
--------
- Per Wendel, @perwendel
- Love Löfdahl, @lallemupp

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
```
