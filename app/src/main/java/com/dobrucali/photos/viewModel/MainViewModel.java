package com.dobrucali.photos.viewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.dobrucali.photos.PhotoRepository;
import com.dobrucali.photos.R;
import com.dobrucali.photos.model.Photo;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private PhotoRepository photoRepository;
    private MutableLiveData<Integer> page = new MutableLiveData<>();
    public MutableLiveData<List<Photo>> photoList = new MutableLiveData<>();

    public MainViewModel() {
        photoRepository = new PhotoRepository();
        page.setValue(0);
        photoList.setValue(new ArrayList<>());
    }

    public void getRecentPhoto(LifecycleOwner lifecycleOwner, Context context) {
       page.setValue(page.getValue() + 1);
       photoRepository.getRecentPhoto(page.getValue()).observe(lifecycleOwner, getRecentPhotoResponse -> {
           if (getRecentPhotoResponse != null) {
               List<Photo> photoList = getRecentPhotoResponse.getPhotos().getPhoto();
               List<Photo> newPhotoList = this.photoList.getValue();
               if (newPhotoList != null && photoList != null) {
                   newPhotoList.addAll(photoList);
               }
               this.photoList.setValue(newPhotoList);
           } else {
               Toast.makeText(context, context.getString(R.string.request_failed),Toast.LENGTH_LONG).show();
           }
       });

    }
}
