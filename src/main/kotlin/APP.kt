import isel.leic.utils.Time
import java.io.File

const val UIN = 3
const val PIN = 4

object APP {
    /*os logs nao estao a funcionar muito bem
        falta meter as horas a que ele entrou e as que ele saiu apartir do log
        se o iun falhar voltar atras
        cena do m que nao sei o que é
        saber quando a porta esta aberta e quando é que ela esta fechada

    */
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
            TUI.centerString("Hello", 0)
            LCD.write("Hello")
            TUI.centerString(name, 1)
            LCD.write(name)


            Time.sleep(100)
            TUI.centerString(name, 0)
            LCD.write(name)
            Log.writeLog(uin, name)
            TUI.centerString("Door is Opening", 1)
            LCD.write("Door is Opening")
            Door.open(3)
            Time.sleep(3000)
            TUI.centerString("Door is Closing", 1)
            LCD.write("Door is Closing")
            Door.close(1)
            Time.sleep(3000)
            LCD.clear()
            app()
        }
    }
}