package com.example.demo_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.EditText
import android.widget.Toast
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

}