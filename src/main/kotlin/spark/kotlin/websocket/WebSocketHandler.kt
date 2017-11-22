package spark.kotlin.websocket

/**
 * Created by Per Wendel on 2017-11-20.
 */

import org.eclipse.jetty.websocket.api.Session
import org.eclipse.jetty.websocket.api.annotations.*
import org.eclipse.jetty.websocket.api.annotations.WebSocket
import java.io.IOException

@WebSocket
class WebSocketHandler(val webSocket: spark.kotlin.websocket.WebSocket) {

    @OnWebSocketConnect
    fun connected(session: Session) {
        println("[WebSocketHandler] on opened")
        webSocket.connectedFunction(Connected(WebSocketSession(session)))
    }

    @OnWebSocketClose
    fun closed(session: Session, statusCode: Int, reason: String) {
        println("[WebSocketHandler] closed")

        try {
            webSocket.closedFunction(Closed(WebSocketSession(session), statusCode, reason))
        } catch (th: Throwable) {
            th.printStackTrace()
        }

        println("[WSH] after")
    }

    @OnWebSocketMessage
    @Throws(IOException::class)
    fun message(session: Session, message: String) {
        println("[WebSocketHandler] Got: " + message)
        webSocket.messageFunction(Message(WebSocketSession(session), message))

        // session!!.remote.sendString(message)
    }

    @OnWebSocketError
    fun error(session: Session, cause: Throwable) {
        println("[WebSocketHandler] Error: " + cause)
        webSocket.errorFunction(Error(WebSocketSession(session), cause))
    }
}