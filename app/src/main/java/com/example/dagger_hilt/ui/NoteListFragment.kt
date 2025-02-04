package com.example.dagger_hilt.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dagger_hilt.R
import com.example.dagger_hilt.adapter.NoteAdapter
import com.example.dagger_hilt.databinding.FragmentNoteListBinding
import com.example.dagger_hilt.model.Note
import com.example.dagger_hilt.repository.NoteRepository
import com.example.dagger_hilt.viewModel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NoteListFragment : Fragment(R.layout.fragment_note_list) {

    private lateinit var binding: FragmentNoteListBinding
    private val noteViewModel: NoteViewModel by viewModels()

    @Inject
    lateinit var adapter: NoteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNoteListBinding.bind(view)
        setUpRecyclerView()

        noteViewModel.getAllNote.observe(viewLifecycleOwner){notes ->
            if(notes.isNotEmpty()){
                binding.rcvNoteList.visibility = View.VISIBLE
                adapter.differ.submitList(notes)
                adapter.notifyDataSetChanged()
            } else {
                binding.rcvNoteList.visibility = View.GONE
            }
        }

        binding.floatingActionBtnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_noteListFragment_to_addNoteFragment)
        }
    }

    private fun setUpRecyclerView(){
        binding.rcvNoteList.apply {
            adapter = this@NoteListFragment.adapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}