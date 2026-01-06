package com.example.to_dolistpro

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getAll(): List<TaskEntity>

    @Insert
    fun insert(task: TaskEntity)

    @Update
    fun update(task: TaskEntity)
    @Delete
    fun delete(task: TaskEntity)
}