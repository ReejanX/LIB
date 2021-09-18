package moc.spn.sbil.lellaw.session;

import android.content.Context;

import moc.spn.sbil.lellaw.abstractclasses.NpsMemory;
import moc.spn.sbil.lellaw.auth.model.User;

import static moc.spn.sbil.lellaw.utils.Constants.KEY_ENCRYPTION;
import static moc.spn.sbil.lellaw.utils.Constants.KEY_TOKEN;

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
