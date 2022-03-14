package com.example.maskinfokotlin.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.maskinfokotlin.R
import com.example.maskinfokotlin.model.Store

// 아이템 뷰 정보를 가지고 있는 클래스
class StoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvName: TextView = itemView.findViewById(R.id.tvName)
    var tvAddr: TextView = itemView.findViewById(R.id.tvAddr)
    var tvDistance: TextView = itemView.findViewById(R.id.tvDistance)
    var tvRemain: TextView = itemView.findViewById(R.id.tvRemain)
    var tvCount: TextView = itemView.findViewById(R.id.tvCount)
}

class StoreAdapter : RecyclerView.Adapter<StoreViewHolder>() {
    var mItems: List<Store> = ArrayList<Store>()

    fun updateItems(items: List<Store>) {
        mItems = items
        notifyDataSetChanged() // UI 갱신
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_store_info, parent, false)
        return StoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val store: Store = mItems[position]
        holder.tvName.text = store.name
        holder.tvAddr.text = store.addr
        holder.tvDistance.text = "0.0km"
        var remainStat: String = store.remainStat
        var count = "알 수 없음"
        var color = Color.GRAY
        when (store.remainStat) {
            "plenty" -> {
                remainStat = "충분"
                count = "100개 이상"
                color = Color.GREEN
            }
            "some" -> {
                remainStat = "여유"
                count = "30개 이상"
                color = Color.YELLOW
            }
            "few" -> {
                remainStat = "매진 임박"
                count = "2개 이상"
                color = Color.RED
            }
            "empty" -> {
                remainStat = "재고 없음"
                count = "1개 이하"
                color = Color.GRAY
            }
            else -> {}
        }
        holder.tvRemain.text = remainStat
        holder.tvCount.text = count
        holder.tvRemain.setTextColor(color)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }
}
