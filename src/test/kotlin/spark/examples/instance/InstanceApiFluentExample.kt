package spark.examples.instance

import spark.kotlin.Http
import spark.examples.testutil.keyStoreLocation
import spark.examples.testutil.keystorePass
import spark.examples.testutil.trustStoreLocation
import spark.examples.testutil.trustStorePassword
import spark.kotlin.ignite

/**
 * Fluent Initialization
 */
fun main(args: Array<String>) {

    val http: Http = ignite()
            .port(5656)
            .threadPool(10)
            .secure(keyStoreLocation(),
                    keystorePass(),
                    trustStoreLocation(),
                    trustStorePassword())

    // when accessing don't forget https
    http.get("/fluent") {
        "Yes, it is!"
    }

}