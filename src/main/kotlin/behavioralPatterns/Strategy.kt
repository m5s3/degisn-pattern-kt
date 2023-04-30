package behavioralPatterns

/**
 * Strategy
 * 인터페이스나 추상 클래스로 외부에서 동일한 방식으로 알고리즘을 호출하는 방법을 명시한다.
 *
 */
interface PaymentStrategy {
    fun pay(amount: Double)
}

/**
 * ConcreteStrategy
 * 스트래티지 패턴에서 명시한 알고리즘을 실제로 구현한 클래스이다.
 *
 */
class CreditCardPaymentStrategy(
    private val cardNumber: String,
    private val expiryDate: String,
    private val cvv: String
) : PaymentStrategy {
    override fun pay(amount: Double) {
        println("Paying $amount with credit card $cardNumber")
    }
}

/**
 * ConcreteStrategy
 * 스트래티지 패턴에서 명시한 알고리즘을 실제로 구현한 클래스이다.
 *
 */
class PayPalPaymentStrategy(
    private val email: String,
    private val password: String
) : PaymentStrategy {
    override fun pay(amount: Double) {
        println("Paying $amount with PayPal account $email")
    }
}

/**
 * Context
 * 스트래티지 패턴을 이용하는 역할을 수행한다.
 * 필요에 따라 동적으로 구체적인 전략을 바꿀 수 있도록 setter 메서드(‘집약 관계’)를 제공한다.
 *
 */
class PaymentContext(
    private var paymentStrategy: PaymentStrategy
) {
    fun setPaymentStrategy(paymentStrategy: PaymentStrategy) {
        this.paymentStrategy = paymentStrategy
    }

    fun processPayment(amount: Double) {
        this.paymentStrategy.pay(amount = amount)
    }
}

fun main() {
    val paymentContext = PaymentContext(CreditCardPaymentStrategy("1234 5678 9012 3456", "12/24", "123"))
    paymentContext.processPayment(100.0)

    paymentContext.setPaymentStrategy(PayPalPaymentStrategy("john.doe@example.com", "password"))
    paymentContext.processPayment(50.0)
}
