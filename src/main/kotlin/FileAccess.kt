import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File


object FileAccess {
    fun init(file: String){
        val bw = File(file).bufferedWriter()
    }

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
            lastLine = reader.readLine().toString()
        }
        return lastLine
    }

    fun clearFile(file: String){
        deleteFile(file)
        createFile(file)
    }

    fun deleteFile(file:String){
        val myFile = File(file)
        if (myFile.exists()) myFile.delete()
    }

    fun createFile(file:String){
        val myFile = File(file)
        myFile.createNewFile()
    }

    fun removeLines(fileName: String, startLine: Int, numLines: Int) {
        require(fileName.isNotEmpty() && startLine >= 1 && numLines >= 1)

        val f = File(fileName)

        var lines = f.readLines()

        var n = numLines

        lines = lines.take(startLine - 1) + lines.drop(startLine + n - 1)

        val text = lines.joinToString(System.lineSeparator())

        f.writeText(text)
    }


}