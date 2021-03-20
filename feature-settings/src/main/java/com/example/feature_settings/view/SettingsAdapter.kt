package com.example.feature_settings.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_settings.R
import com.example.feature_settings.domain.entity.SettingsEntityUi
import com.example.feature_settings.utils.Constants.Companion.VIEW_TYPE_DESCRIPTION
import com.example.feature_settings.utils.Constants.Companion.VIEW_TYPE_LOG_OUT
import com.example.feature_settings.utils.Constants.Companion.VIEW_TYPE_TITLE

class SettingsAdapter(
    private val onItemClickListener: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var content: List<SettingsEntityUi> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            VIEW_TYPE_TITLE -> {
                val view = inflater.inflate(R.layout.item_title, parent, false)
                return TitleViewHolder(view)
            }
            VIEW_TYPE_LOG_OUT -> {
                val view = inflater.inflate(R.layout.item_title, parent, false)
                return TitleViewHolder(view)
            }
            VIEW_TYPE_DESCRIPTION -> {
                val view = inflater.inflate(R.layout.item_description, parent, false)
                return DescriptionViewHolder(view)
            }
            else -> {
                val view = inflater.inflate(R.layout.item_title, parent, false)
                return TitleViewHolder(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int = content[position].viewType

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder.itemViewType) {
            VIEW_TYPE_TITLE -> {
                val titleViewHolder = holder as TitleViewHolder
                titleViewHolder.bind(content[position])
                titleViewHolder.itemView.setOnClickListener { onItemClickListener(position) }
            }
            VIEW_TYPE_LOG_OUT -> {
                val titleViewHolder = holder as TitleViewHolder
                titleViewHolder.bind(content[position])
                titleViewHolder.itemView.setOnClickListener { onItemClickListener(position) }
            }
            VIEW_TYPE_DESCRIPTION -> {
                val descriptionViewHolder = holder as DescriptionViewHolder
                descriptionViewHolder.bind(content[position])
                descriptionViewHolder.itemView.setOnClickListener { onItemClickListener(position) }
            }
            else -> {
                val titleViewHolder = holder as TitleViewHolder
                titleViewHolder.bind(content[position])
                titleViewHolder.itemView.setOnClickListener { onItemClickListener(position) }
            }
        }
    }

    override fun getItemCount(): Int = content.size

    fun setContent(content: List<SettingsEntityUi>) {
        this.content = content
        notifyDataSetChanged()
    }

    class TitleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var titleView: TextView? = null

        init {
            titleView = itemView.findViewById(R.id.item_title)
        }

        fun bind(content: SettingsEntityUi) {
            titleView?.text = content.text
        }
    }

    class DescriptionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var descriptionView: TextView? = null

        init {
            descriptionView = itemView.findViewById(R.id.item_description)
        }

        fun bind(content: SettingsEntityUi) {
            descriptionView?.text = content.text
        }
    }
}