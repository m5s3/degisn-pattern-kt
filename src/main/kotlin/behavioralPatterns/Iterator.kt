package behavioralPatterns

import kotlin.collections.Iterator

data class Car(val brand: String)

class CarIterable(val cars: List<Car> = listOf()) : Iterable<Car> {
    override fun iterator(): Iterator<Car> = CarIterator(cars)
}

class CarIterator(val cars: List<Car> = listOf(), var index: Int = 0) : Iterator<Car> {
    override fun hasNext(): Boolean = cars.size > index

    override fun next(): Car = cars[index++]
}


fun main() {
    val carIterable = CarIterable(listOf(Car("람보르기니"), Car("페라리")))

    val iterator = carIterable.iterator()

    while (iterator.hasNext()) {
        println("브랜드 ${iterator.next()}")
    }

    val iterator2 = carIterable.iterator()

    while (iterator2.hasNext()) {
        println("브랜드 ${iterator2.next()}")
    }
}