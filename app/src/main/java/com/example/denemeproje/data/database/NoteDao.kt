package com.example.denemeproje.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.denemeproje.data.model.NoteModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM note_table ORDER BY note_title ASC")// tum notlari al
    fun getAllNotes(): Flow<List<NoteModel>>

    @Query("SELECT * FROM note_table WHERE note_id = :id")// id'ye gore notu getir
    fun getNoteById(id: Int): Flow<NoteModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteModel)

    @Update
    suspend fun updateNote(note: NoteModel)

    @Delete
    suspend fun deleteNote(note: NoteModel):Int

    @Query("DELETE FROM note_table")
    suspend fun deleteAllNote()


}