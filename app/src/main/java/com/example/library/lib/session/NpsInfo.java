package com.example.library.lib.session;

import static com.example.library.lib.utils.Constants.KEY_PASSWORD;
import static com.example.library.lib.utils.Constants.KEY_PRIVATE;
import static com.example.library.lib.utils.Constants.KEY_URL;
import static com.example.library.lib.utils.Constants.KEY_USER;

import android.content.Context;

import com.example.library.lib.abstractclasses.NpsMemory;


public final class NpsInfo extends NpsMemory {
    private Builder mBuilder;
    public NpsInfo(Context context) {
        super(context);
    }

    @Override
    protected String getName() {
        return "noissesofnispn";
    }

    private void setmBuilder(Builder mBuilder){
        this.mBuilder = mBuilder;
    }

    public void apply(){
        if (mBuilder == null){
            return;
        }

        putString(KEY_URL,mBuilder.url);
        putString(KEY_USER,mBuilder.username);
        putString(KEY_PASSWORD,mBuilder.password);
        putString(KEY_PRIVATE,mBuilder.privateKey);
    }


    public String getUsername(){
        return getString(KEY_USER);
    }
    public String getUrl(){
        return getString(KEY_URL);
    }

    public String getPassword(){
        return getString(KEY_PASSWORD);
    }

    public String getPrivateKey(){
        return getString(KEY_PRIVATE);
    }
    public static class Builder {
        private String url;
        private String username;
        private String password;
        private String privateKey;

        private Context context;
        public Builder(Context context){
            this.context = context;
        }

        public Builder setPrivateKey(String privateKey) {
            this.privateKey = privateKey;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public NpsInfo build(){
            NpsInfo npsInfo = new NpsInfo(context);
            npsInfo.setmBuilder(this);
            return npsInfo;
        }
    }

}
