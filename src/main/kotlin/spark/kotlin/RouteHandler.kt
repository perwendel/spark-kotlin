/*
 * Copyright 2017 Love Löfdahl, Per Wendel
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
package spark.kotlin

import spark.QueryParamsMap
import spark.Request
import spark.Response
import spark.Session

/**
 * Query params. For DSL purposes.
 */
class QueryParams(private val request: Request) {

    val size: Int
        get() {
            return request.queryParams().size
        }

    fun contains(name: String): Boolean {
        return request.queryParams().contains(name)
    }

    fun isEmpty(): Boolean {
        return request.queryParams().isEmpty()
    }

    fun iterator(): Iterator<String> {
        return request.queryParams().iterator()
    }

    operator fun get(name: String): String {
        return request.queryParams(name)
    }

    fun values(name: String): MutableSet<String> {
        val values = mutableSetOf<String>()
        values.addAll(request.queryParamsValues(name))
        return values
    }

    override fun toString(): String {
        return request.queryParams().toString()
    }

    fun raw(): Set<String> {
        return request.queryParams()
    }

}

/**
 * Request attributes. For DSL purposes.
 */
class Attributes(private val request: Request) {

    val size: Int
        get() {
            return request.attributes().size
        }

    fun iterator(): MutableIterator<String> {
        return request.attributes().iterator()
    }

    fun contains(name: String): Boolean {
        return request.attributes().contains(name)
    }

    fun isEmpty(): Boolean {
        return request.attributes().isEmpty()
    }

    operator fun get(name: String): String {
        return request.attribute(name)
    }

    operator fun set(name: String, value: String) {
        request.attribute(name, value)
    }

    override fun toString(): String {
        return request.attributes().toString()
    }

    fun raw(): Set<String> {
        return request.attributes()
    }

}

/**
 * Path params. For DSL purposes.
 */
class PathParams(private val request: Request) {

    val size: Int
        get() {
            return request.params().size
        }

    fun contains(name: String): Boolean {
        return request.params().containsKey(name)
    }

    fun isEmpty(): Boolean {
        return request.params().isEmpty()
    }

    operator fun get(name: String): String {
        return request.params(name)
    }

    override fun toString(): String {
        return request.params().toString()
    }

    fun raw(): Map<String, String> {
        return request.params()
    }
}

/**
 * Splat functionality. DSL purposes.
 */
class Splat(private val splat: Array<out String>) {

    operator fun get(index: Int): String {
        return splat[index]
    }

    val size: Int
        get() {
            return splat.size
        }

    override fun toString(): String {

        if (splat.isEmpty())
            return "[]"

        val iterator = splat.iterator()

        val sb = StringBuilder()
        sb.append('[')

        while (iterator.hasNext()) {

            val element = iterator.next()
            sb.append(element)

            if (!iterator.hasNext()) {
                return sb.append(']').toString()
            }
            sb.append(',').append(' ')
        }

        return sb.toString()
    }

}

/**
 * Handles routes and makes Kotlin sugar possible.
 *
 * @param request the Spark [Request].
 * @param response the Spark [Response]
 */
open class RouteHandler(val request: Request, val response: Response) {

    // Implicit access of Request functionality

    val params = PathParams(request)
    val queryParams = QueryParams(request)
    val attributes = Attributes(request)
    val splat = Splat(request.splat())

    val queryMap: QueryParamsMap
        get() {
            return request.queryMap()
        }

    /**
     * Gets the request content type.
     */
    fun contentType(): String {
        return request.contentType()
    }

    /**
     * Gets the request session, if no exists one is created.
     */
    fun session(): Session {
        return request.session()
    }

    /**
     * Gets the request session, if no exists one is created if create is true.
     */
    fun session(create: Boolean): Session? {
        return request.session(create)
    }

    /**
     * Gets the request uri.
     */
    fun uri(): String {
        return request.uri()
    }

    /**
     * Gets request protocol.
     */
    fun protocol(): String {
        return request.protocol()
    }

    /**
     * Gets request scheme.
     */
    fun scheme(): String {
        return request.scheme()
    }

    /**
     * Gets the request host name (from HTTP request header "host")
     */
    fun host(): String {
        return request.host()
    }

    /**
     * Gets the server port
     */
    fun port(): Int {
        return request.port()
    }

    /**
     * Gets request path info.
     */
    fun pathInfo(): String {
        return request.pathInfo()
    }

    /**
     * Gets request servlet path.
     */
    fun servletPath(): String {
        return request.servletPath()
    }

    /**
     * Gets request context path.
     */
    fun contextPath(): String {
        return request.contextPath()
    }

    /**
     * Gets request user agent.
     */
    fun userAgent(): String {
        return request.userAgent()
    }

    /**
     * Gets request method.
     */
    fun requestMethod(): String {
        return request.requestMethod()
    }


    // Implicit access of Response functions

    /**
     * Gets the response status code.
     */
    fun status(): Int {
        return response.status()
    }

    /**
     * Sets the response status code.
     */
    fun status(code: Int) {
        response.status(code)
    }

    /**
     * Gets the response content type.
     */
    fun type(): String {
        return response.type()
    }

    /**
     * Sets the response content type.
     */
    fun type(contentType: String) {
        response.type(contentType)
    }

    /**
     * Redirects to location.
     */
    fun redirect(location: String) {
        response.redirect(location)
    }

    /**
     * Redirects to location with statusCode.
     */
    fun redirect(location: String, statusCode: Int) {
        response.redirect(location, statusCode)
    }
}
