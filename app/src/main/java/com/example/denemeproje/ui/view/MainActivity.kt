package com.example.denemeproje.ui.view

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.denemeproje.data.adapter.NoteAdapter
import com.example.denemeproje.databinding.ActivityMainBinding
import com.example.denemeproje.ui.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val noteViewModel: NoteViewModel by viewModels() // viewmodeli bagladik
    private lateinit var adapter: NoteAdapter //Recyclerview adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        adapter = NoteAdapter(emptyList()) { note ->
            // tiklanan not icin  duzenleme ekranina git
            val intent = Intent(this@MainActivity, AddNoteActivity::class.java)
            intent.putExtra("note_id", note.id)
            startActivity(intent)
        }

        noteViewModel.allNotes.observe(this){notes->
            adapter = NoteAdapter(notes){note ->
                val intent = Intent(this@MainActivity, AddNoteActivity::class.java)
                intent.putExtra("note_id",note.id)
                startActivity(intent)

            }
            binding.rvNotes.adapter = adapter

        }


        binding.apply {
            btnAddNote.setOnClickListener {
                val intent = Intent(this@MainActivity, AddNoteActivity::class.java)
                startActivity(intent)
            }

            rvNotes.layoutManager = LinearLayoutManager(this@MainActivity)
            rvNotes.adapter = adapter
        }
    }
}
