package com.bodakesatish.skeleton.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bodakesatish.skeleton.domain.model.response.SearchMutualFund
import com.bodakesatish.skeleton.databinding.ItemMutualFundBinding

class MutualFundAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mList = mutableListOf<SearchMutualFund>()
    var onSearchMutualFundClick: ((SearchMutualFund) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemMutualFundBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MutualFundViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MutualFundViewHolder -> {
                holder.view.tvSchemeCode.text = mList[position].schemeCode
                holder.view.tvSchemeName.text = mList[position].schemeName
                holder.view.cvItemMutualFund.setOnClickListener {
                    onSearchMutualFundClick?.invoke(mList[position])
                }
            }
        }
    }

    override fun getItemCount() = mList.size

    inner class MutualFundViewHolder(val view: ItemMutualFundBinding) :RecyclerView.ViewHolder(view.root)

    fun updateList(updatedList: List<SearchMutualFund>) {
        if(mList.isEmpty()) {
            mList.addAll(updatedList)
            notifyDataSetChanged()
        } else {
            mList.clear()
            mList.addAll(updatedList)
            notifyItemRangeChanged(0, updatedList.size)
        }
    }

    fun setOnClickListener(item: (SearchMutualFund) -> Unit) {
        onSearchMutualFundClick = item
    }

}