package com.dobrucali.photos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.dobrucali.photos.model.GetRecentPhotoResponse;

public class MainViewModel extends ViewModel {
    private PhotoRepository photoRepository;

    public MainViewModel() {
        photoRepository = new PhotoRepository();
    }

    public LiveData<GetRecentPhotoResponse> getRecentPhoto() {
        return photoRepository.getRecentPhoto();
    }
}
