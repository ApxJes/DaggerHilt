package com.example.dagger_hilt.di

import android.content.Context
import androidx.room.Room
import com.example.dagger_hilt.db.NoteDatabase
import com.example.dagger_hilt.model.Note
import com.example.dagger_hilt.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideDao(db: NoteDatabase) = db.noteDao()

    @Provides
    @Singleton
    fun provideEntity() = Note()

}