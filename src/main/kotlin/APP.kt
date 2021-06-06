import isel.leic.utils.Time
import java.io.File


object APP {
    /*
        os logs nao estao a funcionar muito bem
        falta meter as horas a que ele entrou e as que ele saiu apartir do log
        se o iun falhar voltar atras
        cena do m que nao sei o que é
        saber quando a porta esta aberta e quando é que ela esta fechada

    */
    fun app() {
        LCD.init()
        Log.init()
        var uin = "0"
        val list = File("USERS.txt").readLines()
        do {
            LCD.clear()
            LCD.write(TUI.getLocalDateTime() + "UIN:???")
            LCD.cursor(1, 4)
            uin = TUI.verifyUin()

            val pinUsersFile = list[uin.toInt()].split(";")[3]

            Time.sleep(100)
            LCD.cursor(1, 0)
            LCD.write("PIN:????")
            LCD.cursor(1, 4)

            val pinWritten = TUI.verifyPin()
            Time.sleep(100)
        } while (pinWritten.toInt() != pinUsersFile.toInt())
        val name = list[uin.toInt()].split(";")[2]
        LCD.clear()
        TUI.centerStringAndWrite("Hello", 0)
        TUI.centerStringAndWrite(name, 1)
        Time.sleep(1000)

        Log.writeLog(uin.toInt(), name)

        LCD.clear()
        LCD.write("Entrance " + TUI.getTime())









        Time.sleep(100)
        TUI.centerStringAndWrite(name, 0)
        Log.writeLog(uin.toInt(), name)
        TUI.centerStringAndWrite("Door is Opening", 1)
        Door.open(3)
        Time.sleep(1000)
        TUI.centerStringAndWrite("Door is Closing", 1)
        Door.close(1)
    }
}