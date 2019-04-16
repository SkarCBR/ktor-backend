package com.mrskar

import com.auth0.jwt.JWT
import com.mrskar.model.SimpleJWT
import com.mrskar.model.exception.InvalidCredentialsException
import com.mrskar.model.request.LoginRegister
import com.mrskar.model.request.PostSnippet
import com.mrskar.model.request.User
import io.ktor.application.call
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.http.ContentType
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.*
import java.util.*

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

        post("/login-register") {
            val post = call.receive<LoginRegister>()
            val user = users.getOrPut(post.user) { User(post.user, post.password) }
            if (user.password != post.password) throw InvalidCredentialsException("Invalid credentials")
            call.respond(mapOf("token" to SimpleJWT())) //Need to sign in the user
        }
    }
}

val users: MutableMap<String, User> = Collections.synchronizedMap(
    listOf(User("mrskar", "1234"))
        .associateBy { it.name }
        .toMutableMap()
)