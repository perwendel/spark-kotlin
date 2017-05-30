package spark.examples.static

import spark.Http
import spark.ignite

/**
 * Example usage of spark-kotlin custom error pages via STATIC API.
 */
fun main(args: Array<String>) {

    val http: Http = ignite()

    http.get("/internalerror") {
        throw RuntimeException()
    }

    http.internalServerError {
        "<html><body>INSTANCE FUCKED UP!</body></html>"
    }

    http.notFound {
        "INSTANCE NOT FOUND MAN!"
    }
}
