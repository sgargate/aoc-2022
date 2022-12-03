import Shape.Companion.byChar
import Shape.Companion.byOutcome

fun main() {

    val lines = getLines("day2/input2.txt")
    val totalScore = lines.map {
        val split = it.split(" ")
        split[0].first() to split[1].first()
    }.map {
        val firstShape = byChar(it.first)
        // firstShape to byChar(it.second)
        firstShape to byOutcome(it.second, firstShape)
    }.sumOf {
        score(it.first, it.second)
    }

    println(totalScore)

}

private fun score(first: Shape, second: Shape) = when {
    first.beats == second -> 0
    second.beats == first -> 6
    else -> 3
} + second.score

enum class Shape(val score: Int){
    Rock(1),
    Paper(2),
    Scissors(3);

    val beats: Shape
        get() = when (this) {
            Rock -> Scissors
            Paper -> Rock
            Scissors -> Paper
        }

    companion object {
        fun byChar(c: Char) = when(c){
            'A', 'X' -> Rock
            'B', 'Y' -> Paper
            'C', 'Z' -> Scissors
            else ->  error("not found $c" )
        }
        fun byOutcome(c: Char, firstShape: Shape ) = when(c){
            'X' -> firstShape.beats
            'Y' -> firstShape
            'Z' -> values().first { it.beats == firstShape }
            else -> error("not found $c")
        }
    }
}