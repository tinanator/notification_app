package com.example.demo_app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlin.concurrent.fixedRateTimer

const val EXTRA_MESSAGE = "com.example.demo_app.MESSAGE"

private lateinit var recyclerView: RecyclerView
private lateinit var viewAdapter: RecyclerView.Adapter<*>
private lateinit var viewManager: RecyclerView.LayoutManager

val allData = DataModel()

lateinit var fragment : ItemFragment

class MainActivity : AppCompatActivity(), ItemFragment.onItemClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle = intent.extras
        if (bundle != null) {
            val message = bundle.getParcelable<Data>("message")

            if (message != null) {
                allData.addData(message)
                fragment = supportFragmentManager.findFragmentById(R.id.list) as ItemFragment
                (fragment.view as RecyclerView).adapter!!.notifyDataSetChanged()
            }
        }
    }

    override fun showDetails(data:Data) {
        Toast.makeText(this, "name: " + data.getName(), Toast.LENGTH_SHORT).show()
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("details", data)
        startActivity(intent)
    }

    fun addData(view: View) {
        val intent = Intent(this, DisplayMessageActivity::class.java)
        startActivity(intent)
    }

    //val builder = NotificationCompat.Builder(this, )

//    private fun createNotificationChannel() {
//        // Create the NotificationChannel, but only on API 26+ because
//        // the NotificationChannel class is new and not in the support library
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val name = getString(R.string.)
//            val descriptionText = getString(R.string.channel_description)
//            val importance = NotificationManager.IMPORTANCE_DEFAULT
//            val channel = NotificationChannel(Build.ID, name, importance).apply {
//                description = descriptionText
//            }
//            // Register the channel with the system
//            val notificationManager: NotificationManager =
//                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//    }



}