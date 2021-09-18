package moc.spn.sbil.lellaw.session;

import android.content.Context;

import moc.spn.sbil.lellaw.abstractclasses.NpsMemory;
import moc.spn.sbil.lellaw.kyc.model.KycState;
import moc.spn.sbil.lellaw.userdetail.UserDetail;

import static moc.spn.sbil.lellaw.utils.Constants.KEY_AGENT_ID;
import static moc.spn.sbil.lellaw.utils.Constants.KEY_BALANCE;
import static moc.spn.sbil.lellaw.utils.Constants.KEY_COMMISSION;
import static moc.spn.sbil.lellaw.utils.Constants.KEY_EMAIL_ID;
import static moc.spn.sbil.lellaw.utils.Constants.KEY_ID;
import static moc.spn.sbil.lellaw.utils.Constants.KEY_KYC;
import static moc.spn.sbil.lellaw.utils.Constants.KEY_LOGO;
import static moc.spn.sbil.lellaw.utils.Constants.KEY_MOBILE;
import static moc.spn.sbil.lellaw.utils.Constants.KEY_NAME;
import static moc.spn.sbil.lellaw.utils.Constants.KEY_REWARD;

public class UserSession extends NpsMemory {
    public UserSession(Context context) {
        super(context);
    }

    public void setUserDetail(UserDetail.Data userDetail) {
        putString(KEY_ID, userDetail.user_id);
        putString(KEY_NAME, userDetail.user_full_name);
        putString(KEY_AGENT_ID, userDetail.user_agent_id);
        putString(KEY_EMAIL_ID, userDetail.user_email_id);
        putString(KEY_MOBILE, userDetail.user_mobile_no);
        putString(KEY_LOGO, userDetail.Identification_photo_Logo);
        putString(KEY_KYC, userDetail.KYC_Verified);
        putString(KEY_BALANCE, userDetail.available_balance);
        putString(KEY_REWARD, userDetail.reward_point);
        putString(KEY_COMMISSION, userDetail.commission);
    }
    public void setKycState(KycState kycState){
        putString(KEY_KYC,kycState.toString());
    }
    public void setUserBalance(String amount){
        putString(KEY_BALANCE, amount);
    }
    public String getId(){
        return getString(KEY_ID);
    }

    public String getUserName(){
        return getString(KEY_NAME);
    }

    public String getAgentId(){
        return getString(KEY_AGENT_ID);
    }
    public String getEmailID(){
        return getString(KEY_EMAIL_ID);
    }
    public String getMobileNo(){
        return getString(KEY_MOBILE);
    }
    public String getUserImageUrl(){
        return getString(KEY_LOGO);
    }
    public KycState getKycState(){
        return KycState.getKycState(getString(KEY_KYC));
    }
    public String getUserBalance(){
        return "Rs. "+getString(KEY_BALANCE);
    }
    public String getUserReward(){
        return "+ "+getString(KEY_REWARD);
    }
    public String getUserCommission(){
        return getString(KEY_COMMISSION);
    }
    @Override
    protected String getName() {
        return "noissesresu";
    }
}
