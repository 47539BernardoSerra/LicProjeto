import isel.leic.utils.Time
import java.util.*

object M {
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
    }

    fun delUser(){

    }
    fun lst(){

    }
}