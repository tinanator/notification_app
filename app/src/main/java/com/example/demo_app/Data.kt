package com.example.demo_app

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import java.sql.Time
import java.util.*


//date in ms

class Data(name_: String?, year_:Int, month_: Int, day_:Int, hour_ : Int, minute_ : Int, clientName_: String?, clientEmail_: String?) : Parcelable {

    private val name = name_
    private val year = year_
    private val month = month_
    private val day = day_
    private val minute = minute_
    private val hour = hour_
    private val clientName = clientName_
    private val clientEmail = clientEmail_



    constructor(parcel: Parcel) : this(
       parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
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

    fun getClientName() : String? {
        return clientName
    }

    fun getClientEmail() : String? {
        return clientEmail
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(year)
        parcel.writeInt(month)
        parcel.writeInt(day)
        parcel.writeInt(hour)
        parcel.writeInt(minute)
        parcel.writeString(clientName)
        parcel.writeString(clientEmail)
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

@Entity
data class Reminder (
        @PrimaryKey(autoGenerate = true) val id: Int,
        @ColumnInfo val name: String?,
        @ColumnInfo val year: Int,
        @ColumnInfo val month: Int,
        @ColumnInfo val day: Int,
        @ColumnInfo val minute: Int,
        @ColumnInfo val hour: Int,
        @ColumnInfo val clientName: String?,
        @ColumnInfo val clientEmail: String?
) {

}

@Dao
interface ReminderDao {
    @Query("SELECT * FROM Reminder")
    fun getAll(): List<Reminder>

    @Query("SELECT * FROM Reminder WHERE id IN (:ReminderIds)")
    fun loadAllByIds(ReminderIds: IntArray): List<Reminder>

    @Insert
    fun insertAll(vararg rem: Reminder)

    @Delete
    fun delete(user: Reminder)

    @Insert
    suspend fun insert(rem: Reminder) : Long
}

@Database(entities = arrayOf(Reminder::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun reminderDao() : ReminderDao
    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "MyDatabase.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
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