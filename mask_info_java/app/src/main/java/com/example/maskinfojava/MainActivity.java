package com.example.maskinfojava;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maskinfojava.adapter.StoreAdapter;
import com.example.maskinfojava.viewmodel.MainViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private MainViewModel mainViewModel;

    private FusedLocationProviderClient fusedLocationClient;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.actionRefresh:
                mainViewModel.fetchStoreInfo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                performAction();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]") // 사용자가 거부했을 때의 메시지
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .check();
    }

    @SuppressLint("MissingPermission")
    private void performAction() {
        fusedLocationClient.getLastLocation()
                .addOnFailureListener(this, e -> {
                    Log.e(TAG, "performAction: ", e.getCause());
                })
                .addOnSuccessListener(this, location -> {
                    // Got last known location. In some rare situations this can be null.
                    if (location != null) {
                        Log.d(TAG, "performAction: " + location.getLatitude() + " | " + location.getLongitude());

                        // FIXME: EXAMPLE
//                        location.setLatitude(37.188078);
//                        location.setLongitude(127.043002);

                        mainViewModel.location = location;
                        mainViewModel.fetchStoreInfo();
                    }
                });

        RecyclerView mainRecyclerView = findViewById(R.id.mainRecyclerView);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        StoreAdapter storeAdapter = new StoreAdapter();
        mainRecyclerView.setAdapter(storeAdapter);

        mainViewModel.itemLiveData.observe(this, stores -> {
            storeAdapter.updateItems(stores);
            getSupportActionBar().setTitle("마스크 재고 있는 곳: " + stores.size());
        });

        mainViewModel.loadingLiveData.observe(this, isLoading -> {
            // 로딩 상태에 따른 프로그래스바 표시
            if (isLoading) {
                findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
            } else {
                findViewById(R.id.progressBar).setVisibility(View.GONE);
            }
        });
    }
}