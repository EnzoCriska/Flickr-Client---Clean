package com.example.admin.flickrclient_clean.presentation.UI.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.example.admin.flickrclient_clean.R;
import com.example.admin.flickrclient_clean.domain.model.Photo;
import com.example.admin.flickrclient_clean.presentation.UI.adapter.AdapterRecycle;
import com.example.admin.flickrclient_clean.presentation.presenter.MainPresenter;
import com.example.admin.flickrclient_clean.util.Constant;
import com.example.admin.flickrclient_clean.util.Injection;
import com.example.admin.flickrclient_clean.util.helper.PreferencesManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainPresenter.View, AdapterRecycle.OnClickPhoto {
    private ArrayList<Photo> list, oldList;
    private RecyclerView recyclerView;
    private Switch aSwitch;
    private AdapterRecycle adapter;
    private MainPresenter mMainPresenter;
    private ProgressDialog progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (PreferencesManager.loadPre(this, "isNotify")) {
            aSwitch.setChecked(true);
        } else {
            aSwitch.setChecked(false);
        }
        mMainPresenter.getPhotoFromApi(Constant.buildUrl(Constant.METHOD, null, null, null));
    }

    @Override
    protected void onResume() {
        if (!oldList.isEmpty()){
            list.addAll(oldList);
            oldList.clear();
            adapter.notifyDataSetChanged();
        }
        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPresenter.clickNotify(MainActivity.this);
            }
        });
        super.onResume();
    }

    @Override
    protected void onStop() {
        PreferencesManager.savePre(this, "isNotify", aSwitch.isChecked());
        Log.i("Main Activity", "Save status...");
        super.onStop();
    }

    private void init(){
        list = new ArrayList<>();
        oldList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler);
        aSwitch = findViewById(R.id.runbackground);
        adapter = new AdapterRecycle(this, this);
        progressBar = new ProgressDialog(this);
        mMainPresenter = Injection.getInstance().getMainPresenter(this);
        recyclerUp();
    }

    public void recyclerUp(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        adapter.setiData(new AdapterRecycle.IData() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Photo getItem(int pos) {
                return list.get(pos);
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_bar, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.searchMenu).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(MainActivity.this, "SEARCHING... " + s, Toast.LENGTH_SHORT).show();
                mMainPresenter.getPhotoFromApi(Constant.buildUrl(Constant.SEACH_METHOD, s, null, null));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        MenuItem view = menu.findItem(R.id.renew);
        view.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                mMainPresenter.getPhotoFromApi(Constant.buildUrl(Constant.METHOD, null, null, null));
                adapter.notifyDataSetChanged();
                return true;
            }
        });

        MenuItem locationfind = menu.findItem(R.id.findLocation);
        locationfind.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
               mMainPresenter.getLocation(MainActivity.this);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClickItemPhoto(int pos) {
        oldList.addAll(list);
        String url = list.get(pos).getUrlS();
        Log.i("MainActivity", "view full");
        Intent intent = new Intent(this, FullActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("URL", url);
        intent.putExtra("Bundle", bundle);
        startActivity(intent);
    }

    @Override
    public void showData(ArrayList<Photo> photos) {
        if (list.isEmpty()){
            list.addAll(photos);
            adapter.notifyDataSetChanged();
        }else{
            equalsData(list, photos);
            ArrayList tpm = new ArrayList();
            tpm.addAll(list);
            list.clear();
            list.addAll(photos);
            list.addAll(tpm);
            adapter.notifyDataSetChanged();
        }
    }

    private void equalsData(ArrayList oldList, ArrayList<Photo> dataList) {
        for (int i = 0; i < oldList.size(); i++) {
            int j = 0;
            while (dataList.size() > 0 && j < dataList.size()) {
                if (oldList.get(i).equals(dataList.get(j))) {
                    dataList.remove(j);
                }
                j++;
            }
        }
    }

    @Override
    public void receiveNotify(String message) {
        aSwitch.setChecked(true);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cancleNotify(String message) {
        aSwitch.setChecked(false);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLocation(String lat, String lon) {
        mMainPresenter.getPhotoFromApi(Constant.buildUrl(Constant.SEACH_METHOD, null, lat, lon));
    }

    @Override
    public void cantGetLocation() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }

    @Override
    public void showProgress() {
       progressBar.show();
    }

    @Override
    public void hideProgress() {
        progressBar.dismiss();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, "Error: "+ message, Toast.LENGTH_SHORT).show();
    }
}
