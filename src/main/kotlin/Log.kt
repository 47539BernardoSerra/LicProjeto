import java.io.File

object Log {

    private const val FILE_NAME = "LOG.txt"

    private var listOfInside = mutableMapOf<String, Boolean>()

    fun init() {

        var bfLog = File(FILE_NAME).bufferedReader()

        do {
            val currentLine = bfLog.readLine()

            val id: String = currentLine.split("_")[1][0].toString()

            val inside = inside(currentLine.split(" ")[2])

            if (!listOfInside.containsKey(id)) {
                listOfInside[id] = inside
            } else if (listOfInside.containsKey(id) && listOfInside[id] != inside) {
                listOfInside[id] = inside
            }

        } while (bfLog.ready())
    }

    private fun inside(arrow: String): Boolean = arrow == "->"
    private fun boolToArrow(boolean: Boolean?): String = if (boolean == true) "->" else "<-"

    fun writeLog(uin: Int, name: String) {
        listOfInside[uin.toString()] = !listOfInside[uin.toString()]!!
        val idIn = listOfInside[uin.toString()]
        val arrow = boolToArrow(!idIn!!)
        val toWrite = TUI.getLocalDateTime() + " $arrow _$uin:$name"
        File("LOG.txt").appendText("\n")
        File("LOG.txt").appendText(toWrite)
    }

    fun entering(uin: String) : Boolean = listOfInside[uin]!!

    fun lastRegisterTime() = "??:??"

}