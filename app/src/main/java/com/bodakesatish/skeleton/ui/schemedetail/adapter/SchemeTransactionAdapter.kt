package com.bodakesatish.skeleton.ui.schemedetail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bodakesatish.skeleton.domain.model.response.UserSchemeTransaction
import com.bodakesatish.skeleton.R
import com.bodakesatish.skeleton.databinding.ItemSchemeNavBinding

class SchemeTransactionAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var context: Context? = null
    private var mList: List<UserSchemeTransaction>
    var onSearchMutualFundClick: ((UserSchemeTransaction) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchemeNavViewHolder {
        val binding = ItemSchemeNavBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SchemeNavViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SchemeNavViewHolder -> {
                holder.tvSchemeDate.text = mList[position].nav
                holder.tvSchemeNav.text = mList[position].nav
                holder.view.setOnClickListener {
                    onSearchMutualFundClick?.invoke(mList[position])
                }
            }
        }
    }

    override fun getItemCount() = mList.size

    inner class SchemeNavViewHolder(val view: View) :RecyclerView.ViewHolder(view) {
        internal val tvSchemeDate = view.findViewById<TextView>(R.id.tvSchemeDate)
        internal val tvSchemeNav = view.findViewById<TextView>(R.id.tvSchemeNav)
    }

    fun updateList(updatedList: List<UserSchemeTransaction>) {
        mList = updatedList
        notifyDataSetChanged()
    }

    fun setOnClickListener(item: (UserSchemeTransaction) -> Unit) {
        onSearchMutualFundClick = item
    }

    init {
        this.mList = emptyList()
    }
}