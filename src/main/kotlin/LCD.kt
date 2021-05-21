import isel.leic.UsbPort
import isel.leic.utils.Time
import java.lang.Integer
import java.util.concurrent.TimeoutException

object LCD {
    // Escreve no LCD usando a interface a 4bits.
    private const val LINES = 2
    private const val COLS = 16
    // Dimensão do display.

    // Escreve um nibble de comando/dados no LCDprivate
    private fun writeNibble(rs: Boolean, data: Int){
        //e,rs,outro
        val rsBit = if(rs) 16 else 0
        val E_MASK = 0x20 // 00100000
        val RS_MASK = 0x10 //00010000
        val D7 = 0x08 //00001000
        val D6 = 0x04 //00000100
        val D5 = 0x02 //00000010
        val D4 = 0x01 // 00000001
        HAL.writeBits(RS_MASK,rsBit)
        Time.sleep(5)
        HAL.writeBits(E_MASK+RS_MASK,rsBit + E_MASK)
        Time.sleep(5)
        HAL.writeBits(E_MASK+RS_MASK+D7+D6+D5+D4,data+E_MASK+rsBit)
        Time.sleep(5)
        HAL.writeBits(E_MASK,0)
    }

    // Escreve um byte de comando/dados no LCDprivate
    private fun writeByte(rs: Boolean, data: Int){
        writeNibble(rs,data shr 4)
        Time.sleep(5)
        writeNibble(rs,(data and 0x0F))
            //Time.sleep(100)
    }

    // Escreve um comando no LCDprivate
    private fun writeCMD(data: Int){
        writeByte(false,data)
    }

    // Escreve um dado no LCDprivate
    private fun writeDATA(data: Int){
        writeByte(true,data)
    }

    // Envia a sequência de iniciação para comunicação a 4bits.
    fun init(){
        Time.sleep(16)
        writeNibble(false,3)
        Time.sleep(5)
        writeNibble(false,3)
        Time.sleep(1)
        writeNibble(false,3)
        writeNibble(false,2)
        writeCMD(40)
        writeCMD(8)
        writeCMD(1)
        writeCMD(6)
        writeCMD(15)
    }

    // Escreve um caráter na posição corrente.
    fun write(c: Char){
        writeDATA(c.toInt())
    }

    // Escreve uma string na posição corrente.
    fun write(text: String){
        var i = 0
        val shortest = if(text.length<16)text.length-1 else 15
        for (i in 0..shortest){
            LCD.write(text[i])
        }
        cursor(2,1)
        if(shortest==15 && text.length-1!=15){
            for(i in shortest..text.length){
                LCD.write(text[i])
            }
        }
    }

    // Envia comando para posicionar cursor (‘line’:0..LINES-1 , ‘column’:0..COLS-1)
    fun cursor(line: Int,column: Int){
        //não funcemina bem
        writeCMD(2)
        val totalShifts = line*column
        if(totalShifts>0){
            for(i in 1..totalShifts){
                writeCMD(16)
            }
        }
    }

    // Envia comando para limpar o ecrã e posicionar o cursor em (0,0)
    fun clear(){
        writeCMD(1)
    }
}
