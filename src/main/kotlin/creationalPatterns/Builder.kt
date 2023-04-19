package creationalPatterns


data class Car(
    val brand: String,
    val model: String,
    val year: Int,
    val price: Int
) {
    class Builder {
        private var brand: String = ""
        private var model: String = ""
        private var year: Int = 0
        private var price: Int = 0

        fun brand(brand: String) = apply {
            this.brand = brand
        }
        fun model(model: String) = apply { this.model = model }
        fun year(year: Int) = apply {this.year = year}
        fun price(price: Int) = apply {this.price = price}

        fun build() = Car(brand, model, year, price)
    }
}

fun main() {
    val car = Car.Builder()
        .brand("Tesla")
        .model("Model S")
        .year(2022)
        .price(100000)
        .build()
    println(car.toString())
}