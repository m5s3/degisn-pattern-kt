package creationalPatterns

interface Animal {
    val type: String
    fun eat(): Animal
    fun bark(): Animal

    fun play(): Animal
}

data class Dog(
    override val type: String
) : Animal {
    override fun eat() = apply {
        println("${type}가 왈왈 먹는다")
    }
    override fun bark() = apply {
        println("${type}가 왈왈 짓는다")
    }

    override fun play() = apply {
        println("${type}가 신나게 논다")
    }
    class Builder {
        private var type: String = ""

        fun type(type: String) = apply {
            this.type = type
        }

        fun build() = Dog(type)
    }
}

data class Cat(
    override val type: String
) : Animal {
    override fun eat() = apply {
        println("${type}가 얍얍")
    }
    override fun bark() = apply {
        println("${type}가 야옹야옹")
    }
    override fun play() = apply {
        println("${type}가 신나게 논다")
    }
    class Builder {
        private var type: String = ""

        fun type(type: String) = apply {
            this.type = type
        }

        fun build() = Cat(type)
    }
}

enum class AnimalType {
    DOG,
    CAT
}

object AnimalFactory {
    fun createAnimal(animalType: AnimalType): Animal {
        return when (animalType) {
            AnimalType.DOG -> Dog.Builder().type("개").build()
            AnimalType.CAT -> Cat.Builder().type("고양이").build()
        }
    }
}

fun main() {
    val animal1 = AnimalFactory.createAnimal(AnimalType.DOG)
    val animal2 = AnimalFactory.createAnimal(AnimalType.CAT)
    val list = listOf(animal1, animal2)
    for (a: Animal in list) {
        println(a.type)
        a.play().eat().bark()
    }
}