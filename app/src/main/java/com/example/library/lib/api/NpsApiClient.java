package com.example.library.lib.api;


import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.example.library.lib.session.AuthSession;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NpsApiClient {

    private static final String TAG = "ApiClient";

    public static Retrofit retrofit = null;

    public static <S> S getApiClient(Context context, Class<S> serviceClass) {
        return getRetrofit(context).create(serviceClass);
    }

    private static String getHeader(Context context, NpsInfo info) {
        String token = new AuthSession(context).getToken();
        if (token == null) {
            String auth = info.getUsername() + ":" + info.getPassword();
            return "Basic " + Base64.encodeToString(auth.getBytes(StandardCharsets.UTF_8), Base64.NO_WRAP);
        }
        return "Bearer " + token;
    }

    private static Retrofit getRetrofit(final Context context) {
        final NpsInfo info = new NpsInfo(context);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(60, TimeUnit.SECONDS);
        httpClient.connectTimeout(60, TimeUnit.SECONDS);
        httpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        httpClient.addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Authorization", getHeader(context, info))
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });
        Log.i(TAG, "getRetrofit: "+getHeader(context, info));

        Gson gson = new GsonBuilder().setLenient().create();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(info.getUrl())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }

}