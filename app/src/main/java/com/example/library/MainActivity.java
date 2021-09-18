package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.library.lib.api.OnNpsApiResult;
import com.example.library.lib.auth.UserViewModel;
import com.example.library.lib.model.User;
import com.example.library.lib.utils.ModelProvider;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        userViewModel = ModelProvider.get(this, UserViewModel.class);

        userViewModel.login("9860773808", "Test@123", new OnNpsApiResult<User>() {
            @Override
            public void onSuccess(User data) {
                Log.d(TAG, "onSuccess: "+data);
            }

            @Override
            public void onSessionExpired(String message) {
                Log.d(TAG, "onSessionExpired: "+message);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
}