object Log {

    //o Log regista num ficheiro a posicao dos users (se entraram ou asiram da ultima vez) e horas.

    private const val LOG_FILE = "LOG.txt"

    //Listas usadas para manter registo da posição dos users em relação a entradas e saidas.
    private var listOfInside = mutableMapOf<String, Boolean>()
    private var listOfTime = mutableMapOf<String,String>()

    //Intializa as listas usadas, mediante o ficheiro prexistente de LOG. Para correr optimamente, é preferivel que se guardaem os logs à parte e se use um ficheiro em branco cada vez que se recomeçao programa,
    //no entanto, em caso de crash ou erro, este pode ser começado com os logs do dias graças a isto.

    fun init() {

        var reader = FileAccess.createReader(LOG_FILE)

        do {
            val currentLine = reader.readLine()

            if(currentLine.isNotEmpty()){

                val id = currentLine.split("_")[1][0].toString()

                val inside = inside(currentLine.split(" ")[2])

                val time = currentLine.split(" ")[1]

                listOfInside[id] = inside
                listOfTime[id] = time
            }

        } while (reader.ready())
    }
    //Transforma a arrow guardada em logs no booleano para saber se o user setá dentro ou fora das portas.

    private fun inside(arrow: String): Boolean = arrow == "->"

    //Transforma o booleano na flecha correspondente para logs
    private fun boolToArrow(boolean: Boolean?): String = if (boolean == false) "->" else "<-"

    //Escreve nos logs
    fun writeLog(uin: Int, name: String) {
        listOfInside[uin.toString()] = !listOfInside[uin.toString()]!!
        val idIn = listOfInside[uin.toString()]
        val arrow = boolToArrow(!idIn!!)
        val toWrite = TUI.getLocalDateTime() + " $arrow _$uin:$name"
        FileAccess.writeText("\n"+toWrite, LOG_FILE)
    }

    //Diz se o user está a entrar ou a sair com a flecha
    fun entering(uin: String) : String = if(listOfInside[uin.toInt().toString()]!=null) boolToArrow(listOfInside[uin.toInt().toString()]!!) else "NONE"

    //Diz a ultima vez que um determinado user passou as portas.
    fun lastRegisterTime(uin: String):String = if(listOfTime[uin.toInt().toString()]!=null) listOfTime[uin.toInt().toString()]!! else "NONE"

}