package com.example.library.lib.api;

public interface OnNpsApiSessionExpiredResult extends OnNpsApiErrorResult{
    void onSessionExpired(String message);
}
