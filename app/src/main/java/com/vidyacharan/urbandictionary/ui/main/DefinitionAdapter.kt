package com.vidyacharan.urbandictionary.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vidyacharan.urbandictionary.R
import com.vidyacharan.urbandictionary.data.model.DefinitionData
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class DefinitionAdapter : RecyclerView.Adapter<DefinitionAdapter.DefinitionVH>() {
    private val definitionList = arrayListOf<DefinitionData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_define, parent, false)
        return DefinitionVH(view)
    }

    override fun getItemCount(): Int = definitionList.size

    override fun onBindViewHolder(holder: DefinitionVH, position: Int) {
        holder.bind(definitionList[position])
    }

    internal fun setData(data: List<DefinitionData>) {
        definitionList.clear()
        definitionList.addAll(data)
        notifyDataSetChanged()
    }


    inner class DefinitionVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val word: TextView = itemView.findViewById(R.id.word)
        private val definition: TextView = itemView.findViewById(R.id.definition)
        private val example: TextView = itemView.findViewById(R.id.example)
        private val author: TextView = itemView.findViewById(R.id.author)
        private val addedDate: TextView = itemView.findViewById(R.id.addedDate)
        private val thumpsUp: TextView = itemView.findViewById(R.id.upVotes)
        private val thumbsDown: TextView = itemView.findViewById(R.id.downVotes)

        fun bind(data: DefinitionData) {
            word.text = data.word
            definition.text = data.definition
            example.text = data.example
            author.text = data.author
            addedDate.text = convertDate(data.writtenOn)
            thumbsDown.text = data.thumbsDown.toString()
            thumpsUp.text = data.thumbsUp.toString()

        }
    }

    companion object {

        internal fun convertDate(date: String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            val outputFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())

            return try {
                inputFormat.parse(date)?.let { outputFormat.format(it) } ?: ""
            } catch (ex: Exception) {
                ""
            }
        }
    }
}