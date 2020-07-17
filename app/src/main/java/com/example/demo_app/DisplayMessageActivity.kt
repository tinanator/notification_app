package com.example.demo_app

import android.R.attr.fragment
import android.R.attr.key
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_display_message.*
import kotlinx.android.synthetic.main.fragment_item.*
import java.sql.Time
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*


class DisplayMessageActivity : AppCompatActivity() {

    private var year = 2020
    private var month = 7
    private var day = 17

    private var hour = 0
    private var minute = 0

    lateinit var calendar : GregorianCalendar

    @RequiresApi(Build.VERSION_CODES.O)
    val formatter = DateTimeFormatter.ofPattern("HH mm")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)
        val message = intent.getStringExtra(EXTRA_MESSAGE)

    }

    fun add(view: View) {
        val name = findViewById<EditText>(R.id.textView).text
        val pos = 2
        //val date = findViewById<EditText>(R.id.editTextDate).text
        val data = Data(name.toString(), year, month, day, hour, minute)
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("message", data)
        }
        startActivity(intent)
    }

    fun chooseClient(view: View) {
        val intent = Intent(this, ClientsActivity::class.java)
        startActivity(intent)
    }


    override fun onCreateDialog(id: Int, args: Bundle?): Dialog? {
        if (id == 0) {
            return DatePickerDialog(this, dateSetListener, year, month, day)
        }
        if (id == 1) {
            return TimePickerDialog(this, timeSetListener, hour, minute, true)
        }
        return null
    }

    fun chooseDate(view:View){
        showDialog(0)
    }

    fun setTime(view:View) {
        showDialog(1)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker: TimePicker, hour_: Int, minute_: Int ->
        hour = hour_
        minute = minute_

        calendar = GregorianCalendar(year, month, day, hour, minute)



        findViewById<TextView>(R.id.editTextTime).text = LocalTime.of(hour, minute).toString()
          //  calendar.get(Calendar.HOUR).toString() + ':' + calendar.get(Calendar.MINUTE)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    val dateSetListener = DatePickerDialog.OnDateSetListener { datePicker: DatePicker, year_: Int, month_: Int, day_: Int ->

        year = year_
        month = month_
        day = day_

        calendar = GregorianCalendar(year, month, day)
        findViewById<TextView>(R.id.editTextDate).text = LocalDate.of(year, month, day).toString()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateDialog(id: Int): Dialog {
        return DatePickerDialog(this)
    }

}