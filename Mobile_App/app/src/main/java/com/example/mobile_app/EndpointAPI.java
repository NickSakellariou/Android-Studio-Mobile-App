package com.example.mobile_app;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface EndpointAPI {

    @Headers({"Authorization: Token 4dd183180c93e02530e1c75341277f83fb307e7c"})
    @GET("mdg_emvolio")
    Call<List<Post>> getPosts(@Query("date_from") String date_from, @Query("date_to") String date_to);
}
