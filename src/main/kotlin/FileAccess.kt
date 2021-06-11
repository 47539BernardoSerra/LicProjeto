import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File

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


}