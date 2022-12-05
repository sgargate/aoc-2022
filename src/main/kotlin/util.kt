import java.math.BigInteger
import java.security.MessageDigest

fun getLines(path: String): List<String> =
    object {}.javaClass.getResource(path)?.readText()?.lines() ?: emptyList()

fun getText(path: String): String =
    object {}.javaClass.getResource(path)?.readText() ?: ""

fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')


fun String.toIntRange(): IntRange {
    return substringBefore("-").toInt() .. substringAfter("-").toInt()
}

fun IntRange.fullyOverlaps(other: IntRange) : Boolean {
    return first <= other.first && last >=other.last
}

fun IntRange.overlaps(other: IntRange) : Boolean {
    return first <= other.last && other.first <= last
}
