package com.example.denemeproje.data.repository
import com.example.denemeproje.data.database.NoteDao
import com.example.denemeproje.data.model.NoteModel
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {
    // bu sinifin amaci daoya bagimli kalmali engellemek viemodelden istek buraya gelir buradan daoya gider.
    // tum notlari getirir
    val allNotes: Flow<List<NoteModel>> = noteDao.getAllNotes()

    // Id'ye gore notlari getirir.
    fun getNoteById(id:Int): Flow<NoteModel> {
        return noteDao.getNoteById(id)
    }

    // Yeni note eklemek icin kullanilir
    suspend fun insert(note : NoteModel){
        noteDao.insertNote(note)
    }

    suspend fun update(note: NoteModel){
        noteDao.updateNote(note)
    }

    suspend fun delete(note: NoteModel){
        noteDao.deleteNote(note)
    }

    suspend fun deleteAll(note: NoteModel){
        noteDao.deleteAllNote()
    }
}