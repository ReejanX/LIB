package moc.spn.sbil.lellaw.session;

import android.content.Context;

import moc.spn.sbil.lellaw.abstractclasses.NpsMemory;

import static moc.spn.sbil.lellaw.utils.Constants.KEY_PASSWORD;
import static moc.spn.sbil.lellaw.utils.Constants.KEY_USER;

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
