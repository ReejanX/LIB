package com.example.library.lib.api;


import com.example.library.lib.abstractclasses.NpsModel;

public interface OnNpsApiResult<D extends NpsModel> extends OnNpsApiSessionExpiredResult {
    void onSuccess(D data);
}
