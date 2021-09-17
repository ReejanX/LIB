package com.example.library.lib.abstractclasses;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.text.Editable;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.R;
import com.example.library.lib.interfaces.OnActivityBackPressed;
import com.example.library.lib.interfaces.OnNetworkChangeListener;
import com.example.library.lib.utils.Utils;


public abstract class BaseActivity extends AppCompatActivity {

    private OnNetworkChangeListener onNetworkChangeListener;
    private LoadingFragment loadingFragment;
    private OnActivityBackPressed onBackPressedListener;
    private BroadcastReceiver mConnReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            onNetworkChanged(isNetworkConnected());
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(this.mConnReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mConnReceiver);
    }

    private Boolean isNetworkConnected() {
        return Utils.isNetworkConnected(this);
    }

    public void showProgress(String msg) {
        if (loadingFragment == null) {
            loadingFragment = LoadingFragment.newInstance(msg);
            loadingFragment.show(getSupportFragmentManager(), "Prog");
        } else {
            loadingFragment.updateMessage(msg);
        }
    }

    public void dismissProgress() {
        if (loadingFragment != null) {
            loadingFragment.dismiss();
            loadingFragment = null;
        }
    }

    private void showAlertDialog(String message, BaseDialogFragment.OnOkClickedListener onOkClickedListener) {
        BaseDialogFragment at = getAppDialogFragment(message);
        at.setOnOkClickedListener(onOkClickedListener);
        at.show(getSupportFragmentManager(), "");
    }

    public void showAlert(String message, BaseDialogFragment.OnOkClickedListener onOkClickedListener) {
        showAlertDialog(message, onOkClickedListener);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            Rect outRect = new Rect();
            if (v == null) {
                return super.dispatchTouchEvent(event);
            }
            v.getGlobalVisibleRect(outRect);
            if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                v.clearFocus();
                dismissKeyboard(v);
            }
        }
        return super.dispatchTouchEvent(event);
    }
    public void showAlert(String message) {
        showAlert(message, null);
    }

    public void dismissKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.fadeout, R.anim.fadein);
    }

    protected void onNetworkChanged(boolean connected) {
        if (onNetworkChangeListener != null) {
            onNetworkChangeListener.onNetworkChanged(connected);
        }

    }

    public void setOnNetworkChangeListener(OnNetworkChangeListener onNetworkChangeListener) {
        this.onNetworkChangeListener = onNetworkChangeListener;
    }

    public void setOnBackPressedListener(OnActivityBackPressed onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    @Override
    public void onBackPressed() {
        if (onBackPressedListener != null) {
            if (onBackPressedListener.onBackPressed()) {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    protected abstract BaseDialogFragment getAppDialogFragment(String message);

    public String getText(EditText et){
        return getText(et.getText());
    }
    private String getText(Editable editable){
        return editable.toString().trim();
    }

}
