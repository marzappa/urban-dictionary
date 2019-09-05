package com.vidyacharan.urbandictionary.data.repository

import com.vidyacharan.urbandictionary.data.model.DefinitionData
import com.vidyacharan.urbandictionary.data.remote.NetworkService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefinitionRepository @Inject constructor(
    private val networkService: NetworkService
) {

    fun getDefinitions(term: String): Single<List<DefinitionData>> {
        return networkService.getDefinition(term = term).map { completeResponse ->
            completeResponse.list?.map {
                DefinitionData(
                    word = it.word ?: "",
                    defId = it.defid ?: 0,
                    definition = it.definition ?: "",
                    example = it.example ?: "",
                    author = it.author ?: "",
                    writtenOn = it.writtenOn ?: "",
                    thumbsUp = it.thumbsUp ?: 0,
                    thumbsDown = it.thumbsDown ?: 0
                )
            } ?: emptyList()
        }
    }

}