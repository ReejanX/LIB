package com.example.library.lib.utils;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

public class ProgressRequestBody extends RequestBody {
    private static final int DEFAULT_BUFFER_SIZE = 2048;
    private File mFile;
    private ImageUploadCallback mListener;
    private String content_type;
    // checks when the function is called second time
    private int writeToCall = 0;

    public ProgressRequestBody(final File file, String content_type, final ImageUploadCallback listener) {
        this.content_type = content_type;
        mFile = file;
        mListener = listener;
        this.writeToCall = 0;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return MediaType.parse(content_type + "/*");
    }

    @Override
    public long contentLength() throws IOException {
        return mFile.length();
    }

    @Override
    public void writeTo(@NonNull BufferedSink sink) {
        writeToCall++; // update the counter
        long fileLength = mFile.length();
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        long uploaded = 0;
        try (FileInputStream in = new FileInputStream(mFile)) {
            int read;
            Handler handler = new Handler(Looper.getMainLooper());
            while ((read = in.read(buffer)) != -1) {
                uploaded += read;
                sink.write(buffer, 0, read);
                if (writeToCall == 2) { // updating the progress
                    handler.post(new ProgressUpdater(uploaded, fileLength));
                }
            }
        } catch (IOException e) {
            if (mListener != null) {
                mListener.onError(e.getLocalizedMessage());
                mListener = null;
            }
        }
    }

    public interface ImageUploadCallback {
        void onProgressUpdate(int percentage);

        void onError(String message);

        void onSuccess(String message);
    }

    private class ProgressUpdater implements Runnable {
        private long mUploaded;
        private long mTotal;

        ProgressUpdater(long uploaded, long total) {
            mUploaded = uploaded;
            mTotal = total;
        }

        @Override
        public void run() {
            if (mListener != null) {
                int progress = (int) (100 * mUploaded / mTotal);

                mListener.onProgressUpdate(progress);
                if (progress == 100) {
                    mListener.onSuccess("");
                    mListener = null;
                }
            }
        }
    }
}