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

// TODO: Add implicit access functions for the methods in request and response that make sense
class RouteHandler(val request: Request, val response: Response) {

    // Implicit access of Request functions

    // TODO: does this make sense, available both in request and response
    fun body(): String {
        return request.body();
    }

    fun bodyAsBytes(): ByteArray? {
        return request.bodyAsBytes();
    }

    fun params(name: String): String {
        return request.params(name)
    }

    fun queryParams(key: String): String {
        return request.queryParams(key)
    }

    fun queryMap(): QueryParamsMap {
        return request.queryMap()
    }

    fun queryMap(key: String): QueryParamsMap {
        return request.queryMap(key)
    }

    fun attribute(key: String): String {
        return request.attribute(key);
    }

    fun attribute(key: String, value: String) {
        request.attribute(key, value);
    }

    fun attributes(): MutableSet<String>? {
        return request.attributes();
    }

    fun session(): Session? {
        return request.session()
    }

    fun session(create: Boolean): Session? {
        return request.session(create)
    }

    fun cookie(name: String): String {
        return request.cookie(name)
    }

    fun cookies(): MutableMap<String, String>? {
        return request.cookies()
    }

    fun uri(): String {
        return request.uri()
    }

    fun protocol(): String {
        return request.protocol()
    }

    // Implicit access of Response functions

    fun status(): Int {
        return response.status()
    }

    fun status(code: Int) {
        response.status(code)
    }

    fun redirect(location: String) {
        response.redirect(location)
    }

    fun redirect(location: String, statusCode: Int) {
        response.redirect(location, statusCode  )
    }
}


class FilterHandler(val req: Request, val res: Response) {

    val request : Request = req
    val response : Response = res



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
            req, res -> function(RouteHandler(req, res))
        }
    }

    fun get(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.get(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView { return function(RouteHandler(req, res)) }), templateEngine)
    }

    fun get(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
        service.get(path, accepts, Route(fun(req, res) { function(RouteHandler(req, res)) }), responseTransformer)
    }

    fun post(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
        service.post(path, accepts) {
            req, res -> function(RouteHandler(req, res))
        }
    }

    fun post(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.post(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView { return function(RouteHandler(req, res)) }), templateEngine)
    }

    fun post(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
        service.post(path, accepts, Route(fun(req, res) { function(RouteHandler(req, res)) }), responseTransformer);
    }

    fun put(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
        service.put(path, accepts) {
            req, res -> function(RouteHandler(req, res))
        }
    }

    fun put(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.put(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView { return function(RouteHandler(req, res)) }), templateEngine)
    }

    fun put(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
        service.post(path, accepts, Route(fun(req, res) { function(RouteHandler(req, res)) }), responseTransformer)
    }

    fun delete(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
        service.delete(path, accepts) {
            req, res -> function(RouteHandler(req, res))
        }
    }

    fun delete(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.delete(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView { return function(RouteHandler(req, res))}), templateEngine)
    }

    fun delete(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
        service.delete(path, accepts, Route(fun(req, res) { function(RouteHandler(req, res)) }), responseTransformer)
    }

    fun head(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
        service.head(path, accepts) {
            req, res -> function(RouteHandler(req, res))
        }
    }

    fun head(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.head(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView { return function(RouteHandler(req, res)) }), templateEngine)
    }

    fun head(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
        service.head(path, accepts, Route(fun(req, res) { function(RouteHandler(req, res)) }), responseTransformer)
    }

    fun trace(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
        service.trace(path, accepts) {
            req, res -> function(RouteHandler(req, res))
        }
    }

    fun trace(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.trace(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView { return function(RouteHandler(req, res)) }), templateEngine)
    }

    fun trace(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
        service.trace(path, accepts, Route(fun(req, res) { function(RouteHandler(req, res)) }), responseTransformer)
    }

    fun options(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
        service.options(path, accepts) {
            req, res -> function(RouteHandler(req, res))
        }
    }

    fun options(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.options(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView { return function(RouteHandler(req, res)) }), templateEngine)
    }

    fun options(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
        service.options(path, accepts, Route(fun(req, res) { function(RouteHandler(req, res))}), responseTransformer)
    }

    fun patch(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
        service.patch(path, accepts) {
            req, res -> function(RouteHandler(req, res))
        }
    }

    fun patch(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.patch(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView { return function(RouteHandler(req, res)) }), templateEngine)
    }

    fun patch(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
        service.patch(path, accepts, Route(fun(req, res) { function(RouteHandler(req, res)) }), responseTransformer)
    }

    fun connect(path: String, accepts: String = DEFAULT_ACCEPT, function: RouteHandler.() -> Any) {
        service.connect(path, accepts) {
            req, res -> function(RouteHandler(req, res))
        }
    }

    fun connect(path: String, accepts: String = DEFAULT_ACCEPT, templateEngine: TemplateEngine, function: RouteHandler.() -> ModelAndView) {
        service.connect(path, accepts, TemplateViewRoute(fun(req, res): ModelAndView { return function(RouteHandler(req, res)) }), templateEngine)
    }

    fun connect(path: String, accepts: String = DEFAULT_ACCEPT, responseTransformer: ResponseTransformer, function: RouteHandler.() -> Any) {
        service.connect(path, accepts, Route(fun(req, res) { function(RouteHandler(req, res)) }), responseTransformer)
    }

    fun before(filter: Filter, accepts: String = DEFAULT_ACCEPT) {
        service.before(filter)
    }

    fun before(path: String? = null, accepts: String = DEFAULT_ACCEPT, function: FilterHandler.() -> Unit) {
        val filter = Filter(fun(req, res) { function(FilterHandler(req, res)) })
        if (path == null) service.before(filter) else service.before(path, accepts, filter)
    }

    fun after(path: String? = null, accepts: String = DEFAULT_ACCEPT, function: FilterHandler.() -> Unit) {
        val filter = Filter(fun(req, res) { function(FilterHandler(req, res)) })
        if (path == null) service.after(filter) else service.after(path, accepts, filter)
    }

    fun afterAfter(path: String? = null, function: FilterHandler.() -> Unit) {
        val filter = Filter(fun(req, res) { function(FilterHandler(req, res)) })
        if (path == null) service.afterAfter(filter) else service.afterAfter(path, filter)
    }
}
