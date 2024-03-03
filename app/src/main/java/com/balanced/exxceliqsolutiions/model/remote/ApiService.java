package com.balanced.exxceliqsolutiions.model.remote;

import com.balanced.exxceliqsolutiions.wrapper.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * This is ApiService class used for the creating method for api calling.
 */
public interface ApiService {

    @GET("users")
    Call<UserResponse> getUsers(@Query("page") int page);

}
