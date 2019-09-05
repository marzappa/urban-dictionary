package com.vidyacharan.urbandictionary.data.remote


import com.google.gson.annotations.SerializedName

data class DefinitionCompleteResponse(
    @SerializedName("list")
    val list: List<DefinitionResponse>? = listOf()
) {
    data class DefinitionResponse(
        @SerializedName("definition")
        val definition: String? = "",
        @SerializedName("permalink")
        val permalink: String? = "",
        @SerializedName("thumbs_up")
        val thumbsUp: Int? = 0,
        @SerializedName("sound_urls")
        val soundUrls: List<Any?>? = listOf(),
        @SerializedName("author")
        val author: String? = "",
        @SerializedName("word")
        val word: String? = "",
        @SerializedName("defid")
        val defid: Int? = 0,
        @SerializedName("current_vote")
        val currentVote: String? = "",
        @SerializedName("written_on")
        val writtenOn: String? = "",
        @SerializedName("example")
        val example: String? = "",
        @SerializedName("thumbs_down")
        val thumbsDown: Int? = 0
    )
}