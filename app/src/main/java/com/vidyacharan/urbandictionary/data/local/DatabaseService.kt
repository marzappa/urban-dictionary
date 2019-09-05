package com.vidyacharan.urbandictionary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vidyacharan.urbandictionary.data.model.DefinitionData

@Database(entities = [DefinitionData::class], version = 1)
abstract class DatabaseService : RoomDatabase() {

    abstract fun definitionDao(): DefinitionDao
}