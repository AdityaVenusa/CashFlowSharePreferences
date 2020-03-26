package com.example.cashflowsharedpreferences;

import com.example.cashflowsharedpreferences.models.Account;
import com.example.cashflowsharedpreferences.models.Session;

public class Application extends android.app.Application {

    private static Account account;
    private static String format;
    private static Session session;

    @Override
    public void onCreate() {
        super.onCreate();
        account = new Account("Aditya Venusa");
        session = new Session(this);
    }

    public static Account getAccount() {
        return account;
    }
    public static Session getSession() {
        return session;
    }

    }
