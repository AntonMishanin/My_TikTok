package com.example.bottom_navigation_feature.presentation.news_feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bottom_navigation_feature.R

class NewsAdapter (private val onItemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var listValute: List<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_detail, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listValute[position])

        holder.itemView.setOnClickListener {
            onItemClickListener(position)
        }
    }

    override fun getItemCount(): Int = listValute.size

    fun setListCurrency(listValute: List<String>) {
        this.listValute = listValute
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var currencyTitleView: TextView? = null

        init {
            currencyTitleView = itemView.findViewById(R.id.textView_label)
        }

        fun bind(currency: String) {
            currencyTitleView?.text = currency
        }
    }
}