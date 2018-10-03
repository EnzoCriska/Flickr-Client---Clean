package com.example.admin.flickrclient_clean.domain.repository;

import com.example.admin.flickrclient_clean.domain.model.Photo;

import java.util.ArrayList;

public interface DataRepository {
    ArrayList<Photo> getData();
}
