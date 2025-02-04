package com.example.dagger_hilt.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.dagger_hilt.R
import com.example.dagger_hilt.adapter.NoteAdapter
import com.example.dagger_hilt.databinding.FragmentAddNoteBinding
import com.example.dagger_hilt.model.Note
import com.example.dagger_hilt.repository.NoteRepository
import com.example.dagger_hilt.viewModel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class AddNoteFragment : Fragment(R.layout.fragment_add_note) {

    private lateinit var binding: FragmentAddNoteBinding

    private val noteViewModel: NoteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddNoteBinding.bind(view)

        binding.floatingActionBtnSave.setOnClickListener {
            checkTitleAndDescription()
        }
    }

    private fun checkTitleAndDescription() {
        val title = binding.edtTitle.text.toString()
        val description = binding.edtDescription.text.toString()

        if (title.isNotEmpty() && description.isNotEmpty()) {
            val note = Note(noteTitle = title, noteDescription = description)
            noteViewModel.saveNote(note)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addNoteFragment_to_noteListFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out title and description", Toast.LENGTH_SHORT).show()
        }
    }
}
