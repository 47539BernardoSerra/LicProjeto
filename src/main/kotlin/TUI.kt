import isel.leic.utils.Time
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object TUI {

    fun getTime() : String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))

    fun ack() {
        Time.sleep(30)
        HAL.setBits(128)
        Time.sleep(30)
        HAL.clrBits(128)
    }

    fun centerString(text : String, line : Int) {
        val space = if(text.length < 15) (16 - text.length - 1) / 2 else 0
        LCD.cursor(line, space)
    }
}