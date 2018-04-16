/*
 * Copyright 2017 Per Wendel
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
package spark.kotlin

import spark.Service
import java.util.concurrent.TimeUnit

const val NIL = "Nil"

// Initialization DSL
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

// Static files DSL
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

// Thread pool DSL
data class ThreadPool(var maxThreads: Int = -1, var minThreads: Int = -1, var idleTimeoutMillis: Int = -1)

// Secure (HTTPS) DSL
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

// Time related extension properties on Integers, used for initialization of Spark.
val Int.days: Seconds
    get() {
        println("TimeUnit.DAYS.toSeconds(this.toLong()): " + TimeUnit.DAYS.toSeconds(this.toLong()))
        return Seconds(inSeconds = TimeUnit.DAYS.toSeconds(this.toLong()))
    }

val Int.hours: Seconds
    get() {
        println("TimeUnit.HOURS.toSeconds(this.toLong()): " + TimeUnit.HOURS.toSeconds(this.toLong()))
        return Seconds(inSeconds = TimeUnit.HOURS.toSeconds(this.toLong()))
    }

val Int.minutes: Seconds
    get() {
        println("TimeUnit.MINUTES.toSeconds(this.toLong()): " + TimeUnit.MINUTES.toSeconds(this.toLong()))
        return Seconds(inSeconds = TimeUnit.MINUTES.toSeconds(this.toLong()))
    }

val Int.seconds: Seconds
    get() {
        return Seconds(inSeconds = this.toLong())
    }

data class Seconds(val inSeconds: Long = 0)