package com.example.maskinfojava;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maskinfojava.adapter.StoreAdapter;
import com.example.maskinfojava.model.Store;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mainRecyclerView = findViewById(R.id.mainRecyclerView);

        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        StoreAdapter storeAdapter = new StoreAdapter();
        mainRecyclerView.setAdapter(storeAdapter);

        List<Store> items = new ArrayList<>();

        Store store01 = new Store();
        store01.setName("우리 약국");
        store01.setAddr("서울시 용산구 한남동");

        items.add(store01);
        items.add(store01);
        items.add(store01);
        items.add(store01);
        items.add(store01);
        items.add(store01);

        storeAdapter.updateItems(items);

    }
}