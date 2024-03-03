package com.balanced.exxceliqsolutiions.model.remote;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.balanced.exxceliqsolutiions.wrapper.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This is Retrofit initialize class where we initialize the retrofit instance object
 * Also, LiveData along with pagination has been handled.
 */
public class RetrofitConfiguration {

    private final ApiService apiService;

    public RetrofitConfiguration() {
        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create the ApiService
        apiService = retrofit.create(ApiService.class);
    }

    public LiveData<PagedList<UserResponse.User>> getUsers() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(6)
                .setEnablePlaceholders(false)
                .build();

        return new LivePagedListBuilder<>(new PostDataSourceFactory(), config).build();
    }

    private static class PostDataSource extends PageKeyedDataSource<Integer, UserResponse.User> {
        private final ApiService apiServicePDS;

        PostDataSource(ApiService apiService) {
            this.apiServicePDS = apiService;
        }

        @Override
        public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, UserResponse.User> callback) {
            apiServicePDS.getUsers(1).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.isSuccessful()) {
                        callback.onResult(response.body().getData(), null, 2);
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    // Handle failure
                }
            });
        }

        @Override
        public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, UserResponse.User> callback) {
            // Not needed for your case
        }

        @Override
        public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, UserResponse.User> callback) {
            int page = params.key;
            apiServicePDS.getUsers(page).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.isSuccessful()) {
                        int nextPage = page + 1;
                        callback.onResult(response.body().getData(), nextPage);
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    // Handle failure
                }
            });
        }
    }

    private class PostDataSourceFactory extends DataSource.Factory<Integer, UserResponse.User> {
        @Override
        public DataSource<Integer, UserResponse.User> create() {
            return new PostDataSource(apiService);
        }
    }

}
