package com.example.demo_app

import android.R.attr.fragment
import android.R.attr.key
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class DisplayMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)
        val message = intent.getStringExtra(EXTRA_MESSAGE)

    }

    fun add(view: View) {
        val name = findViewById<EditText>(R.id.textView).text
        val pos = 2
        //val date = findViewById<EditText>(R.id.editTextDate).text
        val data = Data(name.toString())
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("message", data)
        }
        startActivity(intent)
    }
}