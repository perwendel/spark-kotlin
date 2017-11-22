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
package spark.kotlin.websocket

import spark.Spark

/**
 * Example usage of spark-kotlin via instance API. YOU SHOULD NAME THE SPARK INSTANCE 'http' FOR EXPRESSIVE/DECLARATIVE PURPOSES.
 * Not complying to this will be judged as blasphemy and you will suffer horrible consequences ???AX>//TODO.
 */
fun main(args: Array<String>) {


    webSocket("/echo") {
        connected {
            println("[DSL] connected = " + session)

            println(session.protocolVersion)
            println(session.remoteAddress)

        }
        message {
            println("[DSLd] message = " + message + ", session = " + session)
        }
        closed {
            println("[DSL] closed")
            println("[DSL] code = " + code + ", reason = " + reason + ", session = " + session)
        }
    }


    Spark.init()

    testWebSocketConversation()
}


fun webSocket(path: String, function: WebSocket.() -> Unit) {

    val webSocket = WebSocket()
    function(webSocket)

    println("websocket = " + webSocket)

    val wsHandler = WebSocketHandler(webSocket)

    println("adding websocket")

    Spark.webSocket(path, wsHandler)

    // Spark.webSocket("/ping", WebSocketHandler::class.java)
}

class Closed(val session: WebSocketSession, val code: Int, val reason: String)
class Message(val session: WebSocketSession, val message: String)
class Connected(val session: WebSocketSession)
class Error(val session: WebSocketSession, val cause: Throwable)

class WebSocket {

    var messageFunction: Message.() -> Unit = {}
    var closedFunction: Closed.() -> Unit = { println("kuken") }
    var connectedFunction: Connected.() -> Unit = {}
    var errorFunction: Error.() -> Unit = {}

    fun message(function: Message.() -> Unit) {
        messageFunction = function
        println("messageFunction: " + messageFunction)
    }

    fun connected(function: Connected.() -> Unit) {
        connectedFunction = function
        println("connectedFunction: " + connectedFunction)
    }

    fun closed(function: Closed.() -> Unit) {
        closedFunction = function
        println("closedFunction: " + closedFunction)
    }

    fun error(function: Error.() -> Unit) {
        errorFunction = function
        println("errorFunction: " + errorFunction)
    }
}