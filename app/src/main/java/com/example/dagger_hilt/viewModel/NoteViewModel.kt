package com.example.dagger_hilt.viewModel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dagger_hilt.model.Note
import com.example.dagger_hilt.repository.NoteRepository
import com.example.dagger_hilt.util.MyApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    val noteRepository: NoteRepository,
): ViewModel(){

    val getAllNote = noteRepository.getAllNote()
    fun saveNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.saveNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.deleteNote(note)
    }

    fun getNoteById(id: Int) = noteRepository.getNote(id)
}