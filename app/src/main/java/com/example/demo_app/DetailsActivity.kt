package com.example.demo_app

import android.R.attr.fragment
import android.R.attr.key
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView


class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_fragment_test_fragment)
        val bundle = intent.extras
        if (bundle != null) {
            val message = bundle.getParcelable<Data>("details")

            if (message != null) {
                findViewById<TextView>(R.id.show_name).text = message.getName()
                findViewById<TextView>(R.id.show_date).text = message.getDate().toString()
                findViewById<TextView>(R.id.show_time).text = message.getTime().toString()
                findViewById<TextView>(R.id.client_name).text = message.getClientName()
                findViewById<TextView>(R.id.client_email).text = message.getClientEmail()
            }
        }


    }

}
