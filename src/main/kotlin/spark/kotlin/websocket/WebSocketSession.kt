package spark.kotlin.websocket

import org.eclipse.jetty.websocket.api.CloseStatus
import org.eclipse.jetty.websocket.api.RemoteEndpoint
import org.eclipse.jetty.websocket.api.Session

/**
 * Created by Per Wendel on 2017-11-20.
 */
class WebSocketSession(val session: Session) {

    // Extra DSL BEGIN
    fun send(message: String) {
        session!!.remote.sendString(message)
    }

    // END

    val remote: RemoteEndpoint
        get() {
            try {
                return session.remote
            } catch (exception: org.eclipse.jetty.websocket.api.WebSocketException) {
                throw WebSocketException(exception.message)
            }
        }

    var idleTimeout: Long
        get() {
            return session.idleTimeout
        }
        set(value) {
            session.idleTimeout = value
        }

    val isOpen: Boolean
        get() {
            println("session.isOpen = ${session.isOpen}")
            return session.isOpen
        }

    val localAddress = session.localAddress
    val protocolVersion = session.protocolVersion
    val upgradeResponse = session.upgradeResponse
    val upgradeRequest = session.upgradeRequest
    val policy = session.policy
    val isSecure = session.isSecure
    val remoteAddress = session.remoteAddress

    fun disconnect() {
        return session.disconnect()
    }

    fun suspend(): Suspension {
        return Suspension(session.suspend())
    }

    fun close() {
        return session.close()
    }

    fun close(closeStatus: CloseStatus?) {
        return session.close(closeStatus)
    }

    fun close(statusCode: Int, reason: String?) {
        return session.close(statusCode, reason)
    }

    fun raw(): Session {
        return session
    }

}

    
