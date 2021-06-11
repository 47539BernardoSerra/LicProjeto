import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.util.stream.Stream

object FileAccess {
    fun init(){}

    fun createReader(file: String):BufferedReader{
        return File(file).bufferedReader()
    }

    fun createWriter(file: String):BufferedWriter{
        return File(file).bufferedWriter()
    }

    fun writeText(text: String, file: String) {
        File(file).appendText(text)
    }

    fun returnLastLine(file: String):String?{
        val reader = createReader(file)
        var lastLine : String? = null
        while (reader.ready()){
            lastLine = reader.read().toString()
        }
        return lastLine
    }


}