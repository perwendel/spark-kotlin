package spark.examples.instance

import spark.kotlin.ignite
import spark.kotlin.seconds

/**
 * Example usage of spark-kotlin via instance API. YOU SHOULD NAME THE SPARK INSTANCE 'http' FOR EXPRESSIVE/DECLARATIVE PURPOSES.
 * If you don't it's blasphemy. // TODO
 */
fun main(args: Array<String>) {

    val http =
            ignite {
                staticFiles {
                    location = "/public"
                    expiryTime = 36000.seconds
                }
            }

    http.get("/hello") {
        "Hello Spark Kotlin"
    }

    http.get("/target") {
        "Target"
    }

    http.get("/new") {
        "new get"
    }

    http.post("/new") {
        "New post"
    }


    http.redirect {
        any(
                "/from" to "/hello",
                "/hi" to "/hello"
        )
        get(
                "/source" to "/target"
        )
        post(
                "/gone" to "/new"
        )
    }

}