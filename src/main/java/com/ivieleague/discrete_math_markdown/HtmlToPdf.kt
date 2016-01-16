package com.ivieleague.discrete_math_markdown
import com.lowagie.text.pdf.BaseFont
import org.xhtmlrenderer.pdf.ITextOutputDevice
import org.xhtmlrenderer.pdf.ITextRenderer
import org.xhtmlrenderer.pdf.ITextUserAgent
import org.xhtmlrenderer.resource.XMLResource
import java.io.*
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Created by josep on 1/15/2016.
 */
public object HtmlToPdf{
    val fontPath = "font.ttf"
    fun convert(htmlFile: File, pdfOut: OutputStream){
        try {
            if (!Files.exists(Paths.get(fontPath))) {
                Files.copy(
                        ClassLoader.getSystemClassLoader().getResourceAsStream("LucidaSansUnicodeRegular.ttf"),
                        Paths.get(fontPath))
            }

            val renderer = ITextRenderer();
            val callback = HtmlToPdf.ResourceLoaderUserAgent(renderer.outputDevice);
            callback.sharedContext = renderer.sharedContext;
            renderer.sharedContext.userAgentCallback = callback;
            renderer.fontResolver.addFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED)

            val doc = XMLResource.load(FileInputStream(htmlFile)).document;

            renderer.setDocument(htmlFile);
            renderer.layout();
            renderer.createPDF(pdfOut);
        } finally {
            try {
                pdfOut.close();
            } catch (e: IOException) {
                // ignore
            }
        }
    }
    private class ResourceLoaderUserAgent(outputDevice: ITextOutputDevice): ITextUserAgent(outputDevice)
    {
        override fun resolveAndOpenStream( uri: String): InputStream {
            val ins = super.resolveAndOpenStream(uri);
            System.out.println("IN resolveAndOpenStream() " + uri);
            return ins;
        }
    }
}