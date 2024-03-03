package com.balanced.exxceliqsolutiions.view.main;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.balanced.exxceliqsolutiions.wrapper.UserResponse;
import com.balanced.exxceliqsolutiions.model.remote.RetrofitConfiguration;

/**
 * This is ViewModel class where we call the API i.e getUser() for the response.
 */

public class UserViewModel extends ViewModel {
    private final RetrofitConfiguration postRepository;
    private final LiveData<PagedList<UserResponse.User>> usersLiveData;

    public UserViewModel() {
        postRepository = new RetrofitConfiguration();
        usersLiveData = postRepository.getUsers();
    }

    public LiveData<PagedList<UserResponse.User>> getUsers() {
        return usersLiveData;
    }
}