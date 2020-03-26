package com.example.cashflowsharedpreferences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cashflowsharedpreferences.models.Session;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameInput;
    private EditText passwordInput;
    private Session session;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        session = Application.getSession();

        usernameInput = findViewById(R.id.username);
        passwordInput = findViewById(R.id.pasword);

        if (session.isKeepUsername()) {
            usernameInput.setText(session.getUsername());
        }
    }

    public void resetHanlde(View view) {
        usernameInput.setText("");
        passwordInput.setText("");
    }

    public void LoginHandle(View view) {
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        boolean success = session.validate(username, password);
        if (success) {
            session.setUsername(username);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Snackbar.make(view, "Authentication Failed", Snackbar.LENGTH_SHORT).show();
        }
    }


}
