package com.example.maskinfojava.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maskinfojava.R;
import com.example.maskinfojava.model.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {
    public List<Store> mItems = new ArrayList<>();

    // 아이템 뷰 정보를 가지고 있는 클래스
    static class StoreViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvAddr;
        TextView tvDistance;
        TextView tvRemain;
        TextView tvCount;

        public StoreViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvAddr = itemView.findViewById(R.id.tvAddr);
            tvDistance = itemView.findViewById(R.id.tvDistance);
            tvRemain = itemView.findViewById(R.id.tvRemain);
            tvCount = itemView.findViewById(R.id.tvCount);
        }
    }

    public void updateItems(List<Store> items) {
        mItems = items;
        notifyDataSetChanged(); // UI 갱신
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store_info, parent, false);
        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        Store store = mItems.get(position);

        holder.tvName.setText(store.getName());
        holder.tvAddr.setText(store.getAddr());
        holder.tvDistance.setText("0.0km");
        holder.tvRemain.setText(store.getRemainStat());
        holder.tvCount.setText("100개 이상");
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
