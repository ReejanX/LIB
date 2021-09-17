package com.example.library.lib.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

public final class ModelProvider {
    public synchronized static <T extends ViewModel> T get(ViewModelStoreOwner owner, @NonNull Class<T> modelClass) {
        return new ViewModelProvider(owner).get(modelClass);
    }
}
