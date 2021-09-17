package com.example.library.lib.abstractclasses;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.library.lib.interfaces.OnActivityBackPressed;
import com.example.library.lib.interfaces.OnNetworkChangeListener;


public abstract class BaseFragment extends Fragment implements OnNetworkChangeListener, OnActivityBackPressed {
    private BaseActivity mActivity;


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

    @Override
    public void onNetworkChanged(boolean connected) {

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
    public Boolean onBackPressed() {
        return true;
    }
    public String getText(EditText et){
        return mActivity.getText(et);
    }
}
