package com.example.library.lib.auth;

import android.content.Context;

import com.example.library.lib.abstractclasses.NpsRepository;
import com.example.library.lib.api.OnNpsApiResult;
import com.example.library.lib.model.User;
import com.example.library.lib.utils.NpsData;
import com.example.library.lib.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;



class UserRepo extends NpsRepository {

    protected UserRepo(Context context) {
        super(context);
    }

    protected void login(String username, String password, OnNpsApiResult<User> apiResult) {
        callApi(apiInterface.login(generatePayload("SignIn", createLoginJsonPayload(username, password))), apiResult);
    }
//
//    protected void reset(String username, OnNpsApiResult<Reset> onNpsApiResult) {
//        final NpsData data = generatePayload("FPSendCode", createResetJsonPayload(username));
//        callApi(apiInterface.resetPassword(data), onNpsApiResult);
//    }
//
//    protected void verify(String username, String code, OnNpsApiResult<Reset> onNpsApiResult) {
//        final NpsData data = generatePayload("FPCheckVCode", createVerifyJsonPayload(username, code));
//        callApi(apiInterface.verifyResetCode(data), onNpsApiResult);
//    }
//
//    protected void logout(OnNpsApiResult<Logout> onNpsApiResult) {
//        final NpsData data = generatePayload("LogOut", createLogoutJsonPayload());
//        callApi(apiInterface.logout(data), onNpsApiResult);
//    }
//
//    protected void setPassword(String username, String newPassword, String confirmPassword, OnNpsApiResult<SetPassword> onNpsApiResult) {
//        final NpsData data = generatePayload("SetPassword", createSetPasswordJsonPayload(username, newPassword, confirmPassword));
//        callApi(apiInterface.setVerifyPassword(data), onNpsApiResult);
//    }
//
//    protected void resendCode(String username, OnNpsApiResult<Resend> onNpsApiResult) {
//        final NpsData data = generatePayload("GeneralResendVCode", createResendCodeJsonPayload(username));
//        callApi(apiInterface.resendVerification(data), onNpsApiResult);
//    }
//
//    protected void loginChangePassword(String username, String oldPassword, String newPassword, String confirmPassword, OnNpsApiResult<ChangePassword> apiResult) {
//        final NpsData data = generatePayload("ForcePassword", createLoginChangePassword(username, oldPassword, newPassword, confirmPassword));
//        callApi(apiInterface.changeLoginPassword(data), apiResult);
//    }
//
//    protected void loginChangePin(String username, String oldPin, String newPin, String confirmPin, OnNpsApiResult<ChangePin> apiResult) {
//        final NpsData data = generatePayload("ForceMPIN", createLoginChangePin(username, oldPin, newPin, confirmPin));
//        callApi(apiInterface.changeLoginPin(data), apiResult);
//    }
//
//    private String createLogoutJsonPayload() {
//        JSONObject payload = getRequestObject();
//        try {
//            payload.put("device_id", Utils.id(getContext()));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return payload.toString();
//    }
//
    private String createLoginJsonPayload(String username, String password) {
        JSONObject payload = getRequestObject(username);
        String id = Utils.id(getContext());
        try {
            payload.put("password", password);
            payload.put("device_id", id);
            payload.put("ip_address", "192.168.1.1");
            payload.put("version_id", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return payload.toString();
    }
//
//    private String createResetJsonPayload(String username) {
//        return getRequestObject(username).toString();
//    }
//
//    private String createVerifyJsonPayload(String username, String code) {
//        JSONObject payload = getRequestObject(username);
//        try {
//            payload.put("vcode", code);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return payload.toString();
//    }
//
//    private String createSetPasswordJsonPayload(String username, String newPassword, String confirmPassword) {
//        JSONObject payload = getRequestObject(username);
//        try {
//            payload.put("new_password", newPassword);
//            payload.put("confirm_password", confirmPassword);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return payload.toString();
//    }
//
//    private String createResendCodeJsonPayload(String username) {
//        return getRequestObject(username).toString();
//    }
//
//    protected String createLoginChangePassword(String username, String oldPassword, String newPassword, String confirmPassword) {
//        JSONObject reader = getRequestObject(username);
//        try {
//            reader.put("old_password", oldPassword);
//            reader.put("new_password", newPassword);
//            reader.put("confirm_password", confirmPassword);
//            reader.put("device_id", Utils.id(getContext()));
//        } catch (JSONException ignored) {
//
//        }
//        return reader.toString();
//    }
//
//    protected String createLoginChangePin(String username, String oldPin, String newPin, String confirmPin) {
//        JSONObject reader = getRequestObject(username);
//        try {
//            reader.put("old_mpin", oldPin);
//            reader.put("new_mpin", newPin);
//            reader.put("confirm_mpin", confirmPin);
//            reader.put("device_id", Utils.id(getContext()));
//        } catch (JSONException ignored) {
//
//        }
//        return reader.toString();
//    }

}