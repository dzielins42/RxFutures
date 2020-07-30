# RxFutures
Small library to convert from [CompletableFuture](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html) to RxJava2.

[![Release](https://jitpack.io/v/dzielins42/RxFutures.svg)](https://jitpack.io/#dzielins42/RxFutures)

## Usage

1. [Setup with JitPack](https://jitpack.io/#dzielins42/RxFutures)
2. Use in code:
```
val future = CompletableFuture.completedFuture(42)
future.asSingle().subscribe{ theAnswer -> searchQuestion(theAnswer) }
```

### Java
RxFutures is build on kotlin extension functions, in order to use it in Java you need to use `RxFuturesKt` as follows:
```
CompletableFuture<Integer> future = CompletableFuture.completedFuture(42);
RxFuturesKt.asSingle(future).subscribe(theAnswer -> searchQuestion(theAnswer));
```
