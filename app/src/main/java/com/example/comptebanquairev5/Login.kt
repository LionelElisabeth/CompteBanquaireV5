package com.example.comptebanquairev5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.textfield.TextInputEditText

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        println("__ onCreate : LOGIN __")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onStart() {
        println("__ onStart : LOGIN __")
        super.onStart()


        getUsers()


        // Setup bouton
        val bouton_Ref = findViewById(R.id.button_login) as Button

        bouton_Ref.setOnClickListener {
            checkUser(findViewById<TextInputEditText>(R.id.textView_loginId).text.toString())
        }


    }


    fun getUsers() {
        println("_ get users _")
        try {
            var userId: String = TLSConnection(staticStrings.urlUser).getData()

            var users: List<User> = JSONParser(userId).parseUsers()
            //println("Users found:")
            //users.forEach { println(it) }

            // updateSpinner
            updateSpinnerValues(users)

        } catch (e: Exception) {
            println(e)

            // put default value in spinner
        }

    }

    fun checkUser(idUser: String): Boolean {
        println("_ CheckUser _")

        var localstorage = LocalStorageManager(staticStrings.userFileName, getApplicationContext())
        // check if file user.txt exists
        val fileExist: Boolean = localstorage.fileExist()
        println("The file \"user.txt\", exists : $fileExist")

        // IF YES : compare with value in file
        if (fileExist) {
            val varFile: String = localstorage.readFile()
            if (varFile == idUser) {
                // login user
                login(idUser)

            } else {
                // send error msg
                Toast.makeText(this, "Wrong user", Toast.LENGTH_SHORT).show()
                println("Wrong user tried logging in.")
            }
        }

        // OTHERWISE : create file and save id
        else {
            localstorage.writeFile(idUser)
            println("File created.")
            login(idUser)


            println("The file \"user.txt\", exists : "+localstorage.fileExist())
            println("The file \"user.txt\", content : "+localstorage.readFile())
        }

        return true
    }

    fun login(idUser: String) {
        println("Logging in as user : $idUser")
        this.finish()
    }


    fun updateSpinnerValues(users: List<User>) {
        println("__ updateSpinnerValues __")

        var userNames: ArrayList<String> = arrayListOf()
        users.forEach { userNames.add(it.name) }


        val spinnerSrc = findViewById<Spinner>(R.id.spinner_LoginUsers)
        if (spinnerSrc != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, userNames.toTypedArray()
            )
            spinnerSrc.adapter = adapter
        }
        spinnerSrc.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            //Action to do when selected
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                findViewById<TextView>(R.id.textView_loginInformation).text =
                    users[position].toString()
                findViewById<TextInputEditText>(R.id.textView_loginId).setText(users[position].id.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
}
