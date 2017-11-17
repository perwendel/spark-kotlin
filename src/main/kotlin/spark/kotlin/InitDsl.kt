package spark.kotlin

import spark.Service

const val NIL = "Nil"

data class InitParams(
        var port: Int = Service.SPARK_DEFAULT_PORT,
        var ipAddress: String = "0.0.0.0",
        var pool: ThreadPool,
        val secure: Secure,
        val static: StaticFiles) {

    fun threadPool(function: ThreadPool.() -> Unit) {
        function(pool)
    }

    fun secure(function: Secure.() -> Unit) {
        function(secure)
    }

    fun staticFiles(function: StaticFiles.() -> Unit) {
        function(static)
    }

}

data class StaticFiles(
        var location: String = NIL,
        var externalLocation: String = NIL,
        var expiryTime: Seconds = Seconds(-1)) {

    var headers: Map<String, String> = mutableMapOf()
    var mimeTypes: Map<String, String> = mutableMapOf()

    fun headers(vararg pairs: Pair<String, String>) {
        headers = pairs.toMap()
    }

    fun mimeTypes(vararg pairs: Pair<String, String>) {
        mimeTypes = pairs.toMap()
    }

}

data class ThreadPool(var maxThreads: Int = -1, var minThreads: Int = -1, var idleTimeoutMillis: Int = -1)

data class Secure(var keystore: Store = Store(),
                  var truststore: Store = Store(),
                  var needsClientCert: Boolean = false) {

    fun keystore(function: Store.() -> Unit) {
        function(keystore)
    }

    fun truststore(function: Store.() -> Unit) {
        function(truststore)
    }

}

data class Store(var file: String = NIL, var password: String = NIL)

data class Seconds(val inSeconds: Long = 0)

val Int.seconds: Seconds
    get() {
        return Seconds(inSeconds = this.toLong())
    }