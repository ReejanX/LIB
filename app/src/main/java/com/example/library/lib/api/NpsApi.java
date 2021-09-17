package com.example.library.lib.api;

import com.example.library.lib.abstractclasses.NpsModel;

import org.jetbrains.annotations.NotNull;

import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NpsApi<T extends NpsModel> {
    private static final String TAG = "NpsApi";
    private OnNpsApiResult<T> mApiResult;
    private Call<T> mCall;

    public void callAPI(Call<T> call, OnNpsApiResult<T> apiResult) {
        this.mApiResult = apiResult;
        this.mCall = call;
        this.callAPI();
    }

    private void callAPI() {
        mCall.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NotNull Call<T> call, @NotNull Response<T> response) {

                if (!response.isSuccessful()) {
                    mApiResult.onError(getDefaultThrowable());
                    return;
                }
                final T t = response.body();
                if (t == null) {
                    mApiResult.onError(getDefaultThrowable());
                    return;
                }
                final NpsError error = t.getError();

                if (error == null) {
                    mApiResult.onSuccess(t);
                    return;
                }

                if (t.getCode().equalsIgnoreCase("1") && error.getErrorCode().equalsIgnoreCase("400")) {
                    mApiResult.onSessionExpired("Session Expired \nPlease login");
                } else {
                    mApiResult.onError(new NpsThrowable(error));
                }
            }

            @Override
            public void onFailure(@NotNull Call<T> call, @NotNull Throwable t) {
                if (t instanceof UnknownHostException) {
                    mApiResult.onError(getDefaultThrowable());
                    return;
                }
                mApiResult.onError(t);
            }
        });
    }

    private NpsThrowable getDefaultThrowable() {
        return NpsThrowable.getDefaultThrowable();
    }
}