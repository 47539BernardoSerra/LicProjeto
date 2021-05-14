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
        val key = when (HAL.readBits(15)) {
            7 -> '0'
            0 -> '1'
            4 -> '2'
            8 -> '3'
            1 -> '4'
            5 -> '5'
            9 -> '6'
            2 -> '7'
            6 -> '8'
            10 -> '9'
            11 -> '#'
            3 -> '*'
            else -> NONE.toChar()
        }
        if(key!=NONE.toChar()){HAL.writeBits(128,1)}
        return key
    }

    // Retorna quando a tecla for premida ou NONE após decorrido ‘timeout’ milisegundos.
    fun waitKey(timeout: Long): Char {
        val startTime = Time.getTimeInMillis()
        var currentTime : Long
        var pressedKey : Char
        do {
            pressedKey = getKey()
            if(pressedKey!=NONE.toChar()){break}
            currentTime = Time.getTimeInMillis()
        } while (currentTime - startTime < timeout)
        return pressedKey
    }
}

