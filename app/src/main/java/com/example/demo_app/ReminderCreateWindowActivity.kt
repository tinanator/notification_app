package com.example.demo_app

import android.annotation.SuppressLint
import android.app.*
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.time.ExperimentalTime
import kotlin.time.days
import kotlin.time.hours
import kotlin.time.minutes


class ReminderCreateWindowActivity : AppCompatActivity() {

    private var year = 2020
    private var month = 7
    private var day = 17

    private var hour = 0
    private var minute = 0

    private var clientName = ""
    private var clientEmail = ""

    private lateinit var calendar : GregorianCalendar

    @RequiresApi(Build.VERSION_CODES.O)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reminder_create_window)
        calendar = GregorianCalendar()
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            clientName =
                  "${data!!.extras!!.get("first").toString()} ${data.extras!!.get("last")
                             .toString()}"
            clientEmail =  data.extras!!.get("email").toString()
            findViewById<TextView>(R.id.show_client_name).text = clientName
            findViewById<TextView>(R.id.show_email).text = clientEmail
        }
    }

    private var alarmMgr: AlarmManager? = null
    private lateinit var alarmIntent: Intent
    private lateinit var alarmPendingIntent: PendingIntent

    @ExperimentalTime
    @SuppressLint("ShowToast")
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun add(view: View) {
        val name = findViewById<EditText>(R.id.textView).text
        val pos = 2
        //val date = findViewById<EditText>(R.id.editTextDate).text
        val rem = Reminder(name.toString(), year, month, day, hour, minute, clientName, clientEmail)
        val reminderViewModel = ViewModelProvider(this).get(ReminderViewModel::class.java)
        val id = reminderViewModel.insert(rem)
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("message", rem)
        }

         alarmIntent = Intent(applicationContext, NotifierAlarmReceiver::class.java)
       // val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

         alarmPendingIntent =
            PendingIntent.getBroadcast(
                applicationContext,
                SystemClock.elapsedRealtime().toInt(),
                alarmIntent,
                id
            )
        Log.i("id", id.toString())
        alarmMgr = getSystemService(ALARM_SERVICE) as AlarmManager
       // alarmMgr!!.setExact(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + 10*1000, alarmPendingIntent)
        alarmMgr!!.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.timeInMillis, alarmPendingIntent)
        Log.i("time", calendar.timeInMillis.hours.toString() + ':' + calendar.timeInMillis.minutes.toString())
       // Toast.makeText(this, calendar.timeInMillis.toString(), Toast.LENGTH_LONG)
        startActivity(intent)
    }

    fun chooseClient(view: View) {
        val intent = Intent(this, ClientsActivity::class.java)
        startActivityForResult(intent, 0)
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

        calendar.set(year, month, day, hour, minute)

        Log.i("time222", "$hour $minute")
        Log.i("time333", calendar[GregorianCalendar.HOUR].toString() + ':' + calendar[GregorianCalendar.MINUTE].toString())
        findViewById<TextView>(R.id.editTextTime).text = LocalTime.of(hour, minute).toString()
          //  calendar.get(Calendar.HOUR).toString() + ':' + calendar.get(Calendar.MINUTE)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    val dateSetListener = DatePickerDialog.OnDateSetListener { datePicker: DatePicker, year_: Int, month_: Int, day_: Int ->

        year = year_
        month = month_
        day = day_

        calendar.set(year, month, day, hour, minute)

        findViewById<TextView>(R.id.editTextDate).text = LocalDate.of(year, month, day).toString()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateDialog(id: Int): Dialog {
        return DatePickerDialog(this)
    }

}