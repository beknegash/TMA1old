package com.example.tma;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class ChangeLanguage
{
    private Context ct;
    public ChangeLanguage(Context ctx) {
        ct = ctx;
    }

    public void updateResource(String code)
    {
        Locale locale = new Locale(code);
        Locale.setDefault(locale);
        Resources resources = ct.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }
}
