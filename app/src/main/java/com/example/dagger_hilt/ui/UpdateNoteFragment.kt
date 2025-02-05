package com.example.dagger_hilt.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dagger_hilt.R
import com.example.dagger_hilt.adapter.NoteAdapter
import com.example.dagger_hilt.databinding.FragmentUpdateNoteBinding
import com.example.dagger_hilt.model.Note
import com.example.dagger_hilt.viewModel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UpdateNoteFragment : Fragment(R.layout.fragment_update_note) {

    private lateinit var binding: FragmentUpdateNoteBinding
    val args: UpdateNoteFragmentArgs by navArgs()
    private val viewModel: NoteViewModel by viewModels()

    @Inject
    lateinit var adapter: NoteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpdateNoteBinding.bind(view)
        val note = args.note
        binding.edtUpdateTitle.setText(note.noteTitle)
        binding.edtUpdateDescription.setText(note.noteDescription)

        binding.floatingActionBtnSave.setOnClickListener {
            checkTitleAndDescription()
        }

        binding.floatingActionBtnDelete.setOnClickListener {
            deleteNote()
        }

    }

    private fun checkTitleAndDescription() {
        val title = binding.edtUpdateTitle.text.toString()
        val description = binding.edtUpdateDescription.text.toString()
        if(title.isNotEmpty() && description.isNotEmpty()) {
            val note = Note(noteTitle = title, noteDescription = description, noteId = args.note.noteId)
            viewModel.updateNote(note)
            Toast.makeText(
                requireContext(),
                "Successfully update",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateNoteFragment_to_noteListFragment)
        } else {
            Toast.makeText(
                requireContext(),
                "Please fills out all field",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun deleteNote(){
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Note!")
            .setMessage("Are you sure to delete ${args.note.noteTitle}")
            .setCancelable(true)
            .setPositiveButton("Yes"){_, _ ->
                val note = args.note
                viewModel.deleteNote(note)
                Toast.makeText(
                    requireContext(),
                    "Successfully deleted",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.action_updateNoteFragment_to_noteListFragment)
            }
            .setNegativeButton("Cancel"){_, _ ->
                Toast.makeText(
                    requireContext(),
                    "Cancel to delete",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .create().show()
    }
}