package com.example.admin.flickrclient_clean.pojo.repository;


import android.util.Log;

import com.example.admin.flickrclient_clean.domain.model.Photo;
import com.example.admin.flickrclient_clean.domain.repository.DataRepository;
import com.example.admin.flickrclient_clean.util.StringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

public class DataRepositoryImpl implements DataRepository {
    private String mUrl;
    private ArrayList photos;
    public static DataRepositoryImpl getInstance(String url) {
        return new DataRepositoryImpl(url);
    }

    public DataRepositoryImpl(String url) {
        mUrl = url;
    }
    @Override
    public ArrayList<Photo> getData() {
        try {
            URL url = new URL(mUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            connection.setSSLSocketFactory(socketFactory);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            int resCode = connection.getResponseCode();
            if (resCode == 200) {
                String response = "";
                InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                response = StringUtils.convertStreamToString(inputStream);

                photos = new ArrayList();
                JSONObject object = new JSONObject(response);
                JSONObject objectPhotos = object.getJSONObject("photos");
                JSONArray jsonArray = objectPhotos.getJSONArray("photo");
                for (int i = 0; i < jsonArray.length(); i++) {
                    Photo photo = new Photo(jsonArray.getJSONObject(i));
                    photos.add(photo);
                }
            } else {

        }

    } catch (Exception e) {
        e.printStackTrace();
    }
        return photos;
    }

}
