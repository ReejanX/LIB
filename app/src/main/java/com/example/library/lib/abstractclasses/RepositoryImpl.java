package com.example.library.lib.abstractclasses;

import static com.example.library.lib.utils.ValidationUtils.checkLoginUserName;
import static com.example.library.lib.utils.ValidationUtils.isValidEmail;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.StringRes;

import com.example.library.R;
import com.example.library.lib.utils.TextWatcher;
import com.example.library.lib.utils.WalletLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;


public abstract class RepositoryImpl {
    protected final WalletLayout.Validator notRequiredValidator = new WalletLayout.Validator() {
        @Override
        public Boolean action(String str) {
            return true;
        }

        @Override
        public String getMessage() {
            return null;
        }

        @Override
        public void onEditTextReceived(WalletLayout layout) {

        }
    };
    private final Context context;
    public final WalletLayout.Validator pinValidator = new WalletLayout.Validator() {
        @Override
        public Boolean action(String str) {
            return checkSixDigitPin(str);
        }

        @Override
        public String getMessage() {
            return getString(R.string.error_pin);
        }

        @Override
        public void onEditTextReceived(WalletLayout layout) {
            layout.setMaxLength(6);
        }
    };
    public final WalletLayout.Validator emptyValidator = new WalletLayout.Validator() {
        @Override
        public Boolean action(String str) {
            return checkEmpty(str);
        }

        @Override
        public String getMessage() {
            return getString(R.string.error_empty_path);
        }

        @Override
        public void onEditTextReceived(WalletLayout layout) {
        }
    };
    public WalletLayout.Validator usernameValidator = new WalletLayout.Validator() {
        @Override
        public Boolean action(String str) {
            return checkLoginUserName(str);
        }

        @Override
        public String getMessage() {
            return getString(R.string.required);
        }

        @Override
        public void onEditTextReceived(WalletLayout layout) {

        }
    };
    public WalletLayout.Validator emailValidator = new WalletLayout.Validator() {
        @Override
        public Boolean action(String str) {
            return isValidEmail(str);
        }

        @Override
        public String getMessage() {
            return getString(R.string.error_email);
        }

        @Override
        public void onEditTextReceived(WalletLayout layout) {

        }
    };
    public WalletLayout.Validator passwordValidator = new WalletLayout.Validator() {
        @Override
        public Boolean action(String str) {
            return checkUserPassword(str);
        }

        @Override
        public String getMessage() {
            return getString(R.string.error_password);
        }

        @Override
        public void onEditTextReceived(WalletLayout layout) {
            layout.setMaxLength(16);
        }
    };
    public WalletLayout.Validator amountValidator = new WalletLayout.Validator() {
        @Override
        public Boolean action(String str) {
            return checkAmount(str);
        }

        @Override
        public String getMessage() {
            return getString(R.string.error_amount);
        }

        @Override
        public void onEditTextReceived(WalletLayout layout) {

        }
    };
    public WalletLayout.Validator remarksValidator = new WalletLayout.Validator() {
        @Override
        public Boolean action(String str) {
            return checkEmpty(str);
        }

        @Override
        public String getMessage() {
            return getString(R.string.error_remarks);
        }

        @Override
        public void onEditTextReceived(WalletLayout layout) {

        }
    };
    public WalletLayout.Validator landLineValidator = new WalletLayout.Validator() {
        @Override
        public Boolean action(String str) {
            return checkLandLine(str);
        }

        @Override
        public String getMessage() {
            return getString(R.string.error_land_line_no);
        }

        @Override
        public void onEditTextReceived(WalletLayout layout) {
            layout.setMaxLength(9);
        }
    };

    protected List<WalletLayout> walletLayouts = new ArrayList<>();

    protected RepositoryImpl(Context context) {
        this.context = context;
    }

    protected boolean passwordTextValidator(String password) {
        final String PASSWORD_PATTERN = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
//        final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\\\$%\\\\^&\\\\*])(?=.{8,16}$)";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        if (password == null) {
            return false;
        }
        return pattern.matcher(password).matches();
    }

    protected void addTextValidator(final WalletLayout kisanWalletLayout, final WalletLayout.Validator validator) {
        walletLayouts.add(kisanWalletLayout);
        EditText editText = kisanWalletLayout.getEditText();
        if (editText == null) {
            return;
        }
        kisanWalletLayout.validateData(validator);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                kisanWalletLayout.setError(null);
                kisanWalletLayout.onDefault();
                kisanWalletLayout.validate();
            }
        });

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    return;
                }
                kisanWalletLayout.displayError();
            }
        });

    }

    public Boolean isValid() {
        boolean isValid = true;

        for (WalletLayout view : walletLayouts) {
            if (!view.isValid) {
                view.displayError();
                isValid = false;
            }
        }
        return isValid;
    }

    protected boolean checkUserPassword(String password) {
        int passwordLen = password.length();
        return passwordLen <= 16 && passwordLen >= 8;
    }

    protected boolean checkEqualText(String password, String confirm) {
        return password.equals(confirm);
    }

    protected boolean checkSixDigitPin(String pin) {
        int pinLen = pin.length();
        return pinLen == 6;
    }

    protected boolean checkEmpty(String string) {
        return !string.isEmpty();
    }

