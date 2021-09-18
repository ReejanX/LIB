package moc.spn.sbil.lellaw.auth;

import android.app.Application;

import androidx.annotation.NonNull;

import moc.spn.sbil.lellaw.abstractclasses.NpsViewModel;
import moc.spn.sbil.lellaw.api.OnNpsApiResult;
import moc.spn.sbil.lellaw.auth.model.Logout;
import moc.spn.sbil.lellaw.auth.model.Resend;
import moc.spn.sbil.lellaw.auth.model.Reset;
import moc.spn.sbil.lellaw.auth.model.SetPassword;
import moc.spn.sbil.lellaw.auth.model.User;
import moc.spn.sbil.lellaw.change.model.ChangePassword;
import moc.spn.sbil.lellaw.change.model.ChangePin;
import moc.spn.sbil.lellaw.utils.WalletLayout;

public class UserViewModel extends NpsViewModel {

    private final UserRepoImpl userRepoImpl;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepoImpl = new UserRepoImpl(application.getApplicationContext());
    }

    /**
     * user login auth
     *
     * @param usernameLayout
     * @param passwordLayout
     * @param <T>
     */
    public <T extends WalletLayout> void setUsernameAndPassword(T usernameLayout, T passwordLayout) {
        userRepoImpl.setUsernameAndPasswordTextField(usernameLayout, passwordLayout);
    }

    public Boolean isValidAuth() {
        return userRepoImpl.isValid();
    }

    public void login(OnNpsApiResult<User> apiResult) {
        userRepoImpl.login(apiResult);
    }


    /**
     * reset password
     *
     * @param usernameLayout
     * @param <T>
     */
    public <T extends WalletLayout> void setResetPassword(T usernameLayout) {
        userRepoImpl.setResetPassword(usernameLayout);
    }

    public Boolean isValidResetPassword() {
        return userRepoImpl.isValid();
    }

    public void resetPassword(OnNpsApiResult<Reset> apiResult) {
        userRepoImpl.resetPassword(apiResult);
    }


    /**
     * verify code
     *
     * @return
     */
    public Boolean isValidVerifyCode() {
        return userRepoImpl.isValid();
    }

    public void verifyCode(String username, String code, OnNpsApiResult<Reset> apiResult) {
        userRepoImpl.verifyCode(username, code, apiResult);
    }


    /**
     * set password
     *
     * @param newPassword
     * @param confirmPassword
     * @param <T>
     */
    public <T extends WalletLayout> void setPassword(T newPassword, T confirmPassword) {
        userRepoImpl.setPasswordTextField(newPassword, confirmPassword);
    }

    public Boolean isValidPassword() {
        return userRepoImpl.isValid();
    }

    public void password(String username, OnNpsApiResult<SetPassword> apiResult) {
        userRepoImpl.setPassword(username, apiResult);
    }

    /**
     * resendOTP
     *
     * @param id
     * @param newPassword
     * @param confirmPassword
     * @param apiResult
     */
    public void setVerificationPassword(String id, String newPassword, String confirmPassword, OnNpsApiResult<SetPassword> apiResult) {
        userRepoImpl.userSetVerifiedPassword(id, newPassword, confirmPassword, apiResult);
    }

    public Boolean isValidResendOtp() {
        return userRepoImpl.isValid();
    }

    public void resendOtp(String username, OnNpsApiResult<Resend> apiResult) {
        userRepoImpl.resendOtp(username, apiResult);
    }

    /**
     * set forceful change login password
     *
     * @param oldPasswordLayout
     * @param newPasswordLayout
     * @param confirmPasswordLayout
     * @param <T>
     */
    public <T extends WalletLayout> void setChangeLoginPassword(T oldPasswordLayout, T newPasswordLayout, T confirmPasswordLayout) {
        userRepoImpl.setLoginChangePasswordTextField(oldPasswordLayout, newPasswordLayout, confirmPasswordLayout);
    }

    public Boolean isValidChangeLoginPassword() {
        return userRepoImpl.isValid();
    }

    public void changeLoginPassword(String username, OnNpsApiResult<ChangePassword> apiResult) {
        userRepoImpl.changeLoginPassword(username, apiResult);
    }


    /**
     * set forceful change login pin
     *
     * @param oldPinLayout
     * @param newPinLayout
     * @param confirmPinLayout
     * @param <T>
     */
    public <T extends WalletLayout> void setChangeLoginPin(T oldPinLayout, T newPinLayout, T confirmPinLayout) {
        userRepoImpl.setLoginChangePinTextField(oldPinLayout, newPinLayout, confirmPinLayout);
    }

    public Boolean isValidChangeLoginPin() {
        return userRepoImpl.isValid();
    }

    public void changeLoginPin(String username, OnNpsApiResult<ChangePin> apiResult) {
        userRepoImpl.changeLoginPin(username, apiResult);
    }


    /**
     * logout
     *
     * @param apiResult
     */
    public void logout(OnNpsApiResult<Logout> apiResult) {
        userRepoImpl.logout(apiResult);
    }
}