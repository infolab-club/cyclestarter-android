package com.infolab.ecohack;

import android.content.Context;
import android.content.SharedPreferences;

public class RegistrationController {

    public final static String IS_REGISTERED_BEFORE = "REGISTERED_BEFORE", IS_FIRST_VISIT = "FIRST_VISIT",
        SHARED_PREFERENCES = "ECO_HACK";
    private static RegistrationController registrationController;
    private SharedPreferences sharedPreferences;

    private RegistrationController(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static RegistrationController getInstance(Context context) {
        if (registrationController == null)
            registrationController = new RegistrationController(context);
        return registrationController;
    }





    public void registrate() {
        sharedPreferences.edit()
                .putBoolean(IS_REGISTERED_BEFORE, true)
                .apply();
    }
}
