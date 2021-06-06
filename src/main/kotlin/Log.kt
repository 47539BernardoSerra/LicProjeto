import java.io.File

object Log {

    fun writeLog(uin : String, name : String){
        //falta meter para todos so users
        val inout = if(File("LOG.txt").readLines().isNotEmpty())readLog().last().split(" ")[2] else "->"
        val a = if(inout == "->") "<-" else "->"
        val toWrite = TUI.getTime() + " $a $uin:$name"
        File("LOG.txt").writeText(toWrite)
    }

    fun readLog() = File("LOG.txt").readLines()



}