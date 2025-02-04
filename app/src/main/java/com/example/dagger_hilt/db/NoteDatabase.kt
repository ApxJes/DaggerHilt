package com.example.dagger_hilt.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dagger_hilt.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}