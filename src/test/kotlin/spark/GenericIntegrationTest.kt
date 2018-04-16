package spark

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import spark.kotlin.Http
import spark.kotlin.halt
import spark.kotlin.ignite
import spark.kotlin.notFound
import spark.utils.httpGet
import kotlin.test.assertEquals

class GenericIntegrationTest {

    companion object {

        lateinit var http: Http

        @BeforeClass
        @JvmStatic
        fun setup() {
            http = ignite {
                port = 1234
            }

            notFound {
                "Proprietary 404"
            }

            http.before("/protected/*") {
                halt(401, "Protected")
            }

            http.get("/hello") {
                "hello"
            }

            http.get("/say/my/:name") {
                params["name"]
            }

            http.get("/query") {
                queryParams["ping"]
            }

            http.get("/nothing") {
                status(404)
                "nothing"
            }

            http.after("/hello") {
                response.header("", "")
            }

            http.awaitInit()
        }

        @AfterClass
        @JvmStatic
        fun teardown() {
            http.stop()
        }

    }

    @Test
    fun testHello() {
        val response = httpGet("http://localhost:1234/hello")
        assertEquals("hello", response.message)
    }

    @Test
    fun testPathParams() {
        val response = httpGet("http://localhost:1234/say/my/spinoza")
        assertEquals("spinoza", response.message)
    }

    @Test
    fun testQueryParams() {
        val response = httpGet("http://localhost:1234/query?ping=pong")
        assertEquals("pong", response.message)
    }

    @Test
    fun testImplicit404() {
        val response = httpGet("http://localhost:1234/abcdefghijklmnopqrstuvxyz")
        assertEquals(404, response.code)
        assertEquals("Proprietary 404", response.message)
    }

    @Test
    fun testExplicit404() {
        val response = httpGet("http://localhost:1234/nothing")
        assertEquals(404, response.code)
        assertEquals("nothing", response.message)
    }

    @Test
    fun testBeforeFilter() {
        val response = httpGet("http://localhost:1234/protected/secrets")

        assertEquals(401, response.code)
        assertEquals("Protected", response.message)
    }

    @Test
    fun testAfterFilter() {
        val response = httpGet("http://localhost:1234/hello")

        assertEquals("Protected", response.message)
    }

}