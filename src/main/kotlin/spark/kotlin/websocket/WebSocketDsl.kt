/*
 * Copyright 2018 Per Wendel
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

class Closed(val session: WebSocketSession, val code: Int, val reason: String)
class Message(session: WebSocketSession, val message: String) : Base(session)
class Opened(session: WebSocketSession) : Base(session)
class Error(val session: WebSocketSession, val cause: Throwable)

open class Base(val session: WebSocketSession) {
    fun send(message: String) {
        session.remote.sendString(message)
    }
}

class WebSocket {

    var receivedFunction: Message.() -> Unit = {}
    var closedFunction: Closed.() -> Unit = {}
    var openedFunction: Opened.() -> Unit = {}
    var errorFunction: Error.() -> Unit = {}

    fun received(function: Message.() -> Unit) {
        receivedFunction = function
    }

    fun opened(function: Opened.() -> Unit) {
        openedFunction = function
    }

    fun closed(function: Closed.() -> Unit) {
        closedFunction = function
    }

    fun error(function: Error.() -> Unit) {
        errorFunction = function
    }
}