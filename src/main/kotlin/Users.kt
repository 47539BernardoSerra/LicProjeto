import java.io.File
import java.lang.NumberFormatException

object Users {
    private const val USER_FILE="USERS.txt"

    fun init(){
    }

    fun addUser(name:String,pin:Int):String?{
        val id = firstAvailableId()
        val n = if(firstAvailableId()!=0)"\n" else ""
        val usersString = "$n$id;$pin;$name;0"
        FileAccess.writeText(usersString, USER_FILE)
        return "$id:$name"
    }

    private fun firstAvailableId():Int{
        val reader = FileAccess.createReader(USER_FILE)
        var thisLine : String
        var lastID : Int
        var thisID : Int = -1
        var firstID : Int = 0
        while (reader.ready()){
            lastID = thisID
            thisLine = reader.readLine().toString()
            thisID=thisLine.split(";")[0].toInt()
            if (thisID != lastID+1) {
                    firstID = lastID.toInt()+1
                break;
                }
            firstID = thisID+1
            }
        return firstID
    }

    fun removeUser(uin:String){

    }
}