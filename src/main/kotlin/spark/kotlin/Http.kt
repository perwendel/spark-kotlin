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

import spark.*
import spark.Service.SPARK_DEFAULT_PORT
import spark.kotlin.websocket.WebSocket
import spark.kotlin.websocket.WebSocketHandler
import kotlin.reflect.KClass

// STATIC API BEGIN
val DEFAULT_ACCEPT = "*/*"

// TODO: this is a prototype
fun config(function: InitParams.() -> Unit) {

    val wrapper = InitParams(SPARK_DEFAULT_PORT, "0.0.0.0", ThreadPool(), Secure(), StaticFiles())
    function(wrapper)

    val staticFiles = Spark.staticFiles

    val pool = wrapper.pool
    val secure = wrapper.secure
    val static = wrapper.static

    Spark.port(wrapper.port)
    Spark.ipAddress(wrapper.ipAddress)
    Spark.threadPool(pool.maxThreads, pool.minThreads, pool.idleTimeoutMillis)

    if (secure.keystore.file != NIL) {
        Spark.secure(
                secure.keystore.file,
                secure.keystore.password,
                secure.truststore.file,
                secure.truststore.password,
                secure.needsClientCert)
    }

    if (static.location != NIL) {
        staticFiles.location(static.location)
    }

    if (static.externalLocation != NIL) {
        staticFiles.externalLocation(static.externalLocation)
    }

    if (static.expiryTime.inSeconds > 0) {
        staticFiles.expireTime(static.expiryTime.inSeconds)
    }

    if (static.headers.isNotEmpty()) {
        staticFiles.headers(static.headers)
    }

    for (header in static.headers) {
        staticFiles.header(header.key, header.value)
    }

    for (type in static.mimeTypes) {
        staticFiles.registerMimeType(type.key, type.value)
    }

}

private var redirects = Redirects()

/**
 * Initializes the Spinoza server. SHOULD just be used when using the Websockets functionality.
 */
fun init() {
    Spark.init()
}

fun redirect(function: Redirects.() -> Unit) {
    function(redirects)

    redirects applyOn Spark.redirect
}

/**
 * The port used by Spark
 */
val port: Int
    get() {
        return Spark.port()
    }


//----------------- Route & filter mappings -----------------//

/**
 * Represents a HTTP GET request.
 *
 * @param path The path to listen to.
 * @param accepts The accept type to listen to. Defaults to all accept types.
 * @param function The function that handles the request.
 */
fun get(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
    Spark.get(path, accepts) { req, res ->
        function(RouteHandler(req, res))
    }
}

fun get(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
    Spark.get(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
        return function(RouteHandler(req, res))
    }), templateEngine)
}

fun post(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
    Spark.post(path, accepts) { req, res ->
        function(RouteHandler(req, res))
    }
}

fun post(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
    Spark.post(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
        return function(RouteHandler(req, res))
    }), templateEngine)
}

fun put(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
    Spark.put(path, accepts) { req, res ->
        function(RouteHandler(req, res))
    }
}

fun put(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
    Spark.put(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
        return function(RouteHandler(req, res))
    }), templateEngine)
}

fun delete(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
    Spark.delete(path, accepts) { req, res ->
        function(RouteHandler(req, res))
    }
}

fun delete(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
    Spark.delete(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
        return function(RouteHandler(req, res))
    }), templateEngine)
}

fun head(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
    Spark.head(path, accepts) { req, res ->
        function(RouteHandler(req, res))
    }
}

fun head(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
    Spark.head(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
        return function(RouteHandler(req, res))
    }), templateEngine)
}

fun trace(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
    Spark.trace(path, accepts) { req, res ->
        function(RouteHandler(req, res))
    }
}

fun trace(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
    Spark.trace(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
        return function(RouteHandler(req, res))
    }), templateEngine)
}

fun options(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
    Spark.options(path, accepts) { req, res ->
        function(RouteHandler(req, res))
    }
}

