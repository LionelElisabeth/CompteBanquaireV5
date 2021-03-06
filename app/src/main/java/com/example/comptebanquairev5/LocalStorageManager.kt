package com.example.comptebanquairev5


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import java.io.*

class LocalStorageManager(val filename: String, val context: Context) {

    fun fileExist() : Boolean {
        var exists : Boolean = false

        try{
            if ("" !=this.readFile())
            {exists= true}
        }
        catch (e: Exception){
            println(e)
        }

        return exists
    }

    fun readFile(): String {
        println("__ readFile __")
        var str: String = ""
        try {


            var fileInputStream: FileInputStream?  = this.context.openFileInput(filename)
            var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder: StringBuilder = StringBuilder()
            var text: String? = null
            while ({ text = bufferedReader.readLine(); text }() != null) {
                stringBuilder.append(text)
            }
            //Displaying data on EditText
            str = stringBuilder.toString()

        } catch (e: Exception) {
            println("ERROR ! Could not read file")
            println(e)
        }
        return str
    }

    fun writeFile(content: String) {
        println("__ writeFile __")
        try {

            val fileOutputStream: FileOutputStream
            try {
                fileOutputStream = this.context.openFileOutput(filename, Context.MODE_PRIVATE)
                fileOutputStream.write(content.toByteArray())

            } catch (e: Exception) {
                e.printStackTrace()
            }


        } catch (e: Exception) {
            println("ERROR ! Could not write file")
            println(e)
        }
    }


}