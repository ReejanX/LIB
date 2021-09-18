package com.example.library.lib.auth;

import android.content.Context;

import com.example.library.lib.abstractclasses.RepositoryImpl;
import com.example.library.lib.api.OnNpsApiResult;
import com.example.library.lib.model.User;
import com.example.library.lib.session.AuthSession;
import com.example.library.lib.session.BioSession;
import com.example.library.lib.utils.WalletLayout;

import java.lang.ref.WeakReference;



public class UserRepoImpl extends RepositoryImpl {
    private final UserRepo loginRepo;

    /**
     * user login auth
     */
    private WeakReference<WalletLayout> usernameRef;
    private WeakReference<WalletLayout> passwordRef;

    /**
     * resetPassword
     */
    private WeakReference<WalletLayout> resetRef;

    /**
     * setPassword
     */
    private WeakReference<WalletLayout> newPasswordRef;
    private WeakReference<WalletLayout> confirmPasswordRef;

    //change password
    private WeakReference<WalletLayout> oldLoginPasswordRef;
    private WeakReference<WalletLayout> newLoginPasswordRef;
    private WeakReference<WalletLayout> confirmLoginPasswordRef;

    //change pin
    private WeakReference<WalletLayout> loginOldPinRef;
    private WeakReference<WalletLayout> loginNewPinRef;
    private WeakReference<WalletLayout> confirmLoginPinRef;


    protected UserRepoImpl(Context context) {
        super(context);

        loginRepo = new UserRepo(context);
    }

    /**
     * validate textinputlayout for username and password
     *
     * @param usernameLayout
     * @param passwordLayout
     * @param <T>
     */
    protected <T extends WalletLayout> void setUsernameAndPasswordTextField(T usernameLayout, T passwordLayout) {
        if (usernameLayout == null || passwordLayout == null) {
            return;
        }

        walletLayouts.clear();
        usernameRef = new WeakReference<WalletLayout>(usernameLayout);
        passwordRef = new WeakReference<WalletLayout>(passwordLayout);

        addTextValidator(usernameLayout, usernameValidator);
        addTextValidator(passwordLayout, passwordValidator);
    }

    /**
     * validate textinputlayout for username in reset password
     *
     * @param usernameLayout
     * @param <T>
     */
    protected <T extends WalletLayout> void setResetPassword(T usernameLayout) {
        if (usernameLayout == null) {
            return;
        }

        walletLayouts.clear();
        resetRef = new WeakReference<WalletLayout>(usernameLayout);
        addTextValidator(usernameLayout, usernameValidator);
    }

    /**
     * validate textinputlayout for password
     *
     * @param newPassword
     * @param confirmPassword
     * @param <T>
     */
    protected <T extends WalletLayout> void setPasswordTextField(T newPassword, T confirmPassword) {
        if (newPassword == null || confirmPassword == null) {
            return;
        }

        walletLayouts.clear();
        newPasswordRef = new WeakReference<WalletLayout>(newPassword);
        confirmPasswordRef = new WeakReference<WalletLayout>(confirmPassword);
        addTextValidator(newPassword, passwordValidator);
        addTextValidator(confirmPassword, new PasswordValidator(newPassword, confirmPassword));
    }

    //validate textinputlayout for login password
    protected <T extends WalletLayout> void setLoginChangePasswordTextField(T oldPasswordLayout, T newPasswordLayout, T confirmPasswordLayout) {
        if (oldPasswordLayout == null || newPasswordLayout == null || confirmPasswordLayout == null) {
            return;
        }

        walletLayouts.clear();
        oldLoginPasswordRef = (WeakReference<WalletLayout>) new WeakReference<>(oldPasswordLayout);
        newLoginPasswordRef = (WeakReference<WalletLayout>) new WeakReference<>(newPasswordLayout);
        confirmLoginPasswordRef = (WeakReference<WalletLayout>) new WeakReference<>(confirmPasswordLayout);

        addTextValidator(oldPasswordLayout, passwordValidator);
        addTextValidator(newPasswordLayout, new PasswordSValidator(newPasswordLayout,confirmPasswordLayout));
        addTextValidator(confirmPasswordLayout, new PasswordValidator(newPasswordLayout, confirmPasswordLayout));
    }

    //validate textinputlayout for login pin
    protected <T extends WalletLayout> void setLoginChangePinTextField(T oldPinLayout, T newPinLayout, T confirmPinLayout) {
        if (oldPinLayout == null || newPinLayout == null || confirmPinLayout == null) {
            return;
        }

        walletLayouts.clear();
        loginOldPinRef = (WeakReference<WalletLayout>) new WeakReference<>(oldPinLayout);
        loginNewPinRef = (WeakReference<WalletLayout>) new WeakReference<>(newPinLayout);
        confirmLoginPinRef = (WeakReference<WalletLayout>) new WeakReference<>(confirmPinLayout);

        addTextValidator(oldPinLayout, pinValidator);
        addTextValidator(newPinLayout, new PinSValidator(newPinLayout,confirmPinLayout));
        addTextValidator(confirmPinLayout, new PinValidator(newPinLayout, confirmPinLayout));
    }


    /**
     * user login auth
     *
     * @param apiResult
     */
    //TODO 1 : this func will take our data from viewModel,
    //So i will add username and password as Strings before the interface : apiResult
    //
    protected void login(String username,String  password, OnNpsApiResult<User> apiResult) {
        userLogin(username, password, apiResult);
    }

    private void userLogin(final String username, final String password, final OnNpsApiResult<User> apiResult) {
        loginRepo.login(username, password, new OnNpsApiResult<User>() {
            @Override

            public void onSuccess(final User data) {
                if (data.isSuccessful()) {
                    new AuthSession(getContext()).storeUser(data);
                    new BioSession(getContext()).store(username, password);
                }
                apiResult.onSuccess(data);
            }

            @Override
            public void onError(Throwable throwable) {
                apiResult.onError(throwable);
            }

            @Override
            public void onSessionExpired(String message) {
                apiResult.onSessionExpired(message);
            }
        });
    }


    /**
     * reset password
     *
     * @param apiResult
     */
//    protected void resetPassword(OnNpsApiResult<Reset> apiResult) {
//        final String username = getText(resetRef);
//        userResetPassword(username, apiResult);
//    }
//
//    private void userResetPassword(String username, OnNpsApiResult<Reset> apiResult) {
//        loginRepo.reset(username, apiResult);
//    }


    /**
     * verify code
     *
     * @param username
     * @param code
     * @param apiResult
     */
//    protected void verifyCode(String username, String code, OnNpsApiResult<Reset> apiResult) {
//        userVerifyCode(username, code, apiResult);
//    }
//
//    private void userVerifyCode(String username, String code, OnNpsApiResult<Reset> apiResult) {
//        loginRepo.verify(username, code, apiResult);
//    }
//
//
//    /**
//     * set password
//     *
//     * @param username
//     * @param apiResult
//     */
//    protected void setPassword(String username, OnNpsApiResult<SetPassword> apiResult) {
//        final String newPassword = getText(newPasswordRef);
//        final String confirmPassword = getText(confirmPasswordRef);
//        userSetPassword(username, newPassword, confirmPassword, apiResult);
//    }
//
//    private void userSetPassword(String username, String newPassword, String confirmPassword, OnNpsApiResult<SetPassword> apiResult) {
//        loginRepo.setPassword(username, newPassword, confirmPassword, apiResult);
//    }
//
//    protected void userSetVerifiedPassword(String username, String newPassword, String confirmPassword, OnNpsApiResult<SetPassword> apiResult) {
//        loginRepo.setPassword(username, newPassword, confirmPassword, apiResult);
//    }
//
//
//    /**
//     * resend otp
//     *
//     * @param username
//     * @param apiResult
//     */
//    protected void resendOtp(String username, OnNpsApiResult<Resend> apiResult) {
//        userResendOtp(username, apiResult);
//    }
//
//    private void userResendOtp(String username, OnNpsApiResult<Resend> apiResult) {
//        loginRepo.resendCode(username, apiResult);
//    }
//
//    /**
//     * forceful change login password
//     *
//     * @param username
//     * @param apiResult
//     */
//    protected void changeLoginPassword(String username, OnNpsApiResult<ChangePassword> apiResult) {
//        final String oldPassword = getText(oldLoginPasswordRef);
//        final String newPassword = getText(newLoginPasswordRef);
//        final String confirmPassword = getText(confirmLoginPasswordRef);
//        userChangeLoginPassword(username, oldPassword, newPassword, confirmPassword, apiResult);
//    }
//
//    private void userChangeLoginPassword(String username, String oldPassword, String newPassword, String confirmPassword, OnNpsApiResult<ChangePassword> apiResult) {
//        loginRepo.loginChangePassword(username, oldPassword, newPassword, confirmPassword, apiResult);
//    }
//
//
//    /**
//     * forceful change login pin
//     *
//     * @param username
//     * @param apiResult
//     */
//    protected void changeLoginPin(String username, OnNpsApiResult<ChangePin> apiResult) {
//        final String oldPin = getText(loginOldPinRef);
//        final String newPin = getText(loginNewPinRef);
//        final String confirmPin = getText(confirmLoginPinRef);
//        userChangeLoginPin(username, oldPin, newPin, confirmPin, apiResult);
//    }
//
//    private void userChangeLoginPin(String username, String oldPin, String newPin, String confirmPin, OnNpsApiResult<ChangePin> apiResult) {
//        //confirm pin not included in api request payload
//        loginRepo.loginChangePin(username, oldPin, newPin, confirmPin, apiResult);
//    }
//
//    /**
//     * logout
//     *
//     * @param OnNpsApiResult
//     */
//    protected void logout(OnNpsApiResult<Logout> OnNpsApiResult) {
//        logoutUser(OnNpsApiResult);
//    }
//
//    private void logoutUser(final OnNpsApiResult<Logout> OnNpsApiResult) {
//        loginRepo.logout(new OnNpsApiResult<Logout>() {
//            @Override
//            public void onSuccess(Logout data) {
//                new AuthSession(getContext()).logout();
//                OnNpsApiResult.onSuccess(data);
//            }
//
//            @Override
//            public void onSessionExpired(String message) {
//                OnNpsApiResult.onSessionExpired(message);
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                OnNpsApiResult.onError(throwable);
//            }
//        });
    }
