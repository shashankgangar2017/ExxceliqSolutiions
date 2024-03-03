package com.balanced.exxceliqsolutiions.view.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.balanced.exxceliqsolutiions.R;
import com.balanced.exxceliqsolutiions.model.remote.ApiService;
import com.balanced.exxceliqsolutiions.view.adapter.UserAdapter;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * In this activity, we initialize adapter and the viewModel, and observe data.
 */
public class MainActivity extends AppCompatActivity {
    private UserViewModel userViewModel;

    public static ApiService apiService;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        UserAdapter postAdapter = new UserAdapter();
        recyclerView.setAdapter(postAdapter);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getUsers().observe(this, users -> postAdapter.submitList(users));
    }
}
