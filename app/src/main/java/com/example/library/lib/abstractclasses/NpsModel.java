package com.example.library.lib.abstractclasses;

import com.example.library.lib.api.NpsError;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public abstract class NpsModel {
    @SerializedName("code")
    @Expose
    private String code = "";
    @SerializedName("message")
    @Expose
    private String message = "";
    @SerializedName("errors")
    @Expose
    public List<NpsError> errors;

    public NpsError getError(){

        return errors == null ? null : errors.size() == 0 ? new NpsError("","Something went wrong"):errors.get(0);
    }

    public String getCode() {

        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<NpsError> getErrors() {
        return errors;
    }

    public boolean isSuccessful(){
        return code != null && code.equalsIgnoreCase("0");
    }

}
