package com.example.to_dolistpro

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version = 1)
abstract class ToDoListProDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        fun get(context: Context): ToDoListProDatabase {
            return Room.databaseBuilder(
                context,
                ToDoListProDatabase::class.java,
                "ToDoListPro_db"
            ).allowMainThreadQueries() //just to learn
                .build()
        }
    }
}