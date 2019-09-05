package com.vidyacharan.urbandictionary.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "definition_table")
data class DefinitionData(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "word") val word: String,
    @ColumnInfo(name = "defId") val defId: Int,
    @ColumnInfo(name = "definition") val definition: String,
    @ColumnInfo(name = "example") val example: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "writtenOn") val writtenOn: String,
    @ColumnInfo(name = "thumbsUp") val thumbsUp: Int,
    @ColumnInfo(name = "thumbsDown") val thumbsDown: Int
)