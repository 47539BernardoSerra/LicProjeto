import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object TUI {

    fun getTime() : String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))




}