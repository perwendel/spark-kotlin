package spark.examples.testutil

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest
import org.eclipse.jetty.websocket.client.WebSocketClient
import spark.kotlin.stop
import spark.kotlin.websocket.WebSocketTestClient
import java.lang.Thread.sleep
import java.net.URI
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

/**
 * Created by Per Wendel on 2017-11-20.
 */
@Throws(Exception::class)
fun testWebSocketConversation(stopFunction: () -> Unit) {

    thread(start = true) {

        sleep(1000)

        val uri = "ws://localhost:4567/echo"
        val client = WebSocketClient()
        val ws = WebSocketTestClient()

        try {
            client.start()
            client.connect(ws, URI.create(uri), ClientUpgradeRequest())
            ws.awaitClose(5, TimeUnit.SECONDS)
        } finally {
            client.stop()
        }

        thread(start = true) {
            sleep(1000)
            stopFunction.invoke()
        }
    }


}