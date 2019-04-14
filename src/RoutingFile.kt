package com.mrskar

import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route

fun Routing.getRouting(): Route {
    return this.route("/") {
        get{
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }
    }
}