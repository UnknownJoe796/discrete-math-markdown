package com.ivieleague.discrete_math_markdown

/**
 * Main
 * Created by josep on 1/15/2016.
 */

import java.io.File
import java.io.FileOutputStream

fun main(vararg args: String) {
    if (args.size == 0) {
        println("---Discrete Math Markdown---")
        println("Outputs a PDF file for assignments.")
        println("-t = Testing mode, output markdown changes to console")
        println("Usage: dismaark <input file>")
        runOnAll(false)
    } else if (args.size == 1 && args[0] == "-t") {
        runOnAll(true)
    } else {
        var testMode = false
        var inFile = ""
        for (arg in args) {
            when (arg) {
                "-t" -> {
                    testMode = true
                }
                else -> {
                    inFile = arg
                }
            }
        }

        fullRun(inFile, testMode)
    }
}

private fun runOnAll(verbose: Boolean) {
    for (path in File(".").list()) {
        println(path)
        if (path.endsWith(".txt"))
            fullRun(path, verbose)
        else if (path.endsWith(".md"))
            fullRun(path, verbose)
    }
}

private fun fullRun(inFile: String, verbose: Boolean) {
    try {

        println("Converting...")
        val data = FilesExt.readFile(inFile, Charsets.UTF_8)

        val markdown = DiscreteProcessor.process(data)
        if (verbose) println(markdown)

        val html = MarkdownProcessor.process(markdown)
        if (verbose) println(html)

        try {
            val htmlFile = inFile.replace(".md", ".html")
            val pdfFile = inFile.replace(".md", ".pdf")

            FilesExt.writeFile(htmlFile, html, Charsets.UTF_8)
            HtmlToPdf.convert(File(htmlFile), FileOutputStream(pdfFile))
        } catch(e: Exception) {
            e.printStackTrace()
        }
        println("Success!")
    } catch(e: Exception) {
        e.printStackTrace()
    }
}