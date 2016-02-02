package com.ivieleague.discrete_math_markdown

import java.util.*

/**
 * Created by josep on 1/15/2016.
 */
public object DiscreteProcessor {

    var lineMarker: String = "@@"
    var startMarker: String = "@{"
    var endMarker: String = "}"
    var singleMarker: String = "\\."
    var truthRowMarker: String = "@truthRow"

    val table: HashMap<String, String> = HashMap()

    init {
        table["not in"] = "\u2209"
        table["that"] = ":"
        table["real numbers"] = "\u211D"
        table["natural numbers"] = "\u2115"
        table["all integers"] = "\u2124"

        table["not"] = "~"
        table["then"] = "\u21D2"
        table["if"] = "\u21D0"
        table["iff"] = "\u21D4"
        table["and"] = "\u2227"
        table["or"] = "\u2228"
        table["xor"] = "\u22BB"
        table["same"] = "\u2261"
        table["therefore"] = "\u2234"
        table["exists"] = "\u2203"
        table["all"] = "\u2200"
        table["in"] = "\u2208"

        table["!="] = "\u2260"
        table[">="] = "\u2265"
        table["<="] = "\u2264"
        table[" ?\\^ ?2"] = "\u00B2"
        table[" ?\\^ ?3"] = "\u00B3"
        table[" ?\\^ ?4"] = "\u00B4"
        table[" \\* "] = "\u22C5"
        table["/"] = "\u00F7"
        table["sqrt"] = "\u221A"
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
            if (it.contains(lineMarker)) processAny(" ".plus(it).replace(lineMarker, ""))
            else it
        }.joinToString("\n")
    }

    fun processStartEnd(input: String): String {
        return input.split(startMarker).map {
            val inners = it.split(endMarker)

            if (inners.size > 1)
                processAny(" " + inners[0]) + inners.subList(1, inners.size).joinToString("")
            else
                it
        }.joinToString("")
    }

    fun processSingle(input: String): String {
        var current = input
        for ((key, value) in table) {
            current = current.replace((singleMarker + key).toRegex(), value)
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
        current = current.replace("([ \\(||{\\[\\*+/-\\^,])([a-zA-Z])([ \\)||}\\]\\*+/-\\^,])".toRegex(), "$1 *$2* $3")
        for ((key, value) in table) {
            val count = value.count { it == '$' }
            val replaceVal = "$1" + value.replace("$3", "$4").replace("$2", "$3").replace("$1", "$2") + "$" + (count + 2).toString()
            current = current.replace(("([^a-zA-Z])$key([^a-zA-Z])").toRegex(), replaceVal)
        }
        current = current.replace("\\[([0-9]+)\\]".toRegex()) {
            it.groups[0]!!.value.map { it - 0x30 + 0x2080 }.joinToString()
        }
        return current
    }

}