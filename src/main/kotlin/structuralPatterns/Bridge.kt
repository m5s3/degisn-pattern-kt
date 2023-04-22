package structuralPatterns

//interface Color {
//    fun applyColor()
//}
//
//abstract class Shape(open var color: Color) {
//    abstract fun applyColor()
//}
//
//class Triangle(override var color: Color) : Shape(color) {
//    override fun applyColor() {
//        println("Triangle filled with color")
//        color.applyColor();
//    }
//}
//
//class Pentagon(override var color: Color) : Shape(color) {
//    override fun applyColor() {
//        println("Pentagon filled with color")
//        color.applyColor()
//    }
//}
//
//class RedColor : Color {
//    override fun applyColor() {
//        println("red.")
//    }
//}
//
//class GreenColor : Color {
//    override fun applyColor() {
//        println("green.")
//    }
//}
//fun main() {
//    val triangle = Triangle(RedColor())
//    triangle.applyColor()
//    val pentagon = Pentagon(GreenColor())
//    pentagon.applyColor()
//}

interface Engine {
    fun accelerate(): String
}

class V6Engine : Engine {
    override fun accelerate() = "Vroom! Vroom! V6 Engine!"
}

class V8Engine : Engine {
    override fun accelerate() = "Vroom! Vroom! V8 Engine!"
}

sealed class Car {
    abstract val engine: Engine
    abstract fun drive(): String
}

data class SportCar(override val engine: Engine) : Car() {
    override fun drive() = "Driving a sports car, ${engine.accelerate()}"
}

data class SUV(override val engine: Engine) : Car() {
    override fun drive() = "Driving an SUV, ${engine.accelerate()}"
}

fun main() {
    val v6Engine = V6Engine()
    val v8Engine = V8Engine()

    val sportCar = SportCar(v8Engine)
    val suv = SUV(v6Engine)

    println(sportCar.drive())
    println(suv.drive())
}