//    protected boolean checkFullName(String fullName) {
//        String[] fName = fullName.split(" ");
//        return fName.length > 2;
//    }

    protected boolean checkAmount(String string) {
        return checkEmpty(string) && !string.equals("0") && !string.startsWith("0");
    }

    protected boolean checkLandLine(String string) {
        return checkEmpty(string) && string.length() <= 9 && string.startsWith("0");
    }

    protected boolean checkMobile(String string) {
        return checkEmpty(string) && string.length() == 10 && string.startsWith("9");
    }

//    protected String checkNumber(Services.Data data, String sub) {
//        if (!checkMobile(sub)) {
//            return getString(R.string.error_mobile_no);
//        }
//        if (data == null) {
//            return null;
//        }
//        sub = sub.replace("+", "");
//        sub = sub.replace("(", "");
//        sub = sub.replace(")", "");
//        sub = sub.replace("-", "");
//        String productId = data.product_id;
//        String prefix = sub.substring(0, 3);
//
//        switch (productId) {
//            //ntc gsm
//            case "1":
//                if (!prefix.equals("984") && !prefix.equals("986")) {
//                    return data.product_service_info;
//                } else {
//                    return null;
//                }
//                //ncell
//            case "2":
//                if (!prefix.equals("980") && !prefix.equals("981") && !prefix.equals("982")) {
//                    return data.product_service_info;
//                } else {
//                    return null;
//                }
//                //utl
//            case "3":
//                if (!prefix.equals("972")) {
//                    return data.product_service_info;
//                } else {
//                    return null;
//                }
//                //smart cell
//            case "4":
//                if (!prefix.equals("988") && !prefix.equals("961") && !prefix.equals("962")) {
//                    return data.product_service_info;
//                } else {
//                    return null;
//                }
//                //cdma
//            case "9":
//            case "10":
//                if (!prefix.equals("974") && !prefix.equals("976") && !prefix.equals("975")) {
//                    return data.product_service_info;
//                } else {
//                    return null;
//                }
//                //ntc postpaid
//            case "14":
//                if (!prefix.equals("985")) {
//                    return data.product_service_info;
//                } else {
//                    return null;
//                }
//        }
//        return null;
//    }

    protected Boolean isValidTF(WeakReference<WalletLayout> ref, WalletLayout walletLayout) {
        return ref != null && ref.get() != null && ref.get().equals(walletLayout);
    }

    public String getString(@StringRes int id) {
        return context.getString(id);
    }

    public Context getContext() {
        return context;
    }

    public String getText(WeakReference<WalletLayout> ref) {
        return getText(ref.get());
    }

    public String getText(WalletLayout walletLayout) {
        return Objects.requireNonNull(walletLayout.getEditText()).getText().toString().trim();
    }

    public class PasswordSValidator implements WalletLayout.Validator {
        WalletLayout newPassword, confirmPassword;

        public PasswordSValidator(WalletLayout newPassword, WalletLayout confirmPassword) {
            this.newPassword = newPassword;
            this.confirmPassword = confirmPassword;
        }

        @Override
        public Boolean action(String str) {
            boolean isValid = true;
            if (confirmPassword.getTrimmedText().isEmpty()) {
                isValid = true;
            } else {
                isValid = checkEqualText(newPassword.getTrimmedText(), confirmPassword.getTrimmedText());
                if (isValid) {
                    confirmPassword.onSuccess();
                    confirmPassword.isValid = true;
                } else {
                    confirmPassword.onError(confirmPassword.getErrorMessage());
                    confirmPassword.isValid = false;
                }
            }

            return passwordTextValidator(str);
        }

        @Override
        public String getMessage() {
            return getString(R.string.error_password_combination);
        }

        @Override
        public void onEditTextReceived(WalletLayout layout) {
            layout.setMaxLength(16);
        }
    }

    public class PasswordValidator implements WalletLayout.Validator {
        WalletLayout newPassword, confirmPassword;

        public PasswordValidator(WalletLayout newPassword, WalletLayout confirmPassword) {
            this.newPassword = newPassword;
            this.confirmPassword = confirmPassword;
        }

        @Override
        public void onEditTextReceived(WalletLayout layout) {
            layout.setMaxLength(16);
        }

        @Override
        public Boolean action(String str) {
            if (!newPassword.isValid) {
                return false;
            }
            return checkEqualText(newPassword.getTrimmedText(), confirmPassword.getTrimmedText());
        }

        @Override
        public String getMessage() {
            return newPassword.isValid ? getString(R.string.error_password_not_match) : getString(R.string.error_password_combination);
        }
    }

    public class PinSValidator implements WalletLayout.Validator {
        WalletLayout newPin, confirmPin;

        public PinSValidator(WalletLayout newPin, WalletLayout confirmPin) {
            this.newPin = newPin;
            this.confirmPin = confirmPin;
        }

        @Override
        public Boolean action(String str) {
            boolean isValid = true;
            if (confirmPin.getTrimmedText().isEmpty()) {
                isValid = true;
            } else {
                isValid = checkEqualText(newPin.getTrimmedText(), confirmPin.getTrimmedText());
                if (isValid) {
                    confirmPin.isValid = true;
                    confirmPin.onSuccess();
                } else {
                    confirmPin.isValid = false;
                    confirmPin.onError(confirmPin.getErrorMessage());
                }
            }
            return checkSixDigitPin(newPin.getTrimmedText());
        }

        @Override
        public void onEditTextReceived(WalletLayout layout) {
            layout.setMaxLength(4);
        }

        @Override
        public String getMessage() {
            return getString(R.string.error_pin_combination);
        }
    }

    public class PinValidator implements WalletLayout.Validator {
        WalletLayout newPin, confirmPin;

        public PinValidator(WalletLayout newPin, WalletLayout confirmPin) {
            this.newPin = newPin;
            this.confirmPin = confirmPin;
        }

        @Override
        public Boolean action(String str) {
            if (!newPin.isValid) {
                return false;
            }
            return checkEqualText(newPin.getTrimmedText(), confirmPin.getTrimmedText());
        }

        @Override
        public String getMessage() {
            return getString(R.string.error_pin_not_match);
        }

        @Override
        public void onEditTextReceived(WalletLayout layout) {
            layout.setMaxLength(6);
        }
    }

