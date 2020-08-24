package com.example.demo_app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Connect to db via repository
 */
class ReminderViewModel(application: Application) : AndroidViewModel(application) {
    val allReminders: LiveData<List<Reminder>>
    private val remRepository : ReminderRepository
    init {
        val remDao = AppDatabase.getAppDataBase(application, viewModelScope)!!.reminderDao()
        remRepository = ReminderRepository(remDao)
        allReminders = remRepository.allReminders
    }

    /**
     * Launching a new coroutine to insert the data
     */
    fun insert(rem: Reminder) : Int
    {
        var id : Int = 0
        viewModelScope.launch(Dispatchers.IO) {
            id = remRepository.insert(rem)
        }
        return id
    }
}