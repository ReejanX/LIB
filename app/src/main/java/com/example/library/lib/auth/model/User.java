package moc.spn.sbil.lellaw.auth.model;


import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import moc.spn.sbil.lellaw.abstractclasses.NpsModel;
import moc.spn.sbil.lellaw.abstractclasses.ResponseData;

public class User extends NpsModel {
    private static final String TAG = "User";

    @SerializedName("data")
    @Expose
    public Data data;


    public static class Data extends ResponseData {
        @SerializedName("token")
        @Expose
        public String token = "";
        @SerializedName("encryption_key")
        @Expose
        public String encryption_key = "";
        @SerializedName("first_time_login")
        @Expose
        public Boolean first_time_login = false;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getEncryption_key() {
            return encryption_key;
        }

        public void setEncryption_key(String encryption_key) {
            this.encryption_key = encryption_key;
        }
        ///772F31D6B85843EDA1DC59C823A9BEEC0B9824A7DFCF4F39B54DCCB3C4A9EE60/0C955B35D47C4EC3B369D8B16D2262CFAA99610E77BE4257BD2C0BF1C9048B6B-A785E58FA3D741FA9058E632A573E54723EEA0428D994DC28DEAFF2303CFAE13
        public Boolean getFirst_time_login() {
            return first_time_login;
        }

        public void setFirst_time_login(Boolean first_time_login) {
            this.first_time_login = first_time_login;
        }

        @Override
        public String toString() {
            return "ResponseData{" +
                    "token='" + token + '\'' +
                    ", encryption_key='" + encryption_key + '\'' +
                    ", first_time_login=" + first_time_login +
                    '}';
        }


    }

    @Override
    public String toString() {
        return "User{" +
                "code='" + getCode() + '\'' +
                ", message='" + getMessage() + '\'' +
                ", data=" + data +
                ", errors=" + getErrors() +
                '}';
    }
}
