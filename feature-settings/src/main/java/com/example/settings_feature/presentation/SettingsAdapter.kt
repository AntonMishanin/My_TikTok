package com.example.settings_feature.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.settings_feature.R

class SettingsAdapter(
    private val onItemClickListener: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var content: List<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            0 -> {
                val inflater =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_title, parent, false)
                return ViewHolder0(inflater)
            }
            1 -> {
                val inflater =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_description, parent, false)
                return ViewHolder1(inflater)
            }
            else -> {
                val inflater =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_title, parent, false)
                return ViewHolder0(inflater)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position % 2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder.itemViewType) {
            0 -> {
                val holder0 = holder as ViewHolder0
                holder0.bind(content[position])
                holder0.itemView.setOnClickListener { onItemClickListener(position) }
            }
            1 -> {
                val holder1 = holder as ViewHolder1
                holder1.bind(content[position])
                holder1.itemView.setOnClickListener { onItemClickListener(position) }
            }
            else -> {
                val holder0 = holder as ViewHolder0
                holder0.bind(content[position])
                holder0.itemView.setOnClickListener { onItemClickListener(position) }
            }
        }
    }

    override fun getItemCount(): Int = content.size

    fun setContent(content: List<String>) {
        this.content = content
        notifyDataSetChanged()
    }

    class ViewHolder0(view: View) : RecyclerView.ViewHolder(view) {

        private var titleView: TextView? = null

        init {
            titleView = itemView.findViewById(R.id.item_title)
        }

        fun bind(content: String) {
            titleView?.text = content
        }
    }

    class ViewHolder1(view: View) : RecyclerView.ViewHolder(view) {

        private var descriptionView: TextView? = null

        init {
            descriptionView = itemView.findViewById(R.id.item_description)
        }

        fun bind(content: String) {
            descriptionView?.text = content
        }
    }
}