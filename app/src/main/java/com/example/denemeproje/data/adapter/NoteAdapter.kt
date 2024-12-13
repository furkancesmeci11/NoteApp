package com.example.denemeproje.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.denemeproje.data.model.NoteModel
import com.example.denemeproje.databinding.ItemNoteBinding

class NoteAdapter(
    private var noteList : List<NoteModel>,
    private val onNoteClick : (NoteModel) -> Unit
):RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){


    inner class NoteViewHolder (private val binding: ItemNoteBinding):RecyclerView.ViewHolder(binding.root)
    {
        fun bind(note:NoteModel){
            binding.tvNoteTitle.text = note.title
            binding.tvNoteDescription.text = note.description
            binding.root.setOnClickListener { onNoteClick(note) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteViewHolder(binding)

    }

    override fun getItemCount(): Int = noteList.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        holder.bind(noteList[position])
    }

}