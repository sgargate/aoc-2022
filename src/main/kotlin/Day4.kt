fun main() {
    val sums = getLines("day4/input2.txt")
        .map {
            val s = it.split(",")
            IntRange.ofDashSeparated(s[0]).toSet() to IntRange.ofDashSeparated(s[1]).toSet()

        }.filter {(first, second) ->
            if(first.size > second.size)
            first.intersect(second) == second
            else second.intersect(first) == first
        }.size

    println(sums)

    val sum2 = getLines("day4/input2.txt")
        .map {
            val s = it.split(",")
            IntRange.ofDashSeparated(s[0]).toSet() to IntRange.ofDashSeparated(s[1]).toSet()

        }.filter {(first, second) ->
            first.intersect(second).isNotEmpty()
        }.size

    println(sum2)

}
