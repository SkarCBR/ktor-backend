package com.mrskar.model.request

data class PostSnippet(val data: PostSnippet.Text) {
    data class Text(val text: String)
}