object SerialReceiver {
    fun init(){
    }

   fun rcv():Int{
       var finalBits = 0
       for(i in 0..3){
           val currentBit = HAL.readBits(1)
           val shiftedBit = currentBit shl i
           finalBits = finalBits and shiftedBit
       }
       return finalBits and HAL.readBits(128)
   }
}