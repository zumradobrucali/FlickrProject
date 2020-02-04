package com.dobrucali.photos;

import com.dobrucali.photos.model.GetRecentPhotoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PhotoApi {
    @GET(".")
    Call<GetRecentPhotoResponse> getRecentPhoto(
            @Query("method") String method,
            @Query("api_key") String apiKey,
            @Query("per_page") Integer perPage,
            @Query("page") Integer page,
            @Query("format") String format,
            @Query("nojsoncallback") Integer noJsonCallback
    );
}
