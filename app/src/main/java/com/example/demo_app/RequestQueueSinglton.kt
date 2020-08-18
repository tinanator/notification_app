package com.example.demo_app

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class RequestQueueSinglton constructor(context : Context) {
    companion object {
        @Volatile
        private var INSTANCE: com.example.demo_app.RequestQueueSinglton? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: RequestQueueSinglton(context).also {
                    INSTANCE = it
                }
            }
    }
    val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    fun<T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }

}