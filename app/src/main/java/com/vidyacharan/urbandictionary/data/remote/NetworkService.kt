package com.vidyacharan.urbandictionary.data.remote

import com.vidyacharan.urbandictionary.data.remote.Endpoint.DEFINE
import com.vidyacharan.urbandictionary.data.remote.Endpoint.PARAM_TERM
import com.vidyacharan.urbandictionary.data.remote.Networking.HEADER_API_HOST
import com.vidyacharan.urbandictionary.data.remote.Networking.HEADER_API_KEY
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NetworkService {
    @GET(DEFINE)
    fun getDefinition(
        @Header(HEADER_API_HOST) apiHost: String = Networking.apiHost,
        @Header(HEADER_API_KEY) apiKey: String = Networking.apiKey,
        @Query(PARAM_TERM) term: String
    ): Single<DefinitionCompleteResponse>
}