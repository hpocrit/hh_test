package ru.kpfu.itis.gabdullina.hh.list.utils

import kotlinx.coroutines.CancellationException

object UtilityService {
    inline fun <R> runSuspendCatching(block: () -> R): Result<R> {
        return try {
            Result.success(block())
        } catch(c: CancellationException) {
            throw c
        } catch (e: Throwable) {
            error(e.message.toString() + e.cause.toString())
            Result.failure(e)
        }
    }
}
