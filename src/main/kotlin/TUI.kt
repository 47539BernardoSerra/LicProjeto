import isel.leic.utils.Time
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val UIN = 3
const val PIN = 4

object TUI {

    fun getLocalDateTime() : String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))

    fun getTime() : String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))

    fun ack() {
        Time.sleep(30)
        HAL.setBits(128)
        Time.sleep(30)
        HAL.clrBits(128)
    }

    fun centerStringAndWrite(text : String, line : Int) {
        val space = if(text.length < 15) (16 - text.length - 1) / 2 else 0
        LCD.cursor(line, space)
        LCD.write(text)
    }

    fun verifyUin(): String {
        var uin = ""
        var acc = 0
        while (acc != UIN) {
            if (KBD.getKey() != '!') {
                val key = KBD.getKey()
                LCD.write(key)
                ack()
                uin += key
                acc++
            }
        }
        return uin
    }
    fun verifyPin(): String {
        var pin = ""
        var acc = 0
        while (acc != PIN) {
            if (KBD.getKey() != '!') {
                val key = KBD.getKey()
                LCD.write('*')
                ack()
                pin += key
                acc++
            }
        }
        return pin
    }
}