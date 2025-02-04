package com.example.dagger_hilt.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.dagger_hilt.model.Note
import com.example.dagger_hilt.util.Constants.TABLE_NAME

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertNote(note: Note)

    @Update
     suspend fun updateNote(note: Note)

    @Delete
     suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM $TABLE_NAME ORDER BY noteId DESC")
    fun getAllData(): LiveData<List<Note>>

    @Query("SELECT * FROM $TABLE_NAME WHERE noteId like :id")
    fun getNote(id: Int): LiveData<Note>
}