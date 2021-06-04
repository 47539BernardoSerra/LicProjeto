import isel.leic.UsbPort

object HAL {
    // Virtualiza o acesso aosistemaUsbPort
// Inicia a classe
    private var output: Int = 0

    fun init() {

    }

    // Retorna true se o bit tiver o valor lógico ‘1’
    fun isBit(mask: Int): Boolean {
        val usbIn = UsbPort.`in`().inv()
        val anded = mask and usbIn
        return anded == mask
    }

    // Retorna os valores dos bits representados por mask presentes no UsbPort
    fun readBits(mask: Int): Int {
        val usbIn = UsbPort.`in`().inv()
        return usbIn and mask
    }

    // Escreve nos bits representados por mask o valor de value
    fun writeBits(mask: Int, value: Int) {
        val newOut = (mask.inv() and output) or (mask and value)
        UsbPort.out((newOut).inv())
        output = newOut
    }

    // Coloca os bits representados por mask no valor lógico ‘1’
    fun setBits(mask: Int) {
        val newOut = output or mask
        UsbPort.`out`(newOut.inv())
        output = newOut
    }

    // Coloca os bits representados por mask no valor lógico ‘0’
    fun clrBits(mask: Int) {
        val newOut = (mask and 255).inv() and output
        (UsbPort.`out`(newOut.inv()))
        output = newOut
    }
}