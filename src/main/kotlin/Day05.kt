import java.util.Stack

fun main() {

    val input = getText("day05/input2.txt").split("\n\n")
    val stacks = stacks(input[0].lines().reversed())
    val moves = moves(input[1].lines())

    //part1(moves, stacks)
    part2(moves, stacks)

    val output = stacks.map { it.peek() }.joinToString("")

    println(output)
}

private fun stacks(stackTxt: List<String>): List<Stack<Char>> {
    val stackCount = stackTxt.first().split(" ").last().toInt()
    val stacks = List(stackCount) { Stack<Char>() }

    stackTxt.drop(1).map { line ->
        line.chunked(4).mapIndexed { idx, it ->
            if (it.isNotBlank())
                stacks[idx].push(it[1])
        }
    }
    return stacks
}

private fun moves(movesText: List<String>): List<Move> {
    val regex = Regex("move (\\d+) from (\\d+) to (\\d+)")
    val moves = movesText.map {
        val values = regex.find(it)?.groupValues ?: error("invalid text")
        Move(values[1].toInt(), values[2].toInt(), values[3].toInt())
    }
    return moves
}

private fun part1(
    moves: List<Move>,
    stacks: List<Stack<Char>>
) {
    moves.map { move ->
        repeat(move.count) {
            val item = stacks[move.from - 1].pop()
            stacks[move.to - 1].push(item)
        }
    }
}


private fun part2(
    moves: List<Move>,
    stacks: List<Stack<Char>>
) {
    moves.map { move ->
        (0 until move.count).map {
            stacks[move.from - 1].pop()
        }.reversed().forEach {
            stacks[move.to - 1].push(it)
        }
    }
}

data class Move(val count: Int, val from: Int, val to: Int)