package com.example.onlineshopapp.repository.base

open class BaseRepository {
    protected fun prepareToken(token: String): String {
        var fixedToken = token
        if (!token.lowercase().startsWith("bearer"))
            fixedToken = "Bearer$token"
        return fixedToken
    }
}