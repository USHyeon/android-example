package com.umssung.skeletonlibraryexample

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.SimpleRcvViewHolder>() {

    inner class SimpleRcvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val views = SparseArray<View>()

        fun getView(resId: Int): View? {
            var v = views[resId]
            if (null == v) {
                v = itemView.findViewById(resId)
                views.put(resId, v)
            }
            return v
        }
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: SimpleRcvViewHolder, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleRcvViewHolder {
        return SimpleRcvViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false));
    }


}