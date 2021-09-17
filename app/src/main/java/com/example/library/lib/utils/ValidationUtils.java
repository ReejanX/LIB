package com.example.library.lib.utils;

import android.text.TextUtils;
import android.util.Patterns;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public final class ValidationUtils {
    private static final String TAG = "ValidationUtils";

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean checkLoginUserName(String username) {
        return checkNumber(username) ? checkMobileNumber(username) : isValidEmail(username);
    }

    private static boolean checkNumber(String number) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (number == null) {
            return false;
        }
        return pattern.matcher(number).matches();
    }

    public static boolean checkUserPassword(String password) {
        final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\\\$%\\\\^&\\\\*])(?=.{8,16}$)";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        if (password == null) {
            return false;
        }
        return pattern.matcher(password).matches();
    }

    public static boolean checkMobileNumber(String number) {
        return number.length() == 10;
    }

    public static boolean checkGender(String gender) {
        return !gender.isEmpty();
    }

    public static boolean checkFullName(String fullName) {
        String[] fName = fullName.split(" ");
        return fName.length > 1;
    }

    public static boolean checkPin(String pin) {
        return pin.length() == 6;
    }

    public static boolean checkDateExpiry(String issue, String expiry) {
        Date expiryDate = null;
        Date issueDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            expiryDate = sdf.parse(expiry);
            issueDate = sdf.parse(issue);
            return expiryDate.after(issueDate);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean isValidDOB(String dobStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date dob = sdf.parse(dobStr);

            Calendar birthDay = Calendar.getInstance();
            birthDay.setTimeInMillis(dob.getTime());
            long currentTime = System.currentTimeMillis();
            Calendar now = Calendar.getInstance();
            now.setTimeInMillis(currentTime);
            int years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
            return years > 17;
        } catch (Exception e) {
            return false;
        }

    }

    public static boolean checkIssueDate(String issueDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = sdf.parse(issueDate);
            Date currentDate = new Date();
            return date.before(currentDate);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidFormat(String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            date = sdf.parse(value);
            if (value.equals(sdf.format(date))) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    public static boolean isValidDishHomeSubId(String dishhome_customer_id) {
        return dishhome_customer_id.length() > 4;
    }

    public static boolean isValidSmartCard(String smartCard) {
        int len = smartCard.length();
        return len >= 10 && len <= 11;
    }
}