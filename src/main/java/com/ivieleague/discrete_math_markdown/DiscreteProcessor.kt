package com.ivieleague.discrete_math_markdown

import java.util.*

/**
 * Created by josep on 1/15/2016.
 */
public object DiscreteProcessor {

    var lineMarker: String = "@@"
    var startMarker: String = "@start"
    var endMarker: String = "@end"
    var singleMarker: String = "."
    var truthRowMarker: String = "@truthRow"

    val table: HashMap<String, String> = HashMap()

    init {
        table["then"] = "\u21D2"
        table["if"] = "\u21D0"
        table["iff"] = "\u21D4"
        table["and"] = "\u2227"
        table["or"] = "\u2228"
        table["same"] = "\u2261"
        table["not"] = "~"
    }

    fun process(input: String): String {
        var current = input
        current = processMarkerChanges(current)
        current = processSingle(current)
        current = processLine(current)
        current = processStartEnd(current)
        current = processTruthRows(current)
        return current
    }

    fun processMarkerChanges(input: String):String {
        var current = input
        current = getValue(current, "lineMarker") { lineMarker = it }
        current = getValue(current, "startMarker") { startMarker = it }
        current = getValue(current, "endMarker") { endMarker = it }
        current = getValue(current, "singleMarker") { singleMarker = it }
        current = getValue(current, "truthRowMarker") { truthRowMarker = it }
        return current
    }

    inline fun getValue(input: String, name: String, onResult: (String) -> Unit):String {
        val toFind = "@$name("
        val startIndex = input.indexOf(toFind)
        if (startIndex != -1) {
            val endIndex = input.indexOf(")", startIndex.coerceAtLeast(0))
            if (endIndex != -1) {
                onResult(input.substring(startIndex + toFind.length, endIndex))
                if(startIndex > 0)
                    return input.substring(0, startIndex - 1) + input.substring(endIndex + 1)
                else
                    return input.substring(endIndex + 1)
            }
        }
        return input
    }

    fun processLine(input: String): String {
        return input.split('\n').map {
            if (it.contains(lineMarker)) processAny(it.replace(lineMarker, ""))
            else it
        }.joinToString("\n")
    }

    fun processStartEnd(input: String): String {
        return input.split(startMarker).map {
            val inners = it.split(endMarker)

            if (inners.size > 1)
                processAny(inners[0]) + inners.subList(1, inners.size).joinToString("")
            else
                it
        }.joinToString("")
    }

    fun processSingle(input: String): String {
        var current = input
        for ((key, value) in table) {
            current = current.replace(singleMarker + key, value)
        }
        return current
    }

    fun processTruthRows(input: String): String {
        return input.split('\n').map {
            val index = it.indexOf(truthRowMarker)
            if (index == -1) {
                it
            } else {
                it.substring(0, index) + it.substring(index + truthRowMarker.length)
                        .trim()
                        .toCharArray()
                        .joinToString("|", "|", "|")
            }
        }.joinToString("\n")
    }

    fun processAny(input: String): String {
        var current = input
        for ((key, value) in table) {
            current = current.replace(key, value)
        }
        return current
    }

}