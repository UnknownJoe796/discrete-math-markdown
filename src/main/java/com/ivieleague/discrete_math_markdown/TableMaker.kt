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
    //    printTable(
    //            {p, q -> p},
    //            {p, q -> q},
    //            {p, q -> (!p) then q},
    //            {p, q -> (!q) then p},
    //            {p, q -> (!p) or (!q)}
    //    )
    val list = listOf(-48, -14, -8, 0, 1, 3, 16, 23, 26, 32, 36)
    println("a. " + list.all { it.odd then (it > 0) })
    println("b. " + list.all { (it < 0) then it.even })
    println("c. " + list.all { it.even then (it <= 0) })
}

val Int.even: Boolean get() = Math.abs(this) % 2 == 0
val Int.odd: Boolean get() = Math.abs(this) % 2 == 1

infix fun Boolean.then(other: Boolean): Boolean {
    return !(this and !other)
}

fun printTable(vararg functions: (Boolean) -> Boolean) {
    printTableList(1, functions.map { func ->
        { array: BooleanArray ->
            val p = array[0]
            func(p)
        }
    })
}

fun printTable(vararg functions: (Boolean, Boolean) -> Boolean) {
    printTableList(2, functions.map { func ->
        { array: BooleanArray ->
            val p = array[0]
            val q = array[1]
            func(p, q)
        }
    })
}

fun printTable(vararg functions: (Boolean, Boolean, Boolean) -> Boolean) {
    printTableList(3, functions.map { func ->
        { array: BooleanArray ->
            val p = array[0]
            val q = array[1]
            val r = array[2]
            func(p, q, r)
        }
    })
}

fun printTableVararg(size: Int, vararg functions: (BooleanArray) -> Boolean) {
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

fun printTableList(size: Int, functions: List<(BooleanArray) -> Boolean>) {
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