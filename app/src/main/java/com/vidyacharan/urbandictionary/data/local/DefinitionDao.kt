package com.vidyacharan.urbandictionary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vidyacharan.urbandictionary.data.model.DefinitionData
import io.reactivex.Single

@Dao
interface DefinitionDao {

    @Insert
    fun insert(entity: DefinitionData): Single<Long>

    @Insert
    fun insertMany(vararg entities: DefinitionData): Single<List<Long>>

    @Query("SELECT * from definition_table")
    fun getAllDefinitions(): Single<List<DefinitionData>>

    @Query("SELECT * from definition_table WHERE id = :id LIMIT 1")
    fun getDefinitionById(id: Long): Single<DefinitionData>

    @Query("SELECT * from definition_table WHERE defId = :defId LIMIT 1")
    fun getDefinitionByDefId(defId: Int): Single<DefinitionData>

    @Query("SELECT count(*) from definition_table")
    fun count(): Single<Int>
}