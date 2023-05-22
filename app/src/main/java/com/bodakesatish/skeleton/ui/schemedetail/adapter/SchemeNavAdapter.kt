package com.bodakesatish.skeleton.ui.schemedetail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bodakesatish.skeleton.R
import com.bodakesatish.skeleton.domain.model.response.SchemeNavDataResponse
import com.bodakesatish.skeleton.databinding.ItemSchemeNavBinding

class SchemeNavAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var context: Context? = null
    private var mList: List<SchemeNavDataResponse>
    var onSearchMutualFundClick: ((SchemeNavDataResponse) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemSchemeNavBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SchemeNavViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SchemeNavViewHolder -> {
                holder.tvSchemeDate.text = mList[position].date
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

    fun updateList(updatedList: List<SchemeNavDataResponse>) {
        mList = updatedList
        notifyDataSetChanged()
    }

    fun setOnClickListener(item: (SchemeNavDataResponse) -> Unit) {
        onSearchMutualFundClick = item
    }

    init {
        this.mList = emptyList()
    }
}