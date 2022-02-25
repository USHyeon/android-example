package com.example.maskinfojava;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maskinfojava.adapter.StoreAdapter;
import com.example.maskinfojava.model.Store;
import com.example.maskinfojava.model.StoreInfo;
import com.example.maskinfojava.repository.MaskService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mainRecyclerView = findViewById(R.id.mainRecyclerView);

        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        StoreAdapter storeAdapter = new StoreAdapter();
        mainRecyclerView.setAdapter(storeAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MaskService.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        MaskService service = retrofit.create(MaskService.class);
        Call<StoreInfo> storeInfoCall = service.fetchStoreInfo();
        storeInfoCall.enqueue(new Callback<StoreInfo>() {
            @Override
            public void onResponse(Call<StoreInfo> call, Response<StoreInfo> response) {
                List<Store> items = response.body().getStores();
                storeAdapter.updateItems(items);
            }

            @Override
            public void onFailure(Call<StoreInfo> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}