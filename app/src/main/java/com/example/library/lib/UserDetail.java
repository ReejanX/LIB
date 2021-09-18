package moc.spn.sbil.lellaw.userdetail;

import java.util.List;

import moc.spn.sbil.lellaw.abstractclasses.NpsModel;
import moc.spn.sbil.lellaw.abstractclasses.ResponseData;

public class UserDetail extends NpsModel {
    public List<Data> data;

    public static class Data extends ResponseData {
        public String user_id;
        public String user_full_name;
        public String user_agent_id;
        public String user_email_id;
        public String user_mobile_no;
        public String user_status;
        public String is_user_login_enabled;
        public String Identification_photo_Logo;
        public String KYC_Verified;
        public String available_balance;
        public String reward_point;
        public String commission;
    }
}
