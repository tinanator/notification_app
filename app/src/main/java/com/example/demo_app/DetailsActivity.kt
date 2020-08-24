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
import java.sql.Time
import java.util.*


class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_fragment_test_fragment)
        val bundle = intent.extras
        if (bundle != null) {
            val message = bundle.getParcelable<Reminder>("details")

            if (message != null) {
                findViewById<TextView>(R.id.show_name).text = message.name
                findViewById<TextView>(R.id.show_date).text = Date(message.year, message.month, message.day).toString()
                findViewById<TextView>(R.id.show_time).text = Time(message.hour, message.minute, 0).toString()
                findViewById<TextView>(R.id.client_name).text = message.clientName
                findViewById<TextView>(R.id.client_email).text = message.clientEmail
            }
        }


    }

}
