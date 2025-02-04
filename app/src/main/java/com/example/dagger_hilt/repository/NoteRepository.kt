package com.example.dagger_hilt.repository

import com.example.dagger_hilt.db.NoteDao
import com.example.dagger_hilt.model.Note
import javax.inject.Inject

class NoteRepository @Inject constructor(
    val noteDao: NoteDao
) {
    suspend fun saveNote(note: Note) = noteDao.insertNote(note)
    suspend fun updateNote(note: Note) = noteDao.updateNote(note)
    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)
    fun getAllNote() = noteDao.getAllData()
    fun getNote(id: Int) = noteDao.getNote(id)
}