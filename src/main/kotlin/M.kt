import isel.leic.utils.Time
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.pow

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

    fun addUser(){

    }

    fun delUser(){

    }
    fun lst(){

    }
}