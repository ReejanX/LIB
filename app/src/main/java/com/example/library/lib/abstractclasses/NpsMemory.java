package com.example.library.lib.abstractclasses;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.library.lib.utils.JavaAes;
import com.example.library.lib.utils.Utils;


public abstract class NpsMemory {
    private final Context context;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private final String id;
    public NpsMemory(Context context) {
        this.context = context;
        this.id = Utils.id(context);
        pref = context.getSharedPreferences(getName(), Context.MODE_PRIVATE);
        editor = pref.edit();
        editor.apply();
        editor.commit();
    }

    public Context getContext() {
        return context;
    }

    public void putString(String key, String value){
        editor.putString(key, JavaAes.doEncryptedAES(value,id));
        editor.apply();
        editor.commit();
    }

    public String getString(String key){
        final String val = pref.getString(key, null);
        if (val ==null){
            return null;
        }
        return JavaAes.doDecryptedAES(val,id);
    }
    public void clear() {
        editor.clear();
        editor.apply();
        editor.commit();
    }


    public void remove(String key){
        editor.remove(key);
        editor.apply();
    }

    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener){
        pref.registerOnSharedPreferenceChangeListener(listener);
    }
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener){
        pref.unregisterOnSharedPreferenceChangeListener(listener);
    }

    protected abstract String getName();
}
