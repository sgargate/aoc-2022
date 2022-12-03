import java.util.BitSet

fun main() {
    val lines = getLines("day3/input2.txt")

    val priority: Map<Char, Int> = (('a'..'z').mapIndexed { index, c -> c to (index+1) } +
      ('A'..'Z').mapIndexed { index, c -> c to (index+27) }).toMap()


    val sum = lines.map { line ->
        val firstCompartment = BitSet()
        val secondComparment = BitSet()
        val mid = line.length / 2

        line.mapIndexed { index, c ->
            if (index < mid)
                firstCompartment.set(c.code)
            else secondComparment.set(c.code)
        }
        firstCompartment.and(secondComparment)
        firstCompartment
    }.sumOf {
        priority[it.nextSetBit(0).toChar()] ?: 0
    }
    println(sum)

    val sum2 = lines.chunked(3).map {
        it.map { line ->
            val group = BitSet()
            line.forEach {
                group.set(it.code)
            }
            group
        }.reduce {acc, next ->
            acc.and(next)
            acc
        }
    }.sumOf {
        priority[it.nextSetBit(0).toChar()] ?: 0
    }


    println(sum2)
}