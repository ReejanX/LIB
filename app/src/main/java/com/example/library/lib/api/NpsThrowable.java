package com.example.library.lib.api;


public final class NpsThrowable extends Throwable{
    private NpsError error;
    public static NpsThrowable getDefaultThrowable(){
        return new NpsThrowable(new NpsError("1","Something went wrong!!!"));
    }
    public NpsThrowable(NpsError error){
        super(error.getErrorMessage());
        this.error = error;
    }

    public NpsError getError() {
        return error;
    }
}
