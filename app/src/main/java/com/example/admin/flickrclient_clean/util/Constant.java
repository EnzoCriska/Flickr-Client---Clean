package com.example.admin.flickrclient_clean.util;

import android.net.Uri;

public class Constant {
    public static String URLget ="https://api.flickr.com/services/rest/";
    public static String METHOD = "flickr.photos.getRecent";
    public static String SEACH_METHOD = "flickr.photos.search";
    public static final int REQUEST_LOCATION_PERMISSION = 100;
    public static final long UPDATE_INTERVAL = 5000;
    public static final long FASTEST_INTERVAL = 5000;

    private static final Uri ENDPOINT = Uri
            .parse("https://api.flickr.com/services/rest/")
            .buildUpon()
            .appendQueryParameter("api_key", "cfcdde9577bce83d20b23d02bcd939a5")
            .appendQueryParameter("format", "json")
            .appendQueryParameter("nojsoncallback", "1")
            .appendQueryParameter("extras", "url_s")
            .build();

    public static String buildUrl(String method, String query, String lat, String lon) {
        Uri.Builder uriBuilder = ENDPOINT.buildUpon()
                .appendQueryParameter("method", method);
        if (method.equals(SEACH_METHOD)) {
            if (query != null){
                uriBuilder.appendQueryParameter("text", query);
            }else{
                uriBuilder.appendQueryParameter("lat", lat);
                uriBuilder.appendQueryParameter("lon", lon);
            }
        }
        return uriBuilder.build().toString();
    }
}
