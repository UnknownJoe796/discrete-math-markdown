package com.ivieleague.discrete_math_markdown

import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

/**
 * Created by josep on 1/15/2016.
 */
public object FilesExt {
    fun readFile( path:String, encoding: Charset):String
    {
        val encoded = Files.readAllBytes(Paths.get(path));
        return String(encoded, encoding);
    }
    fun writeFile(path:String, data:String, encoding: Charset){
        Files.deleteIfExists(Paths.get(path))
        Files.write( Paths.get(path), data.toByteArray(encoding), StandardOpenOption.CREATE);
    }
}