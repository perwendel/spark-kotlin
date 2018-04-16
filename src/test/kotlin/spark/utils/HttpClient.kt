package spark.utils

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Response(val code: Int, val message: String)

fun httpGet(url: String): Response {

    val obj = URL(url)

    with(obj.openConnection() as HttpURLConnection) {
        // optional default is GET
        requestMethod = "GET"


        println("\nSending 'GET' request to URL : $url")
        println("Response Code : $responseCode")

        val stream: InputStream

        if (responseCode == 200) {
            stream = inputStream
        } else {
            stream = errorStream
        }

        BufferedReader(InputStreamReader(stream)).use {
            val response = StringBuffer()

            var inputLine = it.readLine()
            while (inputLine != null) {
                response.append(inputLine)
                inputLine = it.readLine()
            }

            println(response.toString())
            return Response(responseCode, response.toString())
        }
    }
}