import isel.leic.UsbPort
import isel.leic.utils.Time

object KBD {
    // Ler teclas. Métodos retornam ‘0’..’9’,’#’,’*’ ou NONE.
    const val NONE = 0;

    // Inicia a classe
    fun init() {
    }

    //Retorna de imediato a tecla premida ou NONE se não há tecla premida. //apenas chamar quando DVal ativo
    fun getKey(): Char {
        return when (HAL.readBits(143)) {
            7+128 -> '0'
            0+128 -> '1'
            4+128 -> '2'
            8+128 -> '3'
            1+128 -> '4'
            5+128 -> '5'
            9+128 -> '6'
            2+128 -> '7'
            6+128 -> '8'
            10+128 -> '9'
            11+128 -> '#'
            3+128 -> '*'
            else -> '!'
        }
    }

    // Retorna quando a tecla for premida ou NONE após decorrido ‘timeout’ milisegundos.
    fun waitKey(timeout: Long): Char {
        val startTime = Time.getTimeInMillis()
        var currentTime: Long
        var pressedKey: Char
        do {
            pressedKey = getKey()
            if (pressedKey != '!') {
                break
            }
            currentTime = Time.getTimeInMillis()
        } while (currentTime - startTime < timeout)
        TUI.ack()
        return pressedKey
    }
}

