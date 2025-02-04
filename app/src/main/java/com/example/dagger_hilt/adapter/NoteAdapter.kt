package com.example.dagger_hilt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dagger_hilt.R
import com.example.dagger_hilt.model.Note
import javax.inject.Inject

class NoteAdapter @Inject constructor()
    : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    lateinit var title: TextView
    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    val differCallBack = object: DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.noteId == newItem.noteId
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = differ.currentList[position]

        title = holder.itemView.findViewById(R.id.txvTitle)

        holder.itemView.apply {
            title.text = note.noteTitle
            setOnClickListener {
                onItemClick?.let { it(note) }
            }
        }
    }

    private var onItemClick: ((Note) -> Unit)? = null
    fun onItemClickListener(listener: (Note) -> Unit) {
        onItemClick = listener
    }
}