package com.example.library.lib.abstractclasses;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.library.lib.interfaces.OnActivityBackPressed;
import com.example.library.lib.interfaces.OnNetworkChangeListener;

import java.util.Objects;


abstract class FullScreenDialogFragment extends DialogFragment implements OnNetworkChangeListener, OnActivityBackPressed {
    private BaseActivity mActivity;

    @Override
    public void onStart() {
        super.onStart();


        Dialog dialog = getDialog();
        if (dialog != null) {
            Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            mActivity = (BaseActivity) context;
        }
        if (getActivity() instanceof BaseActivity) {
            mActivity = (BaseActivity) getActivity();
        }
        if (mActivity != null) {
            mActivity.setOnNetworkChangeListener(this);
            mActivity.setOnBackPressedListener(this);
        }
    }

    public void showProgress(String msg) {
        mActivity.showProgress(msg);
    }

    public void dismissProgress() {
        mActivity.dismissProgress();
    }

    public void showAlert(String message, BaseDialogFragment.OnOkClickedListener onOkClickedListener) {
        mActivity.showAlert(message, onOkClickedListener);
    }

    public void showAlert(String message) {
        showAlert(message, null);
    }

    public void dismissKeyboard(View view) {
        mActivity.dismissKeyboard(view);
    }

    @Override
    public void onNetworkChanged(boolean connected) {

    }
    public String getText(EditText et){
        return mActivity.getText(et);
    }
    @Override
    public Boolean onBackPressed() {
        return true;
    }
}