fun options(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
    Spark.options(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
        return function(RouteHandler(req, res))
    }), templateEngine)
}

fun patch(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
    Spark.patch(path, accepts) { req, res ->
        function(RouteHandler(req, res))
    }
}

fun patch(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
    Spark.patch(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
        return function(RouteHandler(req, res))
    }), templateEngine)
}

fun connect(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
    Spark.connect(path, accepts) { req, res ->
        function(RouteHandler(req, res))
    }
}

fun connect(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
    Spark.connect(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
        return function(RouteHandler(req, res))
    }), templateEngine)
}

fun before(filter: Filter) {
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

//----------------- Custom error pages -----------------//
fun notFound(function: RouteHandler.() -> Any) {
    Spark.notFound() { req, res ->
        function(RouteHandler(req, res))
    }
}

fun internalServerError(function: RouteHandler.() -> Any) {
    Spark.internalServerError() { req, res ->
        function(RouteHandler(req, res))
    }
}

//----------------- exception mapping -----------------//

fun <T : Exception> exception(exceptionClass: KClass<T>, function: ExceptionHandler.() -> Unit) {
    Spark.exception(exceptionClass.java, spark.ExceptionHandler(fun(e, req, res) {
        function(ExceptionHandler(e, req, res))
    }))
}

//----------------- Halts -----------------//

fun halt(): HaltException {
    return Spark.halt()
}

fun halt(body: String): HaltException {
    return Spark.halt(body)
}

fun halt(code: Int): HaltException {
    return Spark.halt(code)
}

fun halt(code: Int, body: String): HaltException {
    return Spark.halt(code, body)
}

/**
 * Stops the Spark server and clears all routes
 */
fun stop() {
    Spark.stop()
}

/**
 * TODO
 */
fun webSocket(path: String, function: WebSocket.() -> Unit) {

    val webSocket = WebSocket()
    function(webSocket)

    println("websocket = " + webSocket)

    val wsHandler = WebSocketHandler(webSocket)

    println("adding websocket")

    Spark.webSocket(path, wsHandler)
}

// STATIC API END

/**
 * Ignites a Spark (HTTP) instance.
 */
fun ignite(): Http {
    return Http(Service.ignite())
}

/**
 * Ignites a Spark (HTTP) instance with the possibility to configure using a simple ignitition DSL:
 *
 * val http = ignite {
 *
 * }
 */
fun ignite(function: InitParams.() -> Unit): Http {

    val wrapper = InitParams(SPARK_DEFAULT_PORT, "0.0.0.0", ThreadPool(), Secure(), StaticFiles())
    function(wrapper)

    val pool = wrapper.pool
    val secure = wrapper.secure
    val static = wrapper.static

    val http = Service.ignite()
            .port(wrapper.port)
            .ipAddress(wrapper.ipAddress)
            .threadPool(pool.maxThreads, pool.minThreads, pool.idleTimeoutMillis)

    if (secure.keystore.file != NIL) {
        http.secure(
                secure.keystore.file,
                secure.keystore.password,
                secure.truststore.file,
                secure.truststore.password,
                secure.needsClientCert)
    }

    if (static.location != NIL) {
        http.staticFiles.location(static.location)
    }

    if (static.externalLocation != NIL) {
        http.staticFiles.externalLocation(static.externalLocation)
    }

    if (static.expiryTime.inSeconds > 0) {
        http.staticFiles.expireTime(static.expiryTime.inSeconds)
    }

    if (static.headers.isNotEmpty()) {
        http.staticFiles.headers(static.headers)
    }

    for (header in static.headers) {
        http.staticFiles.header(header.key, header.value)
    }

    for (type in static.mimeTypes) {
        http.staticFiles.registerMimeType(type.key, type.value)
    }

    return Http(http)
}

/**
 * The route class that takes a Spark service and wraps the route methods to enable fancy syntax
 * with access to request and response parameters in the route code.
 *
 * @para service The [Spark] service that will be wrapped.
 */
class Http(private val service: Service) {

    val DEFAULT_ACCEPT = "*/*"

    private var redirects = Redirects()

    fun redirect(function: Redirects.() -> Unit) {
        function(redirects)

        redirects applyOn service.redirect
    }

    /**
     * Map the route for HTTP GET requests
     *
     * @param path The path to listen to.
     * @param accepts The accept type to listen to. Defaults to all accept types.
     * @param function The function that handles the request.
     */
    fun get(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
        service.get(path, accepts) { req, res ->
            function(RouteHandler(req, res))
        }
    }

    fun get(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.get(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
            return function(RouteHandler(req, res))
        }), templateEngine)
    }

    fun post(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
        service.post(path, accepts) { req, res ->
            function(RouteHandler(req, res))
        }
    }

    fun post(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.post(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
            return function(RouteHandler(req, res))
        }), templateEngine)
    }

    fun put(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
        service.put(path, accepts) { req, res ->
            function(RouteHandler(req, res))
        }
    }

    fun put(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.put(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
            return function(RouteHandler(req, res))
        }), templateEngine)
    }

    fun delete(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
        service.delete(path, accepts) { req, res ->
            function(RouteHandler(req, res))
        }
    }

    fun delete(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.delete(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
            return function(RouteHandler(req, res))
        }), templateEngine)
    }

    fun head(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
        service.head(path, accepts) { req, res ->
            function(RouteHandler(req, res))
        }
    }

    fun head(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.head(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
            return function(RouteHandler(req, res))
        }), templateEngine)
    }

    fun trace(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
        service.trace(path, accepts) { req, res ->
            function(RouteHandler(req, res))
        }
    }

    fun trace(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.trace(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
            return function(RouteHandler(req, res))
        }), templateEngine)
    }

    fun options(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
        service.options(path, accepts) { req, res ->
            function(RouteHandler(req, res))
        }
    }

    fun options(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.options(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
            return function(RouteHandler(req, res))
        }), templateEngine)
    }

    fun patch(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
        service.patch(path, accepts) { req, res ->
            function(RouteHandler(req, res))
        }
    }

    fun patch(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.patch(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
            return function(RouteHandler(req, res))
        }), templateEngine)
    }

    fun connect(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
        service.connect(path, accepts) { req, res ->
            function(RouteHandler(req, res))
        }
    }

    fun connect(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.connect(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView {
            return function(RouteHandler(req, res))
        }), templateEngine)
    }

    fun before(filter: Filter) {
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


    //----------------- Custom error pages -----------------//
    fun notFound(function: RouteHandler.() -> Any) {
        service.notFound() { req, res ->
            function(RouteHandler(req, res))
        }
    }

    fun internalServerError(function: RouteHandler.() -> Any) {
        service.internalServerError() { req, res ->
            function(RouteHandler(req, res))
        }
    }

    /**
     * Maps functionality to be executed when an exception occurs during routing
     */
    fun <T : Exception> exception(exceptionClass: KClass<T>, function: ExceptionHandler.() -> Unit) {
        service.exception(exceptionClass.java, spark.ExceptionHandler(fun(e, req, res) {
            function(ExceptionHandler(e, req, res))
        }))
    }

    /**
     * The port used by the Spark instance.
     */
    val port: Int
        get () {
            return service.port()
        }

    /**
     * Initializes the Spinoza instance API server. SHOULD just be used when using the Websockets functionality.
     */
    fun init() {
        service.init()
    }

    /**
     * Stops the Spark server and clears all routes
     */
    fun stop() {
        service.stop()
    }

    /**
     * TODO
     */
    fun webSocket(path: String, function: WebSocket.() -> Unit) {

        val webSocket = WebSocket()
        function(webSocket)

        println("websocket = " + webSocket)

        val wsHandler = WebSocketHandler(webSocket)

        println("adding websocket")

        service.webSocket(path, wsHandler)
    }
}
