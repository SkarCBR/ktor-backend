package com.mrskar

import com.mrskar.model.request.PostSnippet
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.*

fun Routing.getRouting(): Route {
    return this.route("/") {
        get {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/json/jackson") {
            call.respond(mapOf("hello" to "world"))
        }

        route("/post") {
            post {
                val post = call.receive<PostSnippet>()
                call.respond(mapOf("OK" to true, "text" to post.data.text))
            }
        }
    }
}