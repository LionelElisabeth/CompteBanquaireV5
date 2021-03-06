package com.example.comptebanquairev5


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class JSONParser (val strContent : String){

    fun parseAccounts() : List<Account>{
        println("__ parseReader Accounts __")

        var inter: List<Account> = listOf()
        try {
            val gson = Gson()
            var mapType = object : TypeToken<List<Account>>() {}.type

            inter = gson.fromJson(this.strContent, mapType)

        } catch (e: Exception) {
            println(e)
        }
        return inter

    }


    fun parseUsers(): List<User>{
        println("__ parseReader Users __")

        var inter: List<User> = listOf()
        try {
            val gson = Gson()
            var mapType = object : TypeToken<List<User>>() {}.type

            inter = gson.fromJson(this.strContent, mapType)

        } catch (e: Exception) {
            println(e)
        }
        return inter

    }




}