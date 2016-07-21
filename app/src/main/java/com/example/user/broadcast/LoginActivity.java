package com.example.user.broadcast;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by user on 2016/4/13.
 */
public class LoginActivity extends BaseActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox rememberPass;
    private TextView register;
    private TextView fogotPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        rememberPass = (CheckBox) findViewById(R.id.remember_pass);
        login = (Button) findViewById(R.id.login);
        register = (TextView) findViewById(R.id.register);
        fogotPass = (TextView) findViewById(R.id.foget_pass);
        String text1 = "      Register";
        String text2 = "Fogot password      ";
        SpannableString string1 = new SpannableString(text1);
        string1.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        }, 0, text1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        string1.setSpan(new ForegroundColorSpan(Color.CYAN), 0, text1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        register.append(string1);
        register.setMovementMethod(LinkMovementMethod.getInstance());

        SpannableString string2 = new SpannableString(text2);
        string2.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        }, 0, text2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        string2.setSpan(new ForegroundColorSpan(Color.CYAN), 0, text2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        fogotPass.append(string2);
        fogotPass.setMovementMethod(LinkMovementMethod.getInstance());

        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember) {
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if (account.equals("admin") && password.equals("123456")) {
                    editor = pref.edit();
                    if (rememberPass.isChecked()) {
                        editor.putBoolean("remember_password", true);
                        editor.putString("account", account);
                        editor.putString("password", password);
                    } else {
                        editor.clear();
                    }
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "account or password is invalid",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
