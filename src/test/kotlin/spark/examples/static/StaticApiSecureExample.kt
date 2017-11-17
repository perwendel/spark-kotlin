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
package spark.examples.static

import spark.examples.testutil.keyStoreLocation
import spark.examples.testutil.keystorePass
import spark.examples.testutil.trustStoreLocation
import spark.examples.testutil.trustStorePassword
import spark.kotlin.config
import spark.kotlin.get
import spark.kotlin.notFound

/**
 * Example usage of spark-kotlin via STATIC API.
 */
fun main(args: Array<String>) {

    config {
        port = 4321
        secure {
            keystore {
                file = keyStoreLocation()
                password = keystorePass()
            }
            truststore {
                file = trustStoreLocation()
                password = trustStorePassword()
            }
        }
    }

    // when accessing don't forget https
    get("/hello") {
        "Hello Secure Static Spark Kotlin"
    }

    notFound {
        "there's nothing here"
    }

}

