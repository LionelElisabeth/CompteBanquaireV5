package com.example.comptebanquairev5


import java.io.BufferedReader
import java.io.InputStream
import java.net.URL
import java.net.URLConnection
import kotlin.concurrent.thread

class TLSConnection(val url : String) {

    fun getData() : String{
        println("__ connectToInternet __")
        var str: String = ""


        var t = thread {

            try {

                val url = URL(url)
                val urlConnection: URLConnection = url.openConnection()
                val inputStream: InputStream = urlConnection.getInputStream()

                val reader = BufferedReader(inputStream.reader())


                println("__ parseReader __")


                try {

                    str = reader.readText()


                } catch (e: Exception) {
                    println(e)
                    str = "ERROR!"
                } finally {
                    reader.close()
                }
            }
            catch(e:Exception){
                println(e)
                println("Could not connect to internet")
            }

        }
        t.join()

        return str
    }
}