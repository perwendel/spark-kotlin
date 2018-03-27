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
 * Handles routes and makes Kotlin sugar possible.
 *
 * @param request the Spark [Request].
 * @param response the Spark [Response]
 */
open class RouteHandler(val request: Request, val response: Response) {

    // Implicit access of Request functions

    /**
     * Gets the request path parameter.
     */
    fun params(name: String): String? {
        return request.params(name)
    }

    /**
     * Gets all request path parameters.
     */
    fun params(): MutableMap<String, String>? {
        return request.params()
    }

    /**
     * Gets the request splat (wildcard) parameters.
     */
    fun splat(): Array<out String>? {
        return request.splat()
    }

    /**
     * Gets the request content type.
     */
    fun contentType(): String? {
        return request.contentType()
    }

    /**
     * Gets the request query param.
     */
    fun queryParams(key: String): String? {
        return request.queryParams(key)
    }

    /**
     * Gets the request queryMap.
     */
    fun queryMap(): QueryParamsMap {
        return request.queryMap()
    }

    /**
     * Gets the request queryMap for key.
     */
    fun queryMap(key: String): QueryParamsMap {
        return request.queryMap(key)
    }

    /**
     * Gets request attribute.
     */
    fun attribute(key: String): String {
        return request.attribute(key)
    }

    /**
     * Sets request attribute.
     */
    fun attribute(key: String, value: String) {
        request.attribute(key, value)
    }

    /**
     * Gets the request attributes.
     */
    fun attributes(): MutableSet<String>? {
        return request.attributes()
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
    fun host(): String? {
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
    fun pathInfo(): String? {
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
    fun userAgent(): String? {
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
    fun type(): String? {
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
