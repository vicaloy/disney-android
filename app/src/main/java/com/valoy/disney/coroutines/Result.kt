package com.valoy.disney.coroutines

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.cancellation.CancellationException


inline fun <R> safeRunCatching(block: () -> R): Result<R> = try {
    Result.success(block())
} catch (e: CancellationException) {
    throw e
} catch (e: SocketTimeoutException) {
    Result.failure(NetworkFailureException(cause = e))
} catch (e: UnknownHostException) {
    Result.failure(NetworkFailureException(cause = e))
} catch (e: ConnectException) {
    Result.failure(NetworkFailureException(cause = e))
} catch (e: Throwable) {
    Result.failure(e)
}
