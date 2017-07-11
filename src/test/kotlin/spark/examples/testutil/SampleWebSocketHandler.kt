package spark.examples.testutil

import org.eclipse.jetty.websocket.api.Session
import org.eclipse.jetty.websocket.api.annotations.*
import java.io.InputStream

@WebSocket class SampleWebSocketHandler {
    @OnWebSocketConnect
    fun onConnect(s: Session) {
        println("${s.remoteAddress} has opened a WebSocket connection.")
    }

    @OnWebSocketError
    fun onError(s: Session, t: Throwable) {
        println("An error was encountered on ${s.remoteAddress}' connection: $t")
    }

    // Handles text messages by echoing them back to the sender
    @OnWebSocketMessage
    fun onMessage(s: Session, msg: String) {
        println("Message received from ${s.remoteAddress}: $msg")
        s.remote.sendString(msg)
    }

    // Handles binary messages
    @OnWebSocketMessage
    fun onMessage(s: Session, input: InputStream) {
        println("Binary message received from ${s.remoteAddress}")
    }

    @OnWebSocketClose
    fun onClose(s: Session, code: Int, reason: String) {
        println("${s.remoteAddress} has closed their connection: $code ($reason)")
    }

}
