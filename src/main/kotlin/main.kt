import isel.leic.UsbPort
import isel.leic.utils.Time

//usbIn retorna um valor entre 0 a 255 bits representando o valor dos inputs do UsbPort (Binary->Decimal)
//usbOut aceita um valor em hexadecimal representando o valor dos outputs do programa (hexadecimal->Binary)
//Mask=Identidade do peso do bit(128|64|32|16|8|4|2|1)

fun main(args: Array<String>) {
    val usbIn = UsbPort.`in`()
    LCD.init()
    Time.sleep(1000)
    print("ready")
    LCD.write("ff aos 15")
    //Time.sleep(1000)
    //LCD.clear()
    //LCD.write("Alga next semester")
}