package com.example

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val client = HttpClient(CIO) {
            install(Auth) {
                basic {
                    credentials {
                        BasicAuthCredentials(username = "jetbrains", password = "foobar")
                    }
                    realm = "Access to the '/' path"
                }
            }
        }
        val response: HttpResponse = client.get("http://0.0.0.0:8080/")
        println(response.bodyAsText())
    }
}