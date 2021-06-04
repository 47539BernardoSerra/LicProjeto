object Door {
    //controla o mecanismo da porta

    // Inicia a classe, estabelecendoos valores iniciais.
    fun init(data : Int){
        HAL.writeBits(31, data)
        HAL.setBits(64)
    }
    // Envia comando paraabrir a porta, indicando a velocidade
    fun open(speed: Int){
        init(0x1 or speed shl 1)
    }
    // Envia comando para fechar a porta, indicando a velocidade
    fun close(speed: Int){
        init(0x0 or speed shl 1)
    }

    // Retorna true se o tiverterminado o comando
    fun isFinished(){

    }
}