package com.dobrucali.photos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dobrucali.photos.model.GetRecentPhotoResponse;
import com.dobrucali.photos.retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoRepository {
    PhotoApi photoApi;

    String apiKey = "65f0d2f3fa2af4901eeb60834f4bb334";
    String method = "flickr.photos.getRecent";
    String format = "json";
    Integer perPage = 20;
    Integer page = 1;
    Integer noJsonCallback = 1;

    public PhotoRepository() {
        photoApi = ApiClient.getClient().create(PhotoApi.class);
    }

    public LiveData<GetRecentPhotoResponse> getRecentPhoto() {
        final MutableLiveData<GetRecentPhotoResponse> data = new MutableLiveData<>();
        photoApi.getRecentPhoto(method, apiKey, perPage, page, format, noJsonCallback).enqueue(new Callback<GetRecentPhotoResponse>() {
            @Override
            public void onResponse(Call<GetRecentPhotoResponse> call, Response<GetRecentPhotoResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GetRecentPhotoResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
