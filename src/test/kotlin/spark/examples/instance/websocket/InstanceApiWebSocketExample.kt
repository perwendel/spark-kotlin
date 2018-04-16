/*
 * Copyright 2017 Per Wendel, Love Löfdahl
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
package spark.examples.instance.websocket

import spark.examples.testutil.testWebSocketConversation
import spark.kotlin.ignite

/**
 * Example usage of spark-kotlin via instance API. YOU SHOULD NAME THE SPARK INSTANCE 'http' FOR EXPRESSIVE/DECLARATIVE PURPOSES.
 * Not complying to this will be handled accordingly and your code will suffer horrible consequences.
 */
fun main(args: Array<String>) {

    val http = ignite {
        port = 4567
    }

    http.webSocket("/echo") {
        opened {
            println("[Spinoza] opened = " + session)
            send("you successfully opened me")
        }
        received {
            println("[Spinoza] message = $message, session = $session")
            send("pong: " + message)
        }
        closed {
            println("[Spinoza] Closed, code = $code, reason = $reason, session = $session")
        }
        error {
            println("[Spinoza] Error")
        }
    }

    http.init()

    testWebSocketConversation {
        http.stop()
    }
}


