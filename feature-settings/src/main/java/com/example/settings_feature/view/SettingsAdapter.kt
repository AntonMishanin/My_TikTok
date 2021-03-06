package com.example.settings_feature.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.settings_feature.R
import com.example.settings_feature.domain.entity.SettingsEntityUi
import com.example.settings_feature.utils.Constants.Companion.VIEW_TYPE_DESCRIPTION
import com.example.settings_feature.utils.Constants.Companion.VIEW_TYPE_TITLE

class SettingsAdapter(
    private val onItemClickListener: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var content: List<SettingsEntityUi> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            VIEW_TYPE_TITLE -> {
                val inflater =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_title, parent, false)
                return TitleViewHolder(
                    inflater
                )
            }
            VIEW_TYPE_DESCRIPTION -> {
                val inflater =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_description, parent, false)
                return DescriptionViewHolder(
                    inflater
                )
            }
            else -> {
                val inflater =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_title, parent, false)
                return TitleViewHolder(
                    inflater
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return content[position].viewType
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder.itemViewType) {
            VIEW_TYPE_TITLE -> {
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