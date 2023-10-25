
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

fun main(args: Array<String>) {
    runBlocking {
        printHello()
    }
    val job = GlobalScope.launch {
        repeat(1000000) {
            println(it)
            //delay(100)
        }
    }
    GlobalScope.launch {
        repeat(100_000) {
            if (it == 1000) {
                job.cancel()
                return@repeat
            }
        }
    }
    runBlocking {
        printHello()
    }
}

suspend fun printHello() {
    print("\nHello")
    delay(2000)
    print(" World!")
}