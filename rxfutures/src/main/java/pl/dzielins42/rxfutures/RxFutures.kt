package pl.dzielins42.rxfutures

import io.reactivex.Completable
import io.reactivex.Single
import java.util.concurrent.CompletableFuture

/**
 * Converts `CompletableFuture` to [Single] that will emit its value.
 */
fun <T> CompletableFuture<T>.asSingle(): Single<T> {
    return Single.create { emitter ->
        this.whenComplete { result, error ->
            if (error != null) {
                emitter.onError(error)
            } else {
                emitter.onSuccess(result)
            }
        }
    }
}

/**
 * Converts `CompletableFuture<Void>` to [Completable].
 */
fun CompletableFuture<Void>.asCompletable(): Completable {
    return Completable.create { emitter ->
        this.whenComplete { _, error ->
            if (error != null) {
                emitter.onError(error)
            } else {
                emitter.onComplete()
            }
        }
    }
}