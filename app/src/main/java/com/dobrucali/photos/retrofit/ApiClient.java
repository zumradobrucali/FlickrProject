package com.dobrucali.photos.retrofit;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;
    private static String Base_Url = "https://www.flickr.com/services/rest/";

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Base_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit;
        }
        return retrofit;
    }
}
