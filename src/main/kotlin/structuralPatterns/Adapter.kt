package structuralPatterns

interface Duck {
    fun quack()
    fun fly()
}

class MallardDuck : Duck {
    override fun quack() {
        println("Quack")
    }

    override fun fly() {
        println("I'm flying")
    }
}

interface Turkey {
    fun gobble()
    fun fly()
}

class WildTurkey : Turkey {
    override fun gobble() {
        println("Gobble gobble")
    }

    override fun fly() {
        println("I'm flying a short distance")
    }
}

class TurkeyAdapter(private val turkey: Turkey) : Duck {

    override fun quack() {
        turkey.gobble()
    }

    override fun fly() {
        turkey.fly()
    }
}

fun main() {
    val turkey = WildTurkey()
    val turkeyAdapter = TurkeyAdapter(turkey)

    val duck1 = MallardDuck()
    val duck2 = MallardDuck()

    for (duck: Duck in listOf<Duck>(duck1, duck2,turkeyAdapter)) {
        duck.quack()
    }
}