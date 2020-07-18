package com.example.demo_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ClientsActivity : AppCompatActivity(), clientsFragment.onItemClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.clients_activity)
    }

    override fun chooseClient(firstName: String, lastName: String, email: String) {
        val intent = Intent()
        intent.putExtra("first", firstName)
        intent.putExtra("last", lastName)
        intent.putExtra("email", email)
        setResult(0, intent)
        finish()
    }

}