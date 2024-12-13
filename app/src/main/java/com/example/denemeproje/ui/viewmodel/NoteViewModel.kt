package com.example.denemeproje.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.denemeproje.data.database.DatabaseProvider
import com.example.denemeproje.data.model.NoteModel
import com.example.denemeproje.data.repository.NoteRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NoteRepository
    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage
    val allNotes: LiveData<List<NoteModel>>

    init {
        val noteDao = DatabaseProvider.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        allNotes = repository.allNotes.asLiveData()
    }

    fun saveNote(noteId: Int?, title: String, description: String) = viewModelScope.launch {
        if (title.isNotEmpty() && description.isNotEmpty()) {
            if (noteId != null) {
                val updatedNote = NoteModel(id = noteId, title = title, description = description)
                repository.update(updatedNote)
                _toastMessage.postValue("Not başarıyla güncellendi")
            } else {
                val newNote = NoteModel(title = title, description = description)
                repository.insert(newNote)
                _toastMessage.postValue("Not başarıyla kaydedildi")
            }
        } else {
            _toastMessage.postValue("Gerekli alanları doldurunuz")
        }
    }


    fun deleteNoteById(noteId: Int) {
        viewModelScope.launch {
            val note = repository.getNoteById(noteId).firstOrNull()
            if (note != null) {
                repository.delete(note)
                _toastMessage.postValue("Not silindi")
            } else {
                _toastMessage.postValue("Not bulunamadı, silme işlemi iptal edildi.")
            }
        }
    }



    fun getNoteById(id: Int): LiveData<NoteModel> {
        return repository.getNoteById(id).asLiveData()
    }


}