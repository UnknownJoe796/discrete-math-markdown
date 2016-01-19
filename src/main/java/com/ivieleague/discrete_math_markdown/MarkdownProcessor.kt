package com.ivieleague.discrete_math_markdown

import org.pegdown.Extensions
import org.pegdown.PegDownProcessor


/**
 * Created by josep on 1/15/2016.
 */
object MarkdownProcessor {
    val processor = PegDownProcessor(Extensions.ALL)

    fun process(input:String):String = buildString{
        append(
                """<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta charset="UTF-8"/>
<title>Homework</title>
<style>"""+ Style.css+"""</style>
</head>
<body><div class="markdown-body">
""")
        append(processor.markdownToHtml(input))
        append("</div>\n</body>\n</html>")
    }

/*fun process(input:String):String = buildString {
    append(
"""<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title>Homework</title>
<style>"""+ Style.css+"""</style>
</head>
<body><div class="markdown-body">
""")
    append(Processor.process(input))
    append("</div>\n</body>\n</html>")
}*/
}