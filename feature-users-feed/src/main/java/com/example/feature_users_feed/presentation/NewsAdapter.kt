package com.example.feature_users_feed.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_users_feed.R
import com.example.feature_users_feed.domain.entity.ContentEntity

class NewsAdapter(private val onItemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var content: List<ContentEntity> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            0 -> {
                val view = inflater.inflate(R.layout.layout_feature_one, parent, false)
                return FeatureOneViewHolder(view)
            }
            1 -> {
                val view = inflater.inflate(R.layout.layout_feature_two, parent, false)
                return FeatureTwoViewHolder(view)
            }
            2 -> {
                val view = inflater.inflate(R.layout.layout_feature_three, parent, false)
                return FeatureThreeViewHolder(view)
            }
            3 -> {
                val view = inflater.inflate(R.layout.layout_feature_four, parent, false)
                return FeatureFourViewHolder(view)
            }
            else -> {
                val view = inflater.inflate(R.layout.layout_feature_one, parent, false)
                return FeatureOneViewHolder(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return content[position].viewType
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            0 -> {
                val featureOneViewHolder = holder as FeatureOneViewHolder
            }
            1 -> {
                val featureTwoViewHolder = holder as FeatureTwoViewHolder
            }
            2 -> {
                val featureThreeViewHolder = holder as FeatureThreeViewHolder
            }
            3 -> {
                val featureFourViewHolder = holder as FeatureFourViewHolder
            }
            else -> {
                val featureOneViewHolder = holder as FeatureOneViewHolder
            }
        }
    }

    override fun getItemCount(): Int = content.size

    fun setListCurrency(newContent: List<ContentEntity>) {
        this.content = newContent
        notifyDataSetChanged()
    }

    class FeatureOneViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var currencyTitleView: TextView? = null

        init {
            currencyTitleView = itemView.findViewById(R.id.textView_label)
        }

        fun bind(currency: ContentEntity) {
            //currencyTitleView?.text = currency
        }
    }

    class FeatureTwoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var currencyTitleView: TextView? = null

        init {
            currencyTitleView = itemView.findViewById(R.id.textView_label)
        }

        fun bind(currency: ContentEntity) {
            //currencyTitleView?.text = currency
        }
    }

    class FeatureThreeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var currencyTitleView: TextView? = null

        init {
            currencyTitleView = itemView.findViewById(R.id.textView_label)
        }

        fun bind(currency: ContentEntity) {
            //currencyTitleView?.text = currency
        }
    }

    class FeatureFourViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var currencyTitleView: TextView? = null

        init {
            currencyTitleView = itemView.findViewById(R.id.textView_label)
        }

        fun bind(currency: ContentEntity) {
            //currencyTitleView?.text = currency
        }
    }
}