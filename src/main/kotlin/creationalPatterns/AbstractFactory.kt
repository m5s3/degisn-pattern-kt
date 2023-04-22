package creationalPatterns

interface Payment {
    fun execute()
}

interface Refund {
    fun execute()
}

class NaverPayment : Payment {
    override fun execute() {
        println("네이버 페이로 지불")
    }
}

class NaverRefund : Refund {
    override fun execute() {
        println("네이버 페이 환불")
    }
}

class TossPayment : Payment {
    override fun execute() {
        println("토스 페이로 지불")
    }
}

class TossRefund : Refund {
    override fun execute() {
        println("토스 페이 환불")
    }
}

interface PaymentProcessFactory {
    fun executePayment(): Payment
    fun executeRefund(): Refund
}

class NaverProcessPaymentFactory : PaymentProcessFactory {
    override fun executePayment(): Payment = NaverPayment()

    override fun executeRefund(): Refund = NaverRefund()
}

class TossProcessPaymentFactory : PaymentProcessFactory {
    override fun executePayment(): Payment = TossPayment()

    override fun executeRefund(): Refund = NaverRefund()
}

class OrderPayment(private val paymentProcess: PaymentProcessFactory) {
    private var payment: Payment
    private var refund: Refund

    init {
        payment = paymentProcess.executePayment()
        refund = paymentProcess.executeRefund()
    }

    fun payment() {
        payment.execute()

    }

    fun refund() {
        refund.execute()
    }
}

enum class OrderPaymentEnum(val label: Int){
    NAVER(0),
    TOSS(1);
    companion object {
        fun labelOf(label: Int): OrderPaymentEnum {
            return values()
                .firstOrNull{it.label == label}
                ?: throw NoSuchElementException("정의되지 않은 기호")
        }
    }
}

fun main() {
    val choice: String = readlnOrNull() ?: "0"
    val choicePayment: OrderPaymentEnum = choice.toInt().let {
        OrderPaymentEnum.labelOf(it)
    }
    println(choicePayment)

    val orderPayment: PaymentProcessFactory = when (choicePayment) {
        OrderPaymentEnum.NAVER -> NaverProcessPaymentFactory()
        OrderPaymentEnum.TOSS -> TossProcessPaymentFactory()
    }

    orderPayment.executePayment().execute()
}