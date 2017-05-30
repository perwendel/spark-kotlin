package spark.examples.instance

import spark.Http
import spark.examples.testutil.keyStoreLocation
import spark.examples.testutil.keystorePassword
import spark.examples.testutil.trustStoreLocation
import spark.examples.testutil.trustStorePassword
import spark.ignite

/**
 * Fluent Initialization
 */
fun main(args: Array<String>) {

    val http: Http = ignite()
            .port(5656)
            .threadPool(10)
            .secure(keyStoreLocation(),
                    keystorePassword(),
                    trustStoreLocation(),
                    trustStorePassword())

    // when accessing don't forget https
    http.get("/fluent") {
        "Yes, it is!"
    }

}