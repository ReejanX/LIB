package com.example.library.lib.api;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class NpsError {
    @SerializedName("error_code")
    @Expose
    private String error_code = "";
    @SerializedName("error_message")
    @Expose
    private String error_message = "";

    public NpsError(String error_code, String error_message){
        this.error_code = error_code;
        this.error_message = error_message;
    }
    @NonNull
    @Override
    public String toString() {
        return "error_code: " + error_code + " - error_message: " + error_message;
    }

    public String getErrorCode() {
        return error_code;
    }

    public String getErrorMessage() {
        return error_message;
    }
}