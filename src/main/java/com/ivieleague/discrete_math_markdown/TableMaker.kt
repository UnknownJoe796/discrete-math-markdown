package com.ivieleague.discrete_math_markdown

import java.util.*

/**
 * Created by josep on 1/18/2016.
 */
/**
 * We declare a package-level function main which returns Unit and takes
 * an Array of strings as a parameter. Note that semicolons are optional.
 */

fun main(args: Array<String>) {
    printTable(
            3,
            { it[0] },
            { it[1] },
            { it[2] },
            { it[0] or (it[1] and it[2]) },
            { (it[0] or it[1]) and (it[0] or it[2]) }
    )
}

infix fun Boolean.then(other: Boolean): Boolean {
    return !(this and !other)
}

fun printTable(size: Int, vararg functions: (BooleanArray) -> Boolean) {
    print('|')
    for (i in 1..functions.size) print("-|")
    println()
    val poss = possibilities(size)
    for (p in poss) {
        print("@truthRow ")
        for (func in functions) {
            if (func(p)) {
                print('T')
            } else {
                print('F')
            }
        }
        println()
    }
}

fun possibilities(size: Int): List<BooleanArray> {
    if (size == 1) return listOf(booleanArrayOf(true), booleanArrayOf(false));
    else return ArrayList<BooleanArray>().apply {
        for (element in possibilities(size - 1)) {
            add(element + true)
            add(element + false)
        }
    }
}