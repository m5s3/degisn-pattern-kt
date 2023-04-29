package behavioralPatterns

data class BankTransaction(val amount: Double)

abstract class ApprovalHandler()  {
    private var nextHandler: ApprovalHandler? = null

    fun setNextHandler(handler: ApprovalHandler) {
        nextHandler = handler
    }

    fun approve(transaction: BankTransaction) {
        if (canApprove(transaction)) {
            processApproval(transaction)
        } else {
            nextHandler?.approve(transaction)
        }
    }

    abstract fun canApprove(transaction: BankTransaction): Boolean

    abstract fun processApproval(transaction: BankTransaction)
}

class LowLevelManager : ApprovalHandler() {
    override fun canApprove(transaction: BankTransaction): Boolean = transaction.amount < 100

    override fun processApproval(transaction: BankTransaction) {
        println("Low-level manager approved transaction for ${transaction.amount}")
    }
}

class MiddleLevelManager : ApprovalHandler() {
    override fun canApprove(transaction: BankTransaction): Boolean = transaction.amount >= 100 && transaction.amount < 500

    override fun processApproval(transaction: BankTransaction) {
        println("Middle-level manager approved transaction for ${transaction.amount}")
    }
}

class HighLevelManager : ApprovalHandler() {
    override fun canApprove(transaction: BankTransaction): Boolean = transaction.amount >= 500

    override fun processApproval(transaction: BankTransaction) {
        println("High-level manager approved transaction for ${transaction.amount}")
    }
}

fun main() {
    val lowLevelManager = LowLevelManager()
    val middleLevelManager = MiddleLevelManager()
    val highLevelManager = HighLevelManager()

    lowLevelManager.setNextHandler(middleLevelManager)
    middleLevelManager.setNextHandler(highLevelManager)

    lowLevelManager.approve(BankTransaction(amount = 50.0))
    lowLevelManager.approve(BankTransaction(amount = 250.0))
    lowLevelManager.approve(BankTransaction(amount = 500.0))
    lowLevelManager.approve(BankTransaction(amount = 1500.0))
}