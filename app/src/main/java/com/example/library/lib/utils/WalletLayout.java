package com.example.library.lib.utils;

import android.content.Context;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public abstract class WalletLayout extends TextInputLayout {
    private static final String KEY_REQUIRED = "Required";

    public Boolean isRequired = true;
    public Boolean isValid = false;
    private Validator validator;

    public String value;
    private LinearLayout rightItemsContainer;


    public WalletLayout(@NonNull Context context) {
        super(context);
        setup();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public WalletLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public WalletLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
        setupRightItemsContainer();

    }

    void setup() {

    }

    public void setMaxLength(int length) {
        Objects.requireNonNull(getEditText()).setFilters(new InputFilter[]{new InputFilter.LengthFilter(length)});
    }

    public void validate() {
        validator.onEditTextReceived(this);
        if (!isRequired) {
            isValid = true;
            return;
        }
        if (validator == null) {
            isValid = false;
            return;
        }
        isValid = validator.action(getTrimmedText());

    }

    public String getTrimmedText() {
        return Objects.requireNonNull(getEditText()).getText().toString().trim();
    }

    public void validateData(Validator validator) {
        this.validator = validator;
        if (!isRequired){
            isValid = true;
        }
        validator.onEditTextReceived(this);
    }

    public void displayError() {
        if (isValid) {
            onSuccess();
        } else {
            onError(getErrorMessage());
        }
    }

    public String getErrorMessage() {
        if (getTrimmedText().isEmpty()) {
            return KEY_REQUIRED;
        } else {
            if (validator != null) {
                return validator.getMessage();
            }
            return null;
        }
    }

    public abstract void onSuccess();

    public abstract void onDefault();

    public abstract void onError(String errorMessage);

    public interface Validator {
        Boolean action(String str);

        String getMessage();

        void onEditTextReceived(WalletLayout layout);
    }

    private void setupRightItemsContainer() {
        rightItemsContainer = new LinearLayout(getContext());

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        params.gravity = Gravity.END | Gravity.CENTER;
        params.rightMargin = 20;

        rightItemsContainer.setOrientation(LinearLayout.HORIZONTAL);
        rightItemsContainer.setLayoutParams(params);
//        mInputContainer.addView(rightItemsContainer);
    }

    public void addViewOnRight(View view) {
        rightItemsContainer.addView(view);
    }
}