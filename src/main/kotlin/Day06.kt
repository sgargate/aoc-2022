
fun main() {
    val inputs = getLines("day06/input1.txt")

    inputs.forEach {
        println(startMarker(it))
    }
}

private fun startMarker(input: String): Int {
    val maxLength = 14

    return input.windowed(maxLength).indexOfFirst {
        it.toSet().size == maxLength
    } + maxLength

}

