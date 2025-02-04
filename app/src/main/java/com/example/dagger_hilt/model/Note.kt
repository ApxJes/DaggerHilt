package com.example.dagger_hilt.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dagger_hilt.util.Constants.TABLE_NAME
import kotlinx.parcelize.Parcelize

@Entity(TABLE_NAME)
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    val noteId: Int = 0,
    @ColumnInfo
    val noteTitle: String = "",
    @ColumnInfo
    val noteDescription: String = ""
): Parcelable
