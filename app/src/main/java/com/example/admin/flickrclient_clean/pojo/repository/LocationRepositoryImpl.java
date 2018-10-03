package com.example.admin.flickrclient_clean.pojo.repository;

import com.example.admin.flickrclient_clean.domain.repository.LocationRepository;
import com.example.admin.flickrclient_clean.pojo.Location.GPSTracker;

public class LocationRepositoryImpl implements LocationRepository {
    private GPSTracker gpsTracker;
    public LocationRepositoryImpl(GPSTracker gpsTracker) {
        this.gpsTracker = gpsTracker;
    }

    public static LocationRepositoryImpl getInstance(GPSTracker gpsTracker){
        return new LocationRepositoryImpl(gpsTracker);
    }

    @Override
    public String getLat() {
        return gpsTracker.getLat();
    }

    @Override
    public String getLon() {
        return gpsTracker.getLon();
    }
}
