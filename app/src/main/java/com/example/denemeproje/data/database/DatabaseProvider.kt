package com.example.denemeproje.data.database

import android.content.Context
import androidx.room.Room
import kotlin.concurrent.Volatile

object DatabaseProvider{

    @Volatile
    private var INSTANCE : NoteDataBase? = null


    fun getDatabase(context: Context) : NoteDataBase{
        return INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(
                context.applicationContext,
                NoteDataBase::class.java,
                "note_database"

            ).build()
            INSTANCE = instance
            instance
        }
    }
}