package com.valoy.disney.coroutines

class NetworkFailureException(
    val userMessage: String = "",
    override val cause: Throwable? = null,
) : Exception(cause?.message, cause)
