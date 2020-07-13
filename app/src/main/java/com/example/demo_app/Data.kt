package com.example.demo_app

import android.os.Parcel
import android.os.Parcelable

class Data(name_: String?) : Parcelable {

    private val name = name_

    constructor(parcel: Parcel) : this(
       parcel.readString()
    )

    fun getName() : String? {
        return name
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
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