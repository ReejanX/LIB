package com.example.library.lib.utils;

import org.jetbrains.annotations.NotNull;

public final class NpsData {
    protected String FunctionName;
    protected String Data;
    protected String Signature;

    public NpsData(String FunctionName, String Data, String Signature) {
        this.FunctionName = FunctionName;
        this.Data = Data;
        this.Signature = Signature;
    }

    @NotNull
    @Override
    public String toString() {
        return "ResponseData{" +
                "FunctionName='" + FunctionName + '\'' +
                ", ResponseData='" + Data + '\'' +
                ", Signature='" + Signature + '\'' +
                '}';
    }
}
