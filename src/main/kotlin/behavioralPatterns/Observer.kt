package behavioralPatterns


interface Publisher {
    fun addObserver(o: Observer)
    fun deleteObserver(o: Observer)
    fun notifyObservers()
}

interface Observer {
    fun update(play: Boolean)
}

class PlayController(
    private val observers: MutableList<Observer> = mutableListOf(),
    var play: Boolean = false,
) : Publisher {
    override fun addObserver(o: Observer) {
        observers.add(o)
    }

    override fun deleteObserver(o: Observer) {
        observers.remove(o)
    }

    override fun notifyObservers() {
        observers.map {
            it.update(play)
        }
    }

    fun setFlag(play: Boolean) {
        this.play = play
        notifyObservers()
    }
}

class Playable(
    private val publisher: Publisher,
    private var bPlay: Boolean,
) : Observer {

    init {
        this.publisher.addObserver(this)
    }

    override fun update(play: Boolean) {
        this.bPlay = play
        doAction()
    }

    private fun doAction() {
        if (bPlay) {
            println("프로그램 시작")
        } else {
            println("프로그램 종료")
        }
    }
}

fun main() {
    val pager = PlayController();
    val observer1 = Playable(pager, bPlay = false);
    val observer2 = Playable(pager, bPlay = false);
    pager.setFlag(true)
    pager.setFlag(false)
    pager.setFlag(true)
    pager.setFlag(false)
}
