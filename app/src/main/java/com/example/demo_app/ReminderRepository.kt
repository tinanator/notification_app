package com.example.demo_app

import androidx.lifecycle.LiveData

class ReminderRepository(private val reminderDao: ReminderDao) {
    val allReminders: LiveData<List<Reminder>> = reminderDao.getAll()
    suspend fun insert(reminder: Reminder) : Int{
        return reminderDao.insert(reminder).toInt()
    }
}