package spark.kotlin.websocket

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest
import org.eclipse.jetty.websocket.client.WebSocketClient
import java.net.URI
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

/**
 * Created by Per Wendel on 2017-11-20.
 */
@Throws(Exception::class)
fun testWebSocketConversation() {

    thread(start = true) {

        Thread.sleep(500)

        val uri = "ws://localhost:4567/echo"
        val client = WebSocketClient()
        val ws = WebSocketTestClient()

        try {
            client.start()
            client.connect(ws, URI.create(uri), ClientUpgradeRequest())
            ws.awaitClose(30, TimeUnit.SECONDS)
        } finally {
            client.stop()
        }

    }


}