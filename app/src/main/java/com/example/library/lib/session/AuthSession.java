package com.example.library.lib.session;


import static com.example.library.lib.utils.Constants.KEY_ENCRYPTION;
import static com.example.library.lib.utils.Constants.KEY_TOKEN;

import android.content.Context;

import com.example.library.lib.abstractclasses.NpsMemory;
import com.example.library.lib.model.User;


public final class AuthSession extends NpsMemory {



    public AuthSession(Context context) {
        super(context);
    }

    @Override
    protected String getName() {
        return "noisseshtua";
    }

    public void storeUser(User user) {
        putString(KEY_TOKEN, user.data.getToken());
        putString(KEY_ENCRYPTION, user.data.getEncryption_key());
    }


    public String getToken() {
        return getString(KEY_TOKEN);
    }

    public String getKeyEncryption() {
        return getString(KEY_ENCRYPTION);
    }

    public boolean isLoggedIn(){
        return getToken() != null;
    }

    public void logout(){
        new UserSession(getContext()).clear();
        remove(KEY_TOKEN);
        remove(KEY_ENCRYPTION);
        clear();
    }

    @Override
    public void clear() {
        super.clear();
    }
}
