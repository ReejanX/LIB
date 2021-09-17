package com.example.library.lib.abstractclasses;

public abstract class BaseDialogFragment extends BlurFragment {

    public OnOkClickedListener onOkClickedListener;

    public BaseDialogFragment setOnOkClickedListener(OnOkClickedListener onOkClickedListener) {
        this.onOkClickedListener = onOkClickedListener;
        return this;
    }

    public interface OnOkClickedListener {
        void onOkClicked();
    }

}
