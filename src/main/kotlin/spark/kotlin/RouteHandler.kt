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

import spark.Request
import spark.Response

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
    inline fun params(name: String) = request.params(name)

    /**
     * Gets all request path parameters.
     */
    inline fun params() = request.params()

    /**
     * Gets the request splat (wildcard) parameters.
     */
    inline fun splat() = request.splat()

    /**
     * Gets the request content type.
     */
    inline fun contentType() = request.contentType()

    /**
     * Gets the request query param.
     */
    inline fun queryParams(key: String) = request.queryParams(key)

    /**
     * Gets the request queryMap.
     */
    inline fun queryMap() = request.queryMap()

    /**
     * Gets the request queryMap for key.
     */
    inline fun queryMap(key: String) = request.queryMap(key)

    /**
     * Gets request attribute.
     */
    inline fun attribute(key: String): String = request.attribute(key)

    /**
     * Sets request attribute.
     */
    inline fun attribute(key: String, value: String) = request.attribute(key, value)

    /**
     * Gets the request attributes.
     */
    inline fun attributes() = request.attributes();

    /**
     * Gets the request session, if no exists one is created.
     */
    inline fun session() = request.session()

    /**
     * Gets the request session, if no exists one is created if create is true.
     */
    inline fun session(create: Boolean) = request.session(create)

    /**
     * Gets the request uri.
     */
    inline fun uri() = request.uri()

    /**
     * Gets request protocol.
     */
    inline fun protocol() = request.protocol()

    /**
     * Gets request scheme.
     */
    inline fun scheme() = request.scheme()

    /**
     * Gets the request host name (from HTTP request header "host")
     */
    inline fun host() = request.host()

    /**
     * Gets the server port
     */
    inline fun port() = request.port()

    /**
     * Gets request path info.
     */
    inline fun pathInfo() = request.pathInfo()

    /**
     * Gets request servlet path.
     */
    inline fun servletPath() = request.servletPath()

    /**
     * Gets request context path.
     */
    inline fun contextPath() = request.contextPath()

    /**
     * Gets request user agent.
     */
    inline fun userAgent() = request.userAgent()

    /**
     * Gets request method.
     */
    inline fun requestMethod() = request.requestMethod()

    // Implicit access of Response functions

    /**
     * Gets the response status code.
     */
    inline fun status() = response.status()

    /**
     * Sets the response status code.
     */
    inline fun status(code: Int) = response.status(code)

    /**
     * Gets the response content type.
     */
    inline fun type() = response.type()

    /**
     * Sets the response content type.
     */
    inline fun type(contentType: String) = response.type(contentType)

    /**
     * Redirects to location.
     */
    inline fun redirect(location: String) = response.redirect(location)

    /**
     * Redirects to location with statusCode.
     */
    inline fun redirect(location: String, statusCode: Int) = response.redirect(location, statusCode)
}
