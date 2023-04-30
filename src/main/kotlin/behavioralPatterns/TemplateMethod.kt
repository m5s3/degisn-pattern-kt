package behavioralPatterns

/**
 * Abstract Class : 추상 클래스로 templateMethod를 정의한다.
 *
 */
abstract class Report {
    fun generateReport() {
        fetchData()
        formatData()
        printData()
    }

    protected abstract fun fetchData()
    protected abstract fun formatData()
    protected abstract fun printData()
}

/**
 * Concrete Class : 부모 클래스에서 abstract 로 정의된 templateMethod 를 구현한다.
 *
 */
class SalesReport : Report() {
    override fun fetchData() {
        println("Fetching sales data...")
    }

    override fun formatData() {
        println("Formatting sales data...")
    }

    override fun printData() {
        println("Printing sales report...")
    }
}

class MarketReport: Report() {
    override fun fetchData() {
        println("Fetching market data...")
    }

    override fun formatData() {
        println("Formatting market data...")
    }

    override fun printData() {
        println("Printing market report...")
    }
}

fun main() {
    val salesReport = SalesReport()
    val marketReport = MarketReport()

    val list = listOf(salesReport, marketReport)

    list.forEach { it.generateReport() }

}