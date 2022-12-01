fun main() {
    val sortedSums = getLines("day1/input2.txt")
        .fold(mutableListOf(mutableListOf<Long>())) { acc, s ->
            if (s.isBlank()) acc.add(mutableListOf()) else acc.last().add(s.toLong())
            acc
        }.map { it.sum() }
        .sortedDescending()

    println(sortedSums.first())

    println(sortedSums.take(3).sum())
}