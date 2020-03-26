package com.example.cashflowsharedpreferences;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

public class FragmentActivity extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

}
