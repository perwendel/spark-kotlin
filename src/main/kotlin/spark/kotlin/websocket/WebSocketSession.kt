package spark.kotlin.websocket

import org.eclipse.jetty.websocket.api.CloseStatus
import org.eclipse.jetty.websocket.api.RemoteEndpoint
import org.eclipse.jetty.websocket.api.Session
import org.eclipse.jetty.websocket.api.SuspendToken

/**
 * Created by Per Wendel on 2017-11-20.
 */
class WebSocketSession(val session: Session) {

    // TODO: Fix if closed stuff
    val remote: RemoteEndpoint? // = session.remote

    val localAddress = session.localAddress
    val protocolVersion = session.protocolVersion
    val upgradeResponse = session.upgradeResponse
    val upgradeRequest = session.upgradeRequest
    val policy = session.policy
    val idleTimeout = session.idleTimeout
    val isSecure = session.isSecure
    val remoteAddress = session.remoteAddress

    init {
        if (session.isOpen)
            remote = session.remote
        else
            remote = null
    }

    fun disconnect() {
        return session.disconnect()
    }

    fun setIdleTimeout(ms: Long) {
        session.idleTimeout = ms
    }

    fun suspend(): SuspendToken {
        return session.suspend()
    }

    fun isOpen(): Boolean {
        return session.isOpen
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

    
