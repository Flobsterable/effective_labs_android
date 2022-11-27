package ru.flobsterable.effectiveLabs.data.network.utils

import java.security.MessageDigest

fun md5(str: String): ByteArray =
    MessageDigest.getInstance("MD5").digest(str.toByteArray(Charsets.UTF_8))
fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }
