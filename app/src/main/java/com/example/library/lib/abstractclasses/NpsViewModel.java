package com.example.library.lib.abstractclasses;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.lifecycle.AndroidViewModel;

public abstract class NpsViewModel extends AndroidViewModel {

   public NpsViewModel(@NonNull Application application) {
       super(application);
   }

   public String getString(@StringRes int id){
       return getApplication().getString(id);
   }
}
