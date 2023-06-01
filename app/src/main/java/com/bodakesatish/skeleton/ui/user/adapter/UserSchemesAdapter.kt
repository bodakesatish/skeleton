package com.bodakesatish.skeleton.ui.user.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bodakesatish.skeleton.domain.model.response.UserScheme
import com.bodakesatish.skeleton.R
import com.bodakesatish.skeleton.databinding.ItemMutualFundBinding
import com.bodakesatish.skeleton.ui.user.adapter.UserSchemesAdapter.UserSchemesViewHolder

class UserSchemesAdapter() : RecyclerView.Adapter<UserSchemesViewHolder>() {

    private var context: Context? = null
    private var mList: List<UserScheme>
    var onSearchMutualFundClick: ((UserScheme) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSchemesViewHolder {
        val binding = ItemMutualFundBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserSchemesViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: UserSchemesViewHolder, position: Int) {
        holder.tvSchemeCode.text = mList[position].schemeCode.toString()
        holder.tvSchemeName.text = mList[position].schemeName
        holder.view.setOnClickListener {
            onSearchMutualFundClick?.invoke(mList[position])
        }
    }

    override fun getItemCount() = mList.size

    inner class UserSchemesViewHolder(val view: View) :RecyclerView.ViewHolder(view) {
        internal val tvSchemeCode = view.findViewById<TextView>(R.id.tvSchemeCode)
        internal val tvSchemeName = view.findViewById<TextView>(R.id.tvSchemeName)
    }

    fun updateList(updatedList: List<UserScheme>) {
        mList = updatedList
        notifyDataSetChanged()
    }

    fun setOnClickListener(item: (UserScheme) -> Unit) {
        onSearchMutualFundClick = item
    }

    init {
        this.mList = emptyList()
    }
}