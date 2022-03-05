package com.example.maskinfojava.adapter;

import android.graphics.Color;
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
        holder.tvDistance.setText(String.format("%.2fkm", store.getDistance()));

        String remainStat = store.getRemainStat();
        String count = "알 수 없음";
        int color = Color.GRAY;
        switch (store.getRemainStat()) {
            case "plenty":
                remainStat = "충분";
                count = "100개 이상";
                color = Color.GREEN;
                break;
            case "some":
                remainStat = "여유";
                count = "30개 이상";
                color = Color.YELLOW;
                break;
            case "few":
                remainStat = "매진 임박";
                count = "2개 이상";
                color = Color.RED;
                break;
            case "empty":
                remainStat = "재고 없음";
                count = "1개 이하";
                color = Color.GRAY;
                break;
            default:
        }

        holder.tvRemain.setText(remainStat);
        holder.tvCount.setText(count);

        holder.tvRemain.setTextColor(color);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
