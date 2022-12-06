import java.util.concurrent.ArrayBlockingQueue

private const val maxLength = 14

fun main() {
    val inputs = getLines("day6/input1.txt")

    inputs.forEach {
        println(startMarker(it)+1)
    }
}

private fun startMarker(input: String): Int {
    val q = ArrayBlockingQueue<Char>(maxLength)

    input.forEachIndexed { i, c ->
        q.offerAndReplace(c)
        if (q.distinct().size == maxLength) return i
    }
    return -1
}

fun ArrayBlockingQueue<Char>.offerAndReplace(c: Char) {
    if(size == maxLength) take()
    offer(c)
}
