package spark.examples.instance

import spark.examples.testutil.keyStoreLocation
import spark.examples.testutil.keystorePass
import spark.examples.testutil.trustStoreLocation
import spark.examples.testutil.trustStorePassword
import spark.kotlin.halt
import spark.kotlin.ignite
import spark.kotlin.seconds

/**
 * Example usage of spark-kotlin via instance API. YOU SHOULD NAME THE SPARK INSTANCE 'http' FOR EXPRESSIVE/DECLARATIVE PURPOSES.
 * If you don't it's blasphemy.
 */
fun main(args: Array<String>) {

    val http = ignite {
        port = 5500
        ipAddress = "0.0.0.0"

        threadPool {
            maxThreads = 10
            minThreads = 5
            idleTimeoutMillis = 1000
        }

        secure {
            keystore {
                file = keyStoreLocation()
                password = keystorePass()
            }
            truststore {
                file = trustStoreLocation()
                password = trustStorePassword()
            }
            needsClientCert = false
        }

        staticFiles {
            location = "/public"
            expiryTime = 36000.seconds

            headers(
                    "description" to "static content",
                    "licence" to "free to use"
            )
            mimeTypes(
                    "cxt" to "text/html"
            )
        }
    }

    http.get("/hello") {
        "Hello Spark Kotlin"
    }

    http.get("/halt") {
        halt(201, "created!")
    }

    http.get("/nothing") {
        status(404)
        "Oops, we couldn't find what you're looking for"
    }

    http.get("/saymy/:name") {
        "Hello: " + params[":name"]
    }

    http.get("/redirect") {
        redirect("/hello");
    }

    http.before("/hello") {
        println("Before Hello")
    }

    http.after("/hello") {
        println("After Hello")
    }

    http.finally {
        println("At last")
    }

    http.redirect {
        any("/from" to "/hello")
    }

    http.get("/exception") {
        throw AuthException("You are lost my friend")
    }

    http.exception(AuthException::class) {
        status(401)
        response.body(exception.message)
    }
}

