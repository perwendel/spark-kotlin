package spark.examples.static

import spark.get
import spark.internalServerError
import spark.notFound

/**
 * Example usage of spark-kotlin custom error pages via STATIC API.
 */
fun main(args: Array<String>) {

    get("/internalerror") {
        throw RuntimeException()
    }

    internalServerError {
        "<html><body>YOU FUCKED UP, STATICALLY!</body></html>"
    }

    notFound {
        "STATIC NOT FOUND MAN!"
    }
}
