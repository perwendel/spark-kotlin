package spark.kotlin

import spark.Redirect

class Redirects {

    var anys: Map<String, String> = mutableMapOf()
    var gets: Map<String, String> = mutableMapOf()
    var posts: Map<String, String> = mutableMapOf()
    var puts: Map<String, String> = mutableMapOf()
    var deletes: Map<String, String> = mutableMapOf()

    fun any(vararg pairs: Pair<String, String>) {
        anys = pairs.toMap()
    }

    fun get(vararg pairs: Pair<String, String>) {
        gets = pairs.toMap()
    }

    fun post(vararg pairs: Pair<String, String>) {
        posts = pairs.toMap()
    }

    fun put(vararg pairs: Pair<String, String>) {
        puts = pairs.toMap()
    }

    fun delete(vararg pairs: Pair<String, String>) {
        deletes = pairs.toMap()
    }

    infix fun applyOn(redirect: Redirect) {
        for (paths in anys) {
            redirect.any(paths.key, paths.value)
        }
        for (paths in gets) {
            redirect.get(paths.key, paths.value)
        }
        for (paths in posts) {
            redirect.post(paths.key, paths.value)
        }
        for (paths in puts) {
            redirect.put(paths.key, paths.value)
        }
        for (paths in deletes) {
            redirect.delete(paths.key, paths.value)
        }
    }
}

