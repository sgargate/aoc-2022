import java.lang.Integer.max

fun main() {

    val grid = getLines("day8/input2.txt").map {
        it.map { it.digitToInt() }
    }


    println(part1(grid))

    println(part2(grid))

}

private fun part2(grid: List<List<Int>>): Int {
    var maxScore = 0
    grid.forEachIndexed { i, ints ->
        ints.forEachIndexed { j, value ->
            if (i != 0 && j != 0 && i != grid.size - 1 && j != ints.size - 1) {

                val count1 = max2((i - 1 downTo 0).map { grid[it][j] }.indexOfFirst { it >= value }, i)
                val count2 = max2((j - 1 downTo 0).map { grid[i][it] }.indexOfFirst { it >= value }, j)
                val count3 = max2((j + 1 until grid.size).map { grid[i][it] }.indexOfFirst { it >= value }, grid.size - j - 1)
                val count4 = max2((i + 1 until ints.size).map { grid[it][j] }.indexOfFirst { it >= value }, ints.size - i - 1)
                val score = count1 * count2 * count3 * count4

                maxScore = max(score, maxScore)
            }
        }
    }
    return maxScore
}

private fun part1(grid: List<List<Int>>): Int {
    var visible = 0

    grid.forEachIndexed { i, ints ->
        ints.forEachIndexed { j, value ->
            if (i == 0 || j == 0 || i == grid.size - 1 || j == ints.size - 1) visible++
            else {
                val isInvisible = (0 until j).map { grid[i][it] }.any { it >= value } &&
                        (0 until i).map { grid[it][j] }.any { it >= value } &&
                        (j + 1 until grid.size).map { grid[i][it] }.any { it >= value } &&
                        (i + 1 until ints.size).map { grid[it][j] }.any { it >= value }

                if (!isInvisible) visible++

            }
        }
    }
    return visible
}

fun max2(a: Int, b: Int) = if (a < 0) b else a + 1