package com.example.demo_app

import android.os.Parcel
import android.os.Parcelable
import java.sql.Time
import java.text.SimpleDateFormat
import java.time.Month
import java.util.*

//date in ms

class Data(name_: String?, year_:Int, month_: Int, day_:Int, hour_ : Int, minute_ : Int) : Parcelable {

    private val name = name_
    private val year = year_
    private val month = month_
    private val day = day_
    private val minute = minute_
    private val hour = hour_



    constructor(parcel: Parcel) : this(
       parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    fun getName() : String? {
        return name
    }

    fun getDate() : Date {
        return Date(year, month, day)
    }

    fun getTime() : Time {
        return Time(hour, minute, 0)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(year)
        parcel.writeInt(month)
        parcel.writeInt(day)
        parcel.writeInt(hour)
        parcel.writeInt(minute)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Data> {
        override fun createFromParcel(parcel: Parcel): Data {
            return Data(parcel)
        }

        override fun newArray(size: Int): Array<Data?> {
            return arrayOfNulls(size)
        }
    }

}


class DataModel() {
    private val dataset = mutableListOf<Data>()
    private var size = 0
    fun addData(item: Data) {
        dataset.add(item)
        size += 1
    }

    fun getData() : MutableList<Data> {
        return dataset
    }

    fun getSize() : Int {
        return size
    }

    fun clearData() {
        dataset.clear()
        size = 0
    }
}