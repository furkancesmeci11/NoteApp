package com.example.denemeproje.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.denemeproje.databinding.ActivityAddNoteBinding
import com.example.denemeproje.ui.viewmodel.NoteViewModel


class AddNoteActivity : AppCompatActivity() {
    private val noteViewModel: NoteViewModel by viewModels()
    private var noteId: Int? = null
    private lateinit var binding: ActivityAddNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tek bir gözlemci
        noteViewModel.toastMessage.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

            // Eğer başarılı bir işlemse Activity'yi kapat
            if (message == "Not başarıyla kaydedildi" || message == "Not başarıyla güncellendi" || message == "Not silindi") {
                finish()
            }
        }

        // Mevcut notu doldur
        noteId = intent.getIntExtra("note_id", -1).takeIf { it != -1 }
        noteId?.let { id ->
            noteViewModel.getNoteById(id).observe(this) { note ->
                note?.let {
                    binding.etNoteTitle.setText(it.title)
                    binding.etNoteDescription.setText(it.description)
                    binding.btnDeleteNote.visibility = View.VISIBLE
                }
            }
        }

        // Kaydet butonu
        binding.btnSaveNote.setOnClickListener {
            val title = binding.etNoteTitle.text.toString()
            val description = binding.etNoteDescription.text.toString()

            noteViewModel.saveNote(
                noteId = noteId,
                title = title,
                description = description
            )
        }

        // Silme butonu
        binding.btnDeleteNote.setOnClickListener {
            noteId?.let { id ->
                noteViewModel.deleteNoteById(id)
            }
        }

        // Geri butonu
        binding.backBtn.setOnClickListener {
            val intent = Intent(this@AddNoteActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
