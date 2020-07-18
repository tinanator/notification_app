package com.example.demo_app

import android.graphics.Bitmap
import android.media.Image
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import com.example.demo_app.dummy.DummyContent.DummyItem
import kotlinx.android.synthetic.main.fragment_clients.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter2(
    private val values: JSONArray, private val listener: (fname: String, lname: String, email: String) -> Unit
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter2.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_clients, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values.getJSONObject(position)

        Log.i("name", item.getJSONObject("name").toString())
        Log.i("first", item.getJSONObject("name")["first"].toString())

        holder.firstName.text = item.getJSONObject("name")["first"].toString()

        holder.lastName.text = item.getJSONObject("name")["last"].toString()
        holder.email.text = item["email"].toString()

        holder.itemView.setOnClickListener  {
            listener(
                holder.firstName.text as String, holder.lastName.text as String,
                holder.email.text as String
            )
            //selectedPos = position
            //notifyItemChanged(position)

        }

        val height = holder.img.height
        val width =  holder.img.width


        val url = item.getJSONObject("picture")["medium"].toString()
        val request: ImageRequest = ImageRequest(url, Response.Listener<Bitmap>{
            holder.img.setImageBitmap(it)
        }, width, height, null,
        Response.ErrorListener {})

        MySingleton.getInstance(holder.email.context).addToRequestQueue(request)

    }

    override fun getItemCount(): Int = values.length()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val firstName: TextView = view.findViewById(R.id.first_name)
        val lastName: TextView = view.findViewById(R.id.last_name)
        val email: TextView = view.findViewById(R.id.email)
        val img: ImageView = view.findViewById(R.id.image)


//        override fun toString(): String {
//           // return super.toString() + " '" + contentView.text + "'"
//        }
    }
}