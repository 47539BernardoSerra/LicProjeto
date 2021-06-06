import java.io.File

object Log {


    fun writeLog(uin: Int, name: String) : String {
        val a: List<String> = readLog().filter { it.split("_").contains("$uin:$name") }
        val inout = if (a.isNotEmpty()) a.last().split(" ")[2] else "->"
        val arrow = if (inout == "->") "<-" else "->"
        val toWrite = TUI.getLocalDateTime() + " $arrow _$uin:$name"
        File("LOG.txt").appendText("\n")
        File("LOG.txt").appendText(toWrite)
        return a.last().split(" ")[1]
    }

    fun readLog() = File("LOG.txt").readLines()

}