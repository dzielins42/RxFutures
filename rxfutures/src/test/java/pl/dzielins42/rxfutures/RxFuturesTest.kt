package pl.dzielins42.rxfutures

import org.junit.Test
import pl.dzielins42.rxfutures.asCompletable
import pl.dzielins42.rxfutures.asSingle
import java.util.concurrent.CompletableFuture

class RxFuturesTest {

    @Test
    fun `When CompletableFuture completes, Single completes and emits value`() {
        // Arrange
        val expectedValue = 42
        val future = CompletableFuture.completedFuture(expectedValue)

        // Act
        val testObserver = future.asSingle().test()

        // Assert
        testObserver.assertResult(expectedValue)
    }

    @Test
    fun `When CompletableFuture (Async) completes, Single completes and emits value`() {
        // Arrange
        val expectedValue = 42
        val waitTimeMs = 200L
        val future = CompletableFuture.supplyAsync {
            expectedValue
        }

        // Act
        val testObserver = future.asSingle().test()
        Thread.sleep(waitTimeMs)

        // Assert
        testObserver.assertResult(expectedValue)
    }

    @Test
    fun `When CompletableFuture (Void) completes, Completable completes`() {
        // Arrange
        val expectedValue = 42
        val future: CompletableFuture<Void> = CompletableFuture.completedFuture(null)

        // Act
        val testObserver = future.asCompletable().test()

        // Assert
        testObserver.assertComplete().assertNoErrors()
    }

    @Test
    fun `When CompletableFuture fails, Single calls onError`() {
        // Arrange
        val expectedError = Exception()
        val future = CompletableFuture<Int>()

        // Act
        val testObserver = future.asSingle().test()
        future.completeExceptionally(expectedError)

        // Assert
        testObserver.assertError(expectedError)
    }

    @Test
    fun `When CompletableFuture (Void) completes, Completable calls onError`() {
        // Arrange
        val expectedError = Exception()
        val future: CompletableFuture<Void> = CompletableFuture()

        // Act
        val testObserver = future.asCompletable().test()
        future.completeExceptionally(expectedError)

        // Assert
        testObserver.assertError(expectedError)
    }
}