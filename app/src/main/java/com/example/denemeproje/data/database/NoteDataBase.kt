package com.example.denemeproje.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.denemeproje.data.model.NoteModel

@Database(entities = [NoteModel::class], version = 1, exportSchema = false)
abstract class NoteDataBase : RoomDatabase(){
    abstract fun noteDao(): NoteDao
}