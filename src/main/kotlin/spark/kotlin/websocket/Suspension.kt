package spark.kotlin.websocket

import org.eclipse.jetty.websocket.api.SuspendToken

class Suspension(private val token: SuspendToken) {

    /**
     * Lifts the suspended connection.
     */
    fun lift() {
        token.resume()
    }

    fun raw(): SuspendToken {
        return token;
    }

}