//    public class MobileNoValidator implements WalletLayout.Validator {
//        private final Services.Data data;
//        String errorMessage = getString(R.string.error_mobile_no);
//
//        public MobileNoValidator(Services.Data data) {
//            this.data = data;
//        }
//
//        public MobileNoValidator() {
//            this.data = null;
//        }
//
//        @Override
//        public Boolean action(String str) {
//            String var = checkNumber(data, str);
//            if (var != null) {
//                ;
//                errorMessage = var;
//            }
//            return var == null;
//        }
//
//        @Override
//        public String getMessage() {
//            return errorMessage;
//        }
//
//        @Override
//        public void onEditTextReceived(WalletLayout layout) {
//
//        }
//    }

    public WalletLayout.Validator neaSubscriberValidator = new WalletLayout.Validator() {
        @Override
        public Boolean action(String str) {
            return checkEmpty(str);
        }

        @Override
        public String getMessage() {
            return getString(R.string.error_nea_id);
        }

        @Override
        public void onEditTextReceived(WalletLayout layout) {

        }
    };

    public WalletLayout.Validator customerIdValidator = new WalletLayout.Validator() {
        @Override
        public Boolean action(String str) {
            return checkEmpty(str);
        }

        @Override
        public String getMessage() {
            return getString(R.string.error_customer_id);
        }

        @Override
        public void onEditTextReceived(WalletLayout layout) {

        }
    };

    public WalletLayout.Validator vianetUserValidator = new WalletLayout.Validator() {
        @Override
        public Boolean action(String str) {
            return checkEmpty(str);
        }

        @Override
        public String getMessage() {
            return getString(R.string.vianet_customer_id);
        }

        @Override
        public void onEditTextReceived(WalletLayout layout) {

        }
    };

    public WalletLayout.Validator wlinkUserValidator = new WalletLayout.Validator() {
        @Override
        public Boolean action(String str) {
            return checkEmpty(str);
        }

        @Override
        public String getMessage() {
            return getString(R.string.wlink_customer_id);
        }

        @Override
        public void onEditTextReceived(WalletLayout layout) {

        }
    };

    public WalletLayout.Validator merchantNameValidator = new WalletLayout.Validator() {
        @Override
        public Boolean action(String str) {
            return checkEmpty(str);
        }

        @Override
        public String getMessage() {
            return getString(R.string.merchant_name);
        }

        @Override
        public void onEditTextReceived(WalletLayout layout) {

        }
    };

    public WalletLayout.Validator codeValidator = new WalletLayout.Validator() {
        @Override
        public Boolean action(String str) {
            return checkEmpty(str);
        }

        @Override
        public String getMessage() {
            return getString(R.string.merchant_code);
        }

        @Override
        public void onEditTextReceived(WalletLayout layout) {

        }
    };

    public WalletLayout.Validator nameValidator = new WalletLayout.Validator() {
        @Override
        public Boolean action(String str) {
            return checkEmpty(str);
        }

        @Override
        public String getMessage() {
            return getString(R.string.error_full_name);
        }

        @Override
        public void onEditTextReceived(WalletLayout layout) {

        }
    };

    public WalletLayout.Validator mobileValidator = new WalletLayout.Validator() {
        @Override
        public Boolean action(String str) {
            return checkMobile(str);
        }

        @Override
        public String getMessage() {
            return getString(R.string.error_mobile_no);
        }

        @Override
        public void onEditTextReceived(WalletLayout layout) {

        }
    };

    public WalletLayout.Validator genderValidator = new WalletLayout.Validator() {
        @Override
        public Boolean action(String str) {
            return checkEmpty(str);
        }

        @Override
        public String getMessage() {
            return getString(R.string.error_gender);
        }

        @Override
        public void onEditTextReceived(WalletLayout layout) {

        }
    };
}