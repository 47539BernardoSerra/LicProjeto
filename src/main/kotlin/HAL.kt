import isel.leic.UsbPort
import kotlin.math.*

object HAL {
// Virtualiza o acesso aosistemaUsbPort
// Inicia a classe
    fun init() {

    }
// Retorna true se o bit tiver o valor lógico ‘1’
    fun isBit(mask: Int): Boolean {
        val usbIn = UsbPort.`in`().inv()
        val anded = mask and usbIn
        return anded == mask
}

// Retorna os valores dos bits representados por mask presentes no UsbPort
    fun readBits(mask: Int): Int{
        val usbIn = UsbPort.`in`().inv()
        return usbIn and mask
    }

    // Escreve nos bits representados por mask o valor de value
    fun writeBits(mask: Int, value: Int) {
        val usbIn = UsbPort.`in`().inv()
        UsbPort.`out`((((mask xor 255)and(usbIn)) or value).inv())
}

// Coloca os bits representados por mask no valor lógico ‘1’
    fun setBits(mask: Int) {
        val usbIn = UsbPort.`in`().inv()
        UsbPort.out((mask or usbIn).inv())
}
// Coloca os bits representados por mask no valor lógico ‘0’
    fun clrBits(mask: Int) {
        val usbIn = UsbPort.`in`().inv()
        (UsbPort.`out`(((mask xor 255)and(usbIn)).inv()))
}
}