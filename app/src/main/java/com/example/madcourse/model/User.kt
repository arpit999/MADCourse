package com.example.madcourse.model

import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

data class User(
    val userId: Long,
    val username: String,
    val fullName: String,
    val email: String
)

val domains = listOf("@gmail.com", "@yahoo.com", "@hotmail.com", "@outlook.com")

val userGenerator = generateSequence {
    User(
        //userId must be long and of 8 digit,
        userId = Random.nextLong(100_000_00L, 999_999_99L),
        //username must be alphanumeric and 6 characters,
        username = generateUsername2(6),
        //fullName must be alphabetical and up to 20 characters
        fullName = generateFullName(20),
        //email should be any valid email address.
        email = "${generateUsername(6)}${domains.random()}"
    )
}

fun generateFullName(charLimit: Int): String {
    val nameLength = nextInt(1, charLimit + 1)
    val nameBuilder = StringBuilder(nameLength)
    repeat(nameLength) {
        val letter = ('a'..'z').filter { it.isLetter() }.random()
        nameBuilder.append(letter)
    }
    return nameBuilder.toString().capitalize()
}

fun generateUsername(charLimit: Int): String {
    val usernameBuilder = StringBuilder(charLimit)
    repeat(charLimit) {
        val char = when (nextInt(3)) {
            0 -> ('a'..'z')
            1 -> ('A'..'Z')
            else -> ('0'..'9')
        }.filter { it.isLetterOrDigit() }.random()
        usernameBuilder.append(char)
    }
    return usernameBuilder.toString()
}


fun generateUsername2(charLimit: Int): String {
    val usernameBuilder = StringBuilder(charLimit)
    val hasLetter = booleanArrayOf(false)
    val hasDigit = booleanArrayOf(false)
    repeat(charLimit) {
        val char = when (nextInt(3)) {
            0 -> ('a'..'z')
            1 -> ('A'..'Z')
            else -> ('0'..'9')
        }.filter { it.isLetterOrDigit() }.random()
        if (char.isLetter() && !hasLetter[0]) {
            hasLetter[0] = true
        } else if (char.isDigit() && !hasDigit[0]) {
            hasDigit[0] = true
        }
        usernameBuilder.append(char)
    }
    if (!hasLetter[0]) {
        val letter = ('a'..'z').filter { it.isLetter() }.random()
        val index = nextInt(charLimit)
        usernameBuilder.replace(index, index + 1, letter.toString())
    }
    if (!hasDigit[0]) {
        val digit = ('0'..'9').random()
        val index = nextInt(charLimit)
        usernameBuilder.replace(index, index + 1, digit.toString())
    }
    return usernameBuilder.toString()
}


val users = userGenerator.take(100).toList()