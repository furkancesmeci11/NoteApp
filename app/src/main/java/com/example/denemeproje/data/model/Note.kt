package com.example.denemeproje.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.denemeproje.util.Constants.DATABASE_TABLE

@Entity(tableName = DATABASE_TABLE)
data class NoteModel (
    @ColumnInfo(name = "note_id")
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @ColumnInfo(name = "note_title")
    val title : String,
    @ColumnInfo(name = "note_description")
    val description : String,
    @ColumnInfo(name = "note_timestamp")
    val timestamp : Long = System.currentTimeMillis()//eklenen tarih
)