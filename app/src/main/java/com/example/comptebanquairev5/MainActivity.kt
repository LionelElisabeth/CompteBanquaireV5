package com.example.comptebanquairev5


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val intent : Intent = Intent(this, Login::class.java)
        startActivity(intent)
    }


    override fun onStart() {
        println("__ onStart MainActivity __")
        super.onStart()


        // setup local secure dB
        try {
            var storedData: String = LocalStorageManager(staticStrings.accountFileName, getApplicationContext()).readFile()

            Account.staticAccounts = JSONParser(storedData).parseAccounts()

            updateSpinnerValues(Account.staticAccounts)
        } catch (e: Exception) {// setup local secure dB
            println(e)
        }


        // Setup bouton
        val bouton_Ref = findViewById(R.id.button_refresh) as Button

        bouton_Ref.setOnClickListener {
            refreshAccounts(staticStrings.urlAccount)
        }

        println("__ end onStart __")
    }


    fun updateSpinnerValues(accounts: List<Account>) {
        println("__ updateSpinnerValues __")

        var accountNames: ArrayList<String> = arrayListOf()
        accounts.forEach { accountNames.add(it.accountName) }


        val spinnerSrc = findViewById<Spinner>(R.id.spinner_AccountNames)
        if (spinnerSrc != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, accountNames.toTypedArray()
            )
            spinnerSrc.adapter = adapter
        }
        spinnerSrc.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            //Action to do when selected
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                findViewById<TextView>(R.id.textView_Amount).text =
                    Account.staticAccounts[position].amount.toString()
                findViewById<TextView>(R.id.textView_Iban).text =
                    Account.staticAccounts[position].iban.toString()
                findViewById<TextView>(R.id.textView_Currency).text =
                    Account.staticAccounts[position].currency.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }


    fun refreshAccounts(url: String) {
        println("__ connectToInternet __")

        var resultRequest: String = TLSConnection(url).getData()
        var accounts: List<Account> = JSONParser(resultRequest).parseAccounts()

        Account.staticAccounts = accounts
        LocalStorageManager(staticStrings.accountFileName, getApplicationContext()).writeFile(resultRequest)

        println(Account.staticAccounts)

        updateSpinnerValues(accounts)

    }


}