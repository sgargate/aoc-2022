fun main() {
    val sums = getLines("day1/input2.txt")
        .fold(mutableListOf(mutableListOf<Long>())) { acc, s ->
            if (s.isBlank()) acc.add(mutableListOf()) else acc.last().add(s.toLong())
            acc
        }.map { it.sum() }

    val top3 = sums.sortedDescending().take(3)
    println(top3.first())

    println(top3.sum())
}