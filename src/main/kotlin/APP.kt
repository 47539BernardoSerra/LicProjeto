import isel.leic.utils.Time
import java.io.File

const val UIN = 3
const val PIN = 4

object APP {

    fun app(){
        LCD.write(TUI.getTime() + "UIN:???")
        LCD.cursor(1, 4)
        var acc = 0
        val list = File("USERS.txt").readLines()
        var uin = ""
        while (true){
            if (KBD.getKey() != '!') {
                val key = KBD.getKey()
                LCD.write(key)
                TUI.ack()
                uin += key
                acc++
            }
            if (acc == UIN)
                break
        }
        val pin = list[uin.toInt()].split(";")[3]
        val name = list[uin.toInt()].split(";")[2]
        Time.sleep(50)
        LCD.cursor(1, 0)
        LCD.write("PIN:????")
        LCD.cursor(1, 4)
        var pinn = ""
        acc = 0
        while (true){
            if (KBD.getKey() != '!') {
                val key = KBD.getKey()
                LCD.write(key)
                TUI.ack()
                pinn += key
                acc++
            }
            if (acc == PIN)
                break
        }
        Time.sleep(50)
        if(pinn.toInt() == pin.toInt()){
            LCD.clear()
            TUI.centerString("Bem Vindo", 0)
            LCD.write("Bem Vindo")
            TUI.centerString(name, 1)
            LCD.write(name)
            Time.sleep(50)
            Door.open(2)
            Door.close(2)
        }
    }
}