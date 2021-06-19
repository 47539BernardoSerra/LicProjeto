import isel.leic.utils.Time

object M {

    const val USERS_FILE = "USERS.txt"

    const val LOG_FILE = "LOG.txt"

    fun init(){

    }

    fun maintenance() {
        TUI.lcdClear()
        TUI.writeOnCursor("Out of Service",0)
        TUI.writeOnCursor("Wait",1)
        println("Turn M key to off, to terminate the maintenance mode.")
        println("Commands: NEW, DEL, LST, or OFF")
        while (true) {
            print("Maintenance> ")
            val cmd = readLine()!!
            when (cmd.uppercase()) {
                "OFF" -> {
                    TUI.lcdClear()
                    TUI.writeOnCursor("Shutdown...",1)
                    Time.sleep(5000)
                    return
                }
                "NEW" -> addUser()
                "DEL" -> delUser()
                "LST" -> lst()
                "" -> {
                }
                else -> println("Invalid command.")
            }
            if (HAL.readBits(128)!=128) {
                println("Exiting maintenance mode...")
                return
            }
        }
    }

    private fun addUser(){
        var name : String
        var pin : Int
        do{
            println("Username?")
            name = readLine()!!
            if(name.isEmpty()){
                println("Command aborted")
                return
            }
            if(name.length<=16){
                break;
            }
            println("$name has more than 16 characters")
        }while(true)

        do{
            println("PIN?")
            val line = readLine()!!
            if(name.isEmpty()){
                println("Command aborted")
                return
            }
            try{
                pin= line.toInt()
                if(pin !in 0..9999) {
                    throw NumberFormatException()
                }
                break
            } catch(e: NumberFormatException){
                println("Invalid PIN, length must be 4 and it must be a number")
            }
        }while(true)
        val user = Users.addUser(name,pin)
        if (user == null) {
            println("User not created, insufficient memory.")
        } else {
            println("Adding user $user")
        }
        defragUsers()
    }

    private fun delUser(){
        var deleteLine: List<String>? = null
        var lineNumber = 0
        println("UIN?")
        val uin : String = readLine()!!
        if(uin.isEmpty()){
            println("Command aborted")
            return
        }
        val reader = FileAccess.createReader(USERS_FILE)
        while(reader.ready()){
            val currentLine = reader.readLine().split(";")
            if(currentLine[0]==uin){
                    deleteLine = currentLine
            }
            lineNumber++
        }
        if(deleteLine==null){
            println("UID not found")
            return
        }
        println("Remove user "+deleteLine[0]+":"+deleteLine[2])
        println("Y/N")
        val ans = readLine()!!
        if(ans.lowercase()!="y"){
            println("Command aborted")
            return
        }
        Users.removeUser(uin)
        println("User deleted "+deleteLine[0]+":"+deleteLine[2])
    }

    private fun defragUsers(){
        val reader = FileAccess.createReader(USERS_FILE)
        var listOfUsers = mutableListOf<String>()
        while(reader.ready()){
            listOfUsers.add(reader.readLine().toString())
        }
        for (pass in 0 until (listOfUsers.size - 1)) {
            for (currentPosition in 0 until (listOfUsers.size - 1)) {
                val currentValue = listOfUsers[currentPosition].split(";")[0]
                val futureValue = listOfUsers[currentPosition+1].split(";")[0]
                if (currentValue > futureValue) {
                    listOfUsers[currentPosition] = listOfUsers[currentPosition+1]
                    listOfUsers[currentPosition + 1] = listOfUsers[currentPosition]
                }
            }
        }
        FileAccess.clearFile(USERS_FILE)
        for (i in 0 until listOfUsers.size) {
            FileAccess.writeText(listOfUsers[i]+"\n", USERS_FILE)
        }
    }

    fun lst(){

    }
}