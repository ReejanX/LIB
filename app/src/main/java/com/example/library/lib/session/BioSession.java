package com.example.library.lib.session;

import static com.example.library.lib.utils.Constants.KEY_PASSWORD;
import static com.example.library.lib.utils.Constants.KEY_USER;

import android.content.Context;

import com.example.library.lib.abstractclasses.NpsMemory;


public final class BioSession extends NpsMemory {


    public BioSession(Context context) {
        super(context);
    }

    @Override
    protected String getName() {
        return "noissesoib";
    }

    public void store(String username,String password){
        putString(KEY_USER,username);
        putString(KEY_PASSWORD,password);
    }

    public String getUsername(){
        return getString(KEY_USER);
    }
    public String getPassword(){
        return getString(KEY_PASSWORD);
    }


}
