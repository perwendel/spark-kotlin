/*
 * Copyright 2017 Per Wendel, Love Löfdahl
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package spark.examples.instance

import spark.kotlin.halt
import spark.kotlin.ignite
import spark.kotlin.seconds

public infix fun <A, B> A.tooth(that: B): Pair<A, B> = Pair(this, that)

public infix fun <A, B> A.sabretooth(that: B): Pair<A, B> {
    return Pair(this, that)
}


/**
 * Example usage of spark-kotlin via instance API. YOU SHOULD NAME THE SPARK INSTANCE 'http' FOR EXPRESSIVE/DECLARATIVE PURPOSES.
 * If you don't it's blasphemy.
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

    http.get("/halt") {
        halt(201, "created!")
    }

    http.get("/nothing") {
        status(404)
        "Oops, we couldn't find what you're looking for"
    }

    http.get("/saymy/:name") {
        params(":name")
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

    http.redirect.any("/to", "/hello")

    http.get("/exception") {
        throw NotFoundException("You are lost my friend")
    }

    http.exception(NotFoundException::class) {
        status(404)
        response.body(exception.message)
    }
}

class NotFoundException(message: String) : Exception(message)
