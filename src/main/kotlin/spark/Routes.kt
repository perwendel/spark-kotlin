/*
 * Copyright 2017 Love Löfdahl
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

open class RequestHandler {
    val start: Long = System.currentTimeMillis()

    fun requestTime(): Long {
        return System.currentTimeMillis() - start
    }
}

class RouteHandler(val request: Request, val response: Response) : RequestHandler()
class FilterHandler(val request: Request, val response: Response) : RequestHandler()

/**
 * The route class that takes a Spark service and wraps the route methods to enable fancy syntax
 * with access to request and response parameters in the route code.
 *
 * @para service The [Spark] service that will be wrapped.
 */
class Routes(val service: Service) {

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
