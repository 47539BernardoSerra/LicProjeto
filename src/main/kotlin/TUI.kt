import isel.leic.utils.Time
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object TUI {
    private const val UIN = 3
    private const val PIN = 4

    //Entrega a Data e a Hora
    fun getLocalDateTime() : String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
    //Entrega a Hora
    fun getTime() : String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
    //Ack para premitir a entrada de nova tecla
    fun ack() {
        Time.sleep(10)
        KBD.halSetBits(128)
        Time.sleep(30)
        KBD.halClrBits(128)
    }
    //Centra a String no meio do LCD e escreve no mesmo
    fun centerStringAndWrite(text : String, line : Int) {
        val space = if(text.length < 15) (16 - text.length - 1) / 2 else 0
        LCD.cursor(line, space)
        LCD.write(text)
    }
    //Escreve  a tecla presionada e guarda o uin
    fun writeAndSaveUin(): String {
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
    //Escreve  a tecla presionada e guarda o Pin
    fun writeAndSavePin(): String {
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

    fun lcdClear(){
        LCD.clear()
    }

    fun writeOnCursor(text: String,line: Int){
        LCD.cursor(line,0)
        LCD.write(text)
    }

    fun lcdWriteAndPlaceCursor(text: String, line: Int, col : Int){
        LCD.write(text)
        LCD.cursor(line, col)
    }

    fun lcdCursor(line: Int, col : Int){
        LCD.cursor(line, col)
    }
}