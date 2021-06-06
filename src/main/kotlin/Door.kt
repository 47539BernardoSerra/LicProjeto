import isel.leic.utils.Time

object Door {
    //controla o mecanismo da porta

    // Inicia a classe, estabelecendoos valores iniciais.
    fun init(data : Int){
        HAL.writeBits(31, data)
        HAL.setBits(64)
        Time.sleep(100)
        HAL.clrBits(64)
        while (isFinished()){}
    }
    // Envia comando paraabrir a porta, indicando a velocidade
    fun open(speed: Int){
        init((speed shl 1) or 0x1)
    }
    // Envia comando para fechar a porta, indicando a velocidade
    fun close(speed: Int){
        init((speed shl 1) or 0x0)
    }

    // Retorna true se o tiverterminado o comando
    fun isFinished(): Boolean{
        return HAL.isBit(64)
    }
}