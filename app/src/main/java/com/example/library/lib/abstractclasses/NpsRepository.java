package com.example.library.lib.abstractclasses;

import android.content.Context;
import android.util.Base64;

import com.example.library.lib.api.NpsApi;
import com.example.library.lib.api.NpsApiClient;
import com.example.library.lib.api.NpsApiInterface;
import com.example.library.lib.api.NpsError;
import com.example.library.lib.api.NpsThrowable;
import com.example.library.lib.api.OnNpsApiResult;
import com.example.library.lib.utils.NpsData;
import com.example.library.lib.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public abstract class NpsRepository {

    protected final NpsApiInterface apiInterface;
    private final Context context;
    private static final String TAG = "NpsRepository";

    protected NpsRepository(Context context) {
        this.context = context;
        apiInterface = NpsApiClient.getApiClient(context, NpsApiInterface.class);
    }

    protected Context getContext() {
        return context;
    }

    protected RequestBody createRequestBody(String d) {
        return RequestBody.create(MediaType.parse("text/plain"), d);
    }

    protected RequestBody generateImageData(String payload) {
        return createRequestBody(convertToBase64(payload));
    }

//    protected RequestBody generateImageSignature(String payload) {
//        return createRequestBody(generateSignature(payload));
//    }
//
//    protected NpsData generatePayload(String functionName, String jsonPayload) {
//        return new NpsData(functionName, convertToBase64(jsonPayload), generateSignature(jsonPayload));
//    }

    private String filter(String mData) {
        return mData.replace("\n", "").replace(" ", "");
    }
//
//    private String generateSignature(String jsonPayload) {
//        String key = new AuthSession(context).getKeyEncryption();
//        if (key == null) {
//            key = new NpsInfo(context).getPrivateKey();
//        }
//        return filter(JavaAes.doEncryptedAES(jsonPayload, key));
//    }


    private String convertToBase64(String str) {
        return filter(Base64.encodeToString(str.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT));
    }

    protected <T extends NpsModel> void callApi(Call<T> call, OnNpsApiResult<T> apiResult) {
        if (!Utils.isNetworkConnected(context)) {
            apiResult.onError(new NpsThrowable(new NpsError("-1", "No internet connection!")));
            return;
        }
        final NpsApi<T> api = new NpsApi<>();
        api.callAPI(call, apiResult);
    }

//    protected JSONObject getRequestObject(String username) {
//        JSONObject reader = new JSONObject();
//        try {
//            if (username == null) {
//                username = new BioSession(getContext()).getUsername();
//            }
//            reader.put("user_login_id", username);
//            reader.put("created_platform", "android");
//        } catch (JSONException ignored) {
//
//        }
//        return reader;
//    }
//
//    protected JSONObject getRequestObject() {
//        return getRequestObject(null);
//    }
}