import java.io.File

object Log {

    private const val LOG_FILE = "LOG.txt"
    private const val USER_FILE = "USERS.txt"

    private var listOfInside = mutableMapOf<String, Boolean>()
    private var listOfTime = mutableMapOf<String,String>()

    fun init() {

        var bfLog = File(LOG_FILE).bufferedReader()

        do {
            val currentLine = bfLog.readLine()

            if(currentLine.isNotEmpty()){

                val id = currentLine.split("_")[1][0].toString()

                val inside = inside(currentLine.split(" ")[2])

                val time = currentLine.split(" ")[1]

                listOfInside[id] = inside
                listOfTime[id] = time
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
        File(LOG_FILE).appendText("\n"+toWrite)
    }

    fun entering(uin: String) : String = if(listOfInside[uin]!=null) boolToArrow(listOfInside[uin]!!) else "NONE"

    fun lastRegisterTime(uin: String):String = if(listOfTime[uin]!=null) listOfTime[uin]!! else "NONE"

}