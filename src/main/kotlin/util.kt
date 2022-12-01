import java.math.BigInteger
import java.security.MessageDigest

fun getLines(path: String): List<String> =
    object {}.javaClass.getResource(path)?.readText()?.lines() ?: emptyList()

fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')