package creationalPatterns

object MySingleton {
    var name: String = ""
    var age: Int = 0

    @Synchronized fun printInfo() {
        println("${this.hashCode()} ${this.name} ${this.age}")
    }
}

fun main() {
    for(i in 1 .. 5) {
        MySingleton.name = "박명수"
        MySingleton.age = 33
        Thread {
            val currentThreadId = Thread.currentThread().id
            println("Current thread ID is: $currentThreadId")
            MySingleton.age++
            MySingleton.printInfo()
        }.start()
    }

    for(i in 1 .. 5) {
        Thread {
            val currentThreadId = Thread.currentThread().id
            println("Current thread ID is: $currentThreadId")
            MySingleton.age++
            MySingleton.printInfo()
        }.start()
    }
}