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
package spark

// STATIC API BEGIN
val DEFAULT_ACCEPT = "*/*"

/**
 * Represents a HTTP GET request.
 *
 * @param path The path to listen to.
 * @param accepts The accept type to listen to. Defaults to all accept types.
 * @param function The function that handles the request.
 */
fun get(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
    Spark.get(path, accepts) {
        req, res ->
        function(RouteHandler(req, res))
    }
}

fun get(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
    Spark.get(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
        return function(RouteHandler(req, res))
    }), templateEngine)
}

fun get(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
    Spark.get(path, accepts, Route(fun(req, res) {
        function(RouteHandler(req, res))
    }), responseTransformer)
}

fun post(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
    Spark.post(path, accepts) {
        req, res ->
        function(RouteHandler(req, res))
    }
}

fun post(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
    Spark.post(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
        return function(RouteHandler(req, res))
    }), templateEngine)
}

fun post(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
    Spark.post(path, accepts, Route(fun(req, res) {
        function(RouteHandler(req, res))
    }), responseTransformer);
}

fun put(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
    Spark.put(path, accepts) {
        req, res ->
        function(RouteHandler(req, res))
    }
}

fun put(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
    Spark.put(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
        return function(RouteHandler(req, res))
    }), templateEngine)
}

fun put(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
    Spark.post(path, accepts, Route(fun(req, res) {
        function(RouteHandler(req, res))
    }), responseTransformer)
}

fun delete(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
    Spark.delete(path, accepts) {
        req, res ->
        function(RouteHandler(req, res))
    }
}

fun delete(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
    Spark.delete(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
        return function(RouteHandler(req, res))
    }), templateEngine)
}

fun delete(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
    Spark.delete(path, accepts, Route(fun(req, res) {
        function(RouteHandler(req, res))
    }), responseTransformer)
}

fun head(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
    Spark.head(path, accepts) {
        req, res ->
        function(RouteHandler(req, res))
    }
}

fun head(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
    Spark.head(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
        return function(RouteHandler(req, res))
    }), templateEngine)
}

fun head(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
    Spark.head(path, accepts, Route(fun(req, res) {
        function(RouteHandler(req, res))
    }), responseTransformer)
}

fun trace(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
    Spark.trace(path, accepts) {
        req, res ->
        function(RouteHandler(req, res))
    }
}

fun trace(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
    Spark.trace(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
        return function(RouteHandler(req, res))
    }), templateEngine)
}

fun trace(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
    Spark.trace(path, accepts, Route(fun(req, res) {
        function(RouteHandler(req, res))
    }), responseTransformer)
}

fun options(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
    Spark.options(path, accepts) {
        req, res ->
        function(RouteHandler(req, res))
    }
}

fun options(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
    Spark.options(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
        return function(RouteHandler(req, res))
    }), templateEngine)
}

fun options(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
    Spark.options(path, accepts, Route(fun(req, res) {
        function(RouteHandler(req, res))
    }), responseTransformer)
}

fun patch(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
    Spark.patch(path, accepts) {
        req, res ->
        function(RouteHandler(req, res))
    }
}

fun patch(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
    Spark.patch(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
        return function(RouteHandler(req, res))
    }), templateEngine)
}

fun patch(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
    Spark.patch(path, accepts, Route(fun(req, res) {
        function(RouteHandler(req, res))
    }), responseTransformer)
}

fun connect(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
    Spark.connect(path, accepts) {
        req, res ->
        function(RouteHandler(req, res))
    }
}

fun connect(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
    Spark.connect(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
        return function(RouteHandler(req, res))
    }), templateEngine)
}

fun connect(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
    Spark.connect(path, accepts, Route(fun(req, res) {
        function(RouteHandler(req, res))
    }), responseTransformer)
}

fun before(filter: Filter, accepts: String = DEFAULT_ACCEPT) {
    Spark.before(filter)
}

fun before(path: String? = null, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Unit) {
    val filter = Filter(fun(req, res) {
        function(RouteHandler(req, res))
    })
    if (path == null) Spark.before(filter) else Spark.before(path, accepts, filter)
}

fun after(path: String? = null, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Unit) {
    val filter = Filter(fun(req, res) {
        function(RouteHandler(req, res))
    })
    if (path == null) Spark.after(filter) else Spark.after(path, accepts, filter)
}

fun finally(path: String? = null, function: RouteHandler.() -> Unit) {
    val filter = Filter(fun(req, res) {
        function(RouteHandler(req, res))
    })
    if (path == null) Spark.afterAfter(filter) else Spark.afterAfter(path, filter)
}
// STATIC API END

/**
 * Ignites a Spark (HTTP) instance.
 */
fun ignite() : Http {
    return Http(Service.ignite())
}

class RouteHandler(val request: Request, val response: Response) {

    // Implicit access of Request functions

    /**
     * Gets the request path parameter.
     */
    fun params(name: String): String {
        return request.params(name)
    }

    /**
     * Gets all request path parameters.
     */
    fun params(): MutableMap<String, String>? {
        return request.params();
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
    fun contentType(): String {
        return request.contentType()
    }

    /**
     * Gets the request query param.
     */
    fun queryParams(key: String): String {
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
        return request.attribute(key);
    }

    /**
     * Sets request attribute.
     */
    fun attribute(key: String, value: String) {
        request.attribute(key, value);
    }

    /**
     * Gets the request attributes.
     */
    fun attributes(): MutableSet<String>? {
        return request.attributes();
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

/**
 * The route class that takes a Spark service and wraps the route methods to enable fancy syntax
 * with access to request and response parameters in the route code.
 *
 * @para service The [Spark] service that will be wrapped.
 */
class Http(val service: Service) {

    val DEFAULT_ACCEPT = "*/*"

    /**
     * Represents a HTTP GET request.
     *
     * @param path The path to listen to.
     * @param accepts The accept type to listen to. Defaults to all accept types.
     * @param function The function that handles the request.
     */
    fun get(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
        service.get(path, accepts) {
            req, res ->
            function(RouteHandler(req, res))
        }
    }

    fun get(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.get(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
            return function(RouteHandler(req, res))
        }), templateEngine)
    }

    fun get(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
        service.get(path, accepts, Route(fun(req, res) {
            function(RouteHandler(req, res))
        }), responseTransformer)
    }

    fun post(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
        service.post(path, accepts) {
            req, res ->
            function(RouteHandler(req, res))
        }
    }

    fun post(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.post(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
            return function(RouteHandler(req, res))
        }), templateEngine)
    }

    fun post(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
        service.post(path, accepts, Route(fun(req, res) {
            function(RouteHandler(req, res))
        }), responseTransformer);
    }

    fun put(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
        service.put(path, accepts) {
            req, res ->
            function(RouteHandler(req, res))
        }
    }

    fun put(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.put(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
            return function(RouteHandler(req, res))
        }), templateEngine)
    }

    fun put(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
        service.post(path, accepts, Route(fun(req, res) {
            function(RouteHandler(req, res))
        }), responseTransformer)
    }

    fun delete(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
        service.delete(path, accepts) {
            req, res ->
            function(RouteHandler(req, res))
        }
    }

    fun delete(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.delete(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
            return function(RouteHandler(req, res))
        }), templateEngine)
    }

    fun delete(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
        service.delete(path, accepts, Route(fun(req, res) {
            function(RouteHandler(req, res))
        }), responseTransformer)
    }

    fun head(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
        service.head(path, accepts) {
            req, res ->
            function(RouteHandler(req, res))
        }
    }

    fun head(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.head(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
            return function(RouteHandler(req, res))
        }), templateEngine)
    }

    fun head(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
        service.head(path, accepts, Route(fun(req, res) {
            function(RouteHandler(req, res))
        }), responseTransformer)
    }

    fun trace(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
        service.trace(path, accepts) {
            req, res ->
            function(RouteHandler(req, res))
        }
    }

    fun trace(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.trace(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
            return function(RouteHandler(req, res))
        }), templateEngine)
    }

    fun trace(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
        service.trace(path, accepts, Route(fun(req, res) {
            function(RouteHandler(req, res))
        }), responseTransformer)
    }

    fun options(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
        service.options(path, accepts) {
            req, res ->
            function(RouteHandler(req, res))
        }
    }

    fun options(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.options(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
            return function(RouteHandler(req, res))
        }), templateEngine)
    }

    fun options(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
        service.options(path, accepts, Route(fun(req, res) {
            function(RouteHandler(req, res))
        }), responseTransformer)
    }

    fun patch(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
        service.patch(path, accepts) {
            req, res ->
            function(RouteHandler(req, res))
        }
    }

    fun patch(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.patch(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
            return function(RouteHandler(req, res))
        }), templateEngine)
    }

    fun patch(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
        service.patch(path, accepts, Route(fun(req, res) {
            function(RouteHandler(req, res))
        }), responseTransformer)
    }

    fun connect(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
        service.connect(path, accepts) {
            req, res ->
            function(RouteHandler(req, res))
        }
    }

    fun connect(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.connect(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
            return function(RouteHandler(req, res))
        }), templateEngine)
    }

    fun connect(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
        service.connect(path, accepts, Route(fun(req, res) {
            function(RouteHandler(req, res))
        }), responseTransformer)
    }

    fun before(filter: Filter, accepts: String = DEFAULT_ACCEPT) {
        service.before(filter)
    }

    fun before(path: String? = null, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Unit) {
        val filter = Filter(fun(req, res) {
            function(RouteHandler(req, res))
        })
        if (path == null) service.before(filter) else service.before(path, accepts, filter)
    }

    fun after(path: String? = null, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Unit) {
        val filter = Filter(fun(req, res) {
            function(RouteHandler(req, res))
        })
        if (path == null) service.after(filter) else service.after(path, accepts, filter)
    }

    fun finally(path: String? = null, function: RouteHandler.() -> Unit) {
        val filter = Filter(fun(req, res) {
            function(RouteHandler(req, res))
        })
        if (path == null) service.afterAfter(filter) else service.afterAfter(path, filter)
    }
}
