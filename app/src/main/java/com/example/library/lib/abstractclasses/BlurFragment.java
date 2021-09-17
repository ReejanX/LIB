package com.example.library.lib.abstractclasses;


import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.library.R;

import org.jetbrains.annotations.NotNull;


abstract class BlurFragment extends FullScreenDialogFragment {

    private static final String TAG = "BlurFragment";


    public BlurFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.blur_fragment, container, false);
    }

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = getActivity().getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        Bitmap b1 = view.getDrawingCache();
        Rect frame = new Rect();
        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        final int width = getActivity().getWindow().getDecorView().getWidth();
        final int height = getActivity().getWindow().getDecorView().getHeight();

        Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height - statusBarHeight);

        //define this only once if blurring multiple times
        RenderScript rs = RenderScript.create(getActivity());

        //this will blur the bitmapOriginal with a radius of 8 and save it in bitmapOriginal
        final Allocation input = Allocation.createFromBitmap(rs, b); //use this constructor for best performance, because it uses USAGE_SHARED mode which reuses memory
        final Allocation output = Allocation.createTyped(rs, input.getType());
        final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        script.setRadius(16f);
        script.setInput(input);
        script.forEach(output);
        output.copyTo(b);
        dialog.getWindow().setBackgroundDrawable(new BitmapDrawable(getResources(), b));
        return dialog;
    }
}
