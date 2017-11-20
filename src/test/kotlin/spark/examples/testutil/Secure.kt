package spark.examples.testutil

fun keyStoreLocation(): String {
    return "./src/test/resources/keystore.jks"
}

fun keystorePass(): String {
    return "password"
}

fun trustStoreLocation(): String {
    return keyStoreLocation()
}

fun trustStorePassword(): String {
    return keystorePass()
}
