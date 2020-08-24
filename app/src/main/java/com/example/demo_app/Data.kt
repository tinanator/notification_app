package com.example.demo_app

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.CoroutineScope
import java.sql.Time
import java.util.*


@Parcelize
@Entity
data class Reminder (
        @ColumnInfo val name: String?,
        @ColumnInfo val year: Int,
        @ColumnInfo val month: Int,
        @ColumnInfo val day: Int,
        @ColumnInfo val minute: Int,
        @ColumnInfo val hour: Int,
        @ColumnInfo val clientName: String?,
        @ColumnInfo val clientEmail: String?
) : Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}

@Dao
interface ReminderDao {
    @Query("SELECT * FROM Reminder")
    fun getAll(): LiveData<List<Reminder>>

    @Query("SELECT * FROM Reminder WHERE id IN (:ReminderIds)")
    fun loadAllByIds(ReminderIds: IntArray): List<Reminder>

    @Insert
    suspend fun insertAll(vararg rem: Reminder)

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

        fun getAppDataBase(
            context: Context,
            viewModelScope: CoroutineScope
        ): AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "MyDatabase.db")
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}
