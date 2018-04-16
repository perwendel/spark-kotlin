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
package spark.examples.static

import spark.examples.instance.AuthException
import spark.kotlin.*

/**
 * Example usage of spark-kotlin via STATIC API.
 */
fun main(args: Array<String>) {

    println(1.hours)
    println(10.minutes)
    println(1.days)

    config {
        port = 4568
        staticFiles {
            location = "/public"
            expiryTime = 3600.seconds
        }
    }

    // hello world example
    get("/hello") {
        "Hello Static Spark Kotlin"
    }

    // halt example
    get("/halt") {
        halt(201, "created!")
    }

    // Response code example
    get("/nothing") {
        status(404)
        "Oops, we couldn't find what you're looking for"
    }

    // Path params example
    get("/saymy/:name") {
        println("params: " + params)
        "Hello: " + params[":name"]
    }

    // Query params example (add ?q=<something here> when accessing)
    get("/search") {

        queryParams.contains("q")
        queryParams.isEmpty()

        println(queryParams) // all params (keys)
        println(queryParams.values("q")) // all values for key "q"

        "you searched for: " + queryParams["q"]
    }

    // Query map example
    get("/map") {

        println(queryMap.hasKey("q"))
        println(queryMap["q"].value())

        "play around with query maps"
    }

    // Splat example
    get("/say/*/to/*") {

        println(splat)
        println(splat.size)
        println(splat[0])

        "say " + splat[0] + " to " + splat[1]
    }

    // redirect from route example
    get("/redirect") {
        redirect("/hello");
    }

    // before filter example
    before("/hello") {
        println("Before Hello")
    }

    // after filter example
    after("/hello") {
        println("After Hello")
    }

    // finally filter example
    finally {
        println("At last")
    }

    // request access example
    get("/info") {
        "request: " + request
    }

    // Custom 404 not found example
    notFound {
        "Nothing here"
    }

    // Exception mapping example
    get("/exception") {
        throw AuthException("static protection")
    }

    exception(AuthException::class) {
        status(401)
        response.body(exception.message)
    }

    // redirect DSL example
    redirect {
        any("/from" to "/hello")
    }

    // Attributes example
    before("/attribute/:value") {
        println("[before] setting attribute 'somekey' to: " + params["value"])
        attributes["somekey"] = params["value"]
    }

    after("/attribute/:value") {

        println("attributes: " + attributes)
        println("[after] attribute: " + attributes["somekey"])
    }

    // Available functionality example
    get("/available") {

        println(port())
        println(uri())

        "done"
    }

    println(port)
}
