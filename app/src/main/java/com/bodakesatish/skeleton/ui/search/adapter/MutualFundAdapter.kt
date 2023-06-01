package com.bodakesatish.skeleton.ui.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bodakesatish.skeleton.domain.model.response.SearchMutualFund
import com.bodakesatish.skeleton.R
import com.bodakesatish.skeleton.databinding.ItemMutualFundBinding

class MutualFundAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var context: Context? = null
    private var mList: List<SearchMutualFund>
    var onSearchMutualFundClick: ((SearchMutualFund) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemMutualFundBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MutualFundViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MutualFundViewHolder -> {
                holder.tvSchemeCode.text = mList[position].schemeCode
                holder.tvSchemeName.text = mList[position].schemeName
                holder.view.setOnClickListener {
                    onSearchMutualFundClick?.invoke(mList[position])
                }
            }
        }
    }

    override fun getItemCount() = mList.size

    inner class MutualFundViewHolder(val view: View) :RecyclerView.ViewHolder(view) {
        internal val tvSchemeCode = view.findViewById<TextView>(R.id.tvSchemeCode)
        internal val tvSchemeName = view.findViewById<TextView>(R.id.tvSchemeName)
    }

    fun updateList(updatedList: List<SearchMutualFund>) {
        mList = updatedList
        notifyDataSetChanged()
    }

    fun setOnClickListener(item: (SearchMutualFund) -> Unit) {
        onSearchMutualFundClick = item
    }

    init {
        this.mList = emptyList()
    }
}