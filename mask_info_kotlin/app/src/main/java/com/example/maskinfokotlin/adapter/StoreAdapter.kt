package com.example.maskinfokotlin.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.maskinfokotlin.R
import com.example.maskinfokotlin.databinding.ItemStoreInfoBinding
import com.example.maskinfokotlin.model.Store

// 아이템 뷰 정보를 가지고 있는 클래스
class StoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = ItemStoreInfoBinding.bind(itemView)
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
        holder.binding.store = mItems[position]

//        holder.tvName.text = store.name
//        holder.tvAddr.text = store.addr
//        holder.tvDistance.text = String.format("%.2fkm", store.distance)
//        var remainStat: String? = store.remain_stat
//        var count = "알 수 없음"
//        var color = Color.GRAY



//        holder.tvRemain.text = remainStat
//        holder.tvCount.text = count
//        holder.tvRemain.setTextColor(color)
//        holder.tvCount.setTextColor(color)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }
}

@BindingAdapter("remainStat")
fun setRemainStat(textView: TextView, store: Store) {
    when (store.remain_stat) {
        "plenty" -> textView.text = "충분"
        "some" -> textView.text = "여유"
        "few" -> textView.text = "매진 임박"
        else -> textView.text = "재고 없음"
    }
}

@BindingAdapter("count")
fun setCount(textView: TextView, store: Store) {
    when (store.remain_stat) {
        "plenty" -> textView.text = "100개 이상"
        "some" -> textView.text = "30개 이상"
        "few" -> textView.text = "2개 이상"
        else -> textView.text = "1개 이하"
    }
}

@BindingAdapter("color")
fun setColor(textView: TextView, store: Store) {
    when (store.remain_stat) {
        "plenty" -> textView.setTextColor(Color.GREEN)
        "some" -> textView.setTextColor(Color.YELLOW)
        "few" -> textView.setTextColor(Color.RED)
        else -> textView.setTextColor(Color.GRAY)
    }
}
