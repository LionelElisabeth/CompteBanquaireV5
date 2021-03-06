package com.example.comptebanquairev5

class User (val id : Int, val name : String, val lastname : String) {

    override fun toString(): String {
        return "user ( ${id} '${name}'  '${lastname}' )"

    }
}