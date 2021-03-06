package com.example.comptebanquairev5


class Account(
    val id: Int,
    val accountName: String,
    val amount: Double,
    val iban: String,
    val currency: String

) {

    companion object {
        var staticAccounts: List<Account> = listOf()
    }

    /*
        constructor(   id: Objects, accountName:String, amount:Objects, iban:Objects, currency:Objects){
            this.id = id
            this.accountName = accountName
            this.amount = amount
            this.iban = iban
            this.currency = currency
        }
    */
    override fun toString(): String {
        return "account (${id} '${this.accountName}'  ${amount}  '${iban}'  '${currency}'"

    }
}
