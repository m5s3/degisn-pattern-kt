package behavioralPatterns

import java.util.*

/**
 * Command
 * 실행될 기능에 대한 인터페이스.
 * 실행되는 기능들을 종합하는 execute를 선언한다.
 *
 */
interface Command {
    fun execute()
    fun undo()
}

class AdditionCommand(
    private val calculator: Calculator,
    private val operand: Double,
) : Command {
    override fun execute() {
        calculator.add(operand)
    }

    override fun undo() {
        calculator.subtract(operand)
    }
}

class SubtractionCommand(
    private val calculator: Calculator,
    private val operand: Double,
) : Command {
    override fun execute() {
        calculator.subtract(operand)
    }

    override fun undo() {
        calculator.add(operand)
    }
}

class Calculator(
    var currentValue: Double = 0.0
) {
    fun add(operand: Double) {
        currentValue += operand
    }

    fun subtract(operand: Double) {
        currentValue -= operand
    }
}

class CalculatorInvoker(
    private val commandHistory: Stack<Command> = Stack<Command>()
) {
   fun executeCommand(command: Command) {
       command.execute()
       commandHistory.push(command)
   }

    fun undoLastCommand() {
        if (!commandHistory.empty()) {
            val lastCommand = commandHistory.pop()
            lastCommand.undo()
        }
    }
}


/**
 * Receiver
 * 수행할 수 있는 작업을 정의 인터페이스
 *
 */
interface Light {
    var brightness: Int
    fun turnOn()
    fun turnOff()
    fun dim(dimLevel: Int)
}

/**
 * Receiver
 * ConcreteCommand 의 execute 를 구현하는 클래스.
 * 기능을 실행하기 위해 필요한 수신자 인터페이스 구현체이다.
 *
 */
class SmartLight(
    override var brightness: Int = 0
) : Light {
    override fun turnOn() {
        brightness = 100
        println("Light is turned on. Brightness is $brightness%.")
    }

    override fun turnOff() {
        brightness = 0
        println("Light is turned off.")
    }

    override fun dim(dimLevel: Int) {
        if (brightness > 100) {
            println("Brightness Max Level is 100")
            return
        }

        if (brightness in 0..100) {

            brightness -= dimLevel
            println("Brightness is now $brightness%.")
            return
        }

        println("Cannot dim the light when it is turned off.")
    }
}

/**
 * ConcreteCommand
 * 실제로 실행되는 기능을 구현한다.
 *
 */
class TurnOnCommand(
    private val light: Light
) : Command {
    override fun execute() {
        light.turnOn()
    }

    override fun undo() {
        light.turnOff()
    }
}

/**
 * ConcreteCommand
 * 실제로 실행되는 기능을 구현한다.
 *
 */
class TurnOffCommand(
    private val light: Light
) : Command {
    override fun execute() {
        light.turnOff()
    }

    override fun undo() {
        light.turnOn()
    }
}

/**
 * ConcreteCommand
 * 실제로 실행되는 기능을 구현한다.
 *
 */
class DimCommand(
    private val light: Light,
    private val dimLevel: Int,
) : Command {
    private var previousBrightness = 0
    override fun execute() {
        //previousBrightness = light.brightness
        light.dim(dimLevel)
    }

    override fun undo() {
//        light.brightness = previousBrightness
        light.dim(-dimLevel)
    }
}

/**
 * Invoker
 * 해당 요청에 따르는 기능의 실행을 요청하는 호출자 클래스이다.
 *
 */
class SmartHomeInvoker(
    private val commands: MutableList<Command> = mutableListOf()
) {
    fun executeCommand(command: Command) {
        command.execute()
        commands.add(command)
    }

    fun undoLastCommand() {
        if (commands.isNotEmpty()) {
            val lastCommand = commands.removeLast()
            lastCommand.undo()
            return
        }
        println("No commands to undo.")
    }
}

/**
 * Client
 * 요청을 전달하는 클라이언트이다.
 *
 */
class SmartHomeRemote(
    private val light: Light,
    private val smartHomeInvoker: SmartHomeInvoker,
) {
    fun turnOn() {
        val command = TurnOnCommand(light)
        smartHomeInvoker.executeCommand(command)
    }

    fun turnOff() {
        val command = TurnOffCommand(light)
        smartHomeInvoker.executeCommand(command)
    }

    fun dim() {
        val command = DimCommand(light, 10)
        smartHomeInvoker.executeCommand(command)
    }

    fun undo() {
        smartHomeInvoker.undoLastCommand()
    }
}

fun main() {
//    val calculator = Calculator()
//    val invoker = CalculatorInvoker()
//
//    invoker.executeCommand(AdditionCommand(calculator, 10.0))
//    invoker.executeCommand(SubtractionCommand(calculator, 5.0))
//    invoker.undoLastCommand()
//
//    println("Current value: ${calculator.currentValue}") // Output: Current value: 10.0

    val smartLight = SmartLight()
    val smartLightInvoker = SmartHomeInvoker()
    val remote = SmartHomeRemote(smartLight, smartLightInvoker)

    remote.turnOn()  // Output: "Light is turned on. Brightness is 100%."

    remote.dim()     // Output: "Brightness is now 90%."
    remote.dim()     // Output: "Brightness is now 80%."

    //remote.turnOff()

    remote.undo()    // Output: "Light is turned on. Brightness is 80%."
    remote.undo()    // Output: "Light is turned on. Brightness is 90%."
    remote.undo()    // Output: "Light is turned on. Brightness is 100%."
    remote.undo()    // Output: "No commands to
}

