package com.example.chinesestyle;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.widget.CheckBox;
import androidx.appcompat.app.AppCompatActivity;
import com.example.chinesestyle.helpers.DatabaseHelper;
import com.example.chinesestyle.helpers.SharedPrefsManager;

public class LoginActivity extends AppCompatActivity {
    private EditText etLoginId, etPassword;
    private Button btnLogin;
    private TextView tvRegisterLink;
    private DatabaseHelper dbHelper;
    private SharedPrefsManager prefsManager;
    private CheckBox cbRememberPassword, cbAutoLogin;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DatabaseHelper(this);
        prefsManager = SharedPrefsManager.getInstance(this);

        etLoginId = this.findViewById(R.id.etLoginId);
        etPassword = this.findViewById(R.id.etPassword);
        btnLogin = this.findViewById(R.id.btnLogin);
        tvRegisterLink = this.findViewById(R.id.tvRegisterLink);

        cbRememberPassword = findViewById(R.id.cbRememberPassword);
        cbAutoLogin = findViewById(R.id.cbAutoLogin);

        if (prefsManager.isRememberPassword()) {
            String savedLoginId = prefsManager.getLoginId();
            String savedPassword = prefsManager.getPassword();
            etLoginId.setText(savedLoginId);
            etPassword.setText(savedPassword);
            cbRememberPassword.setChecked(true);
        }

        if (prefsManager.isAutoLogin()) {
            cbAutoLogin.setChecked(true);
            // Perform login operation
        }

        btnLogin.setOnClickListener(v -> {
            String loginId = etLoginId.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (loginId.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "请输入登录信息和密码", Toast.LENGTH_SHORT).show();
                return;
            }

            if (dbHelper.loginUser(loginId, password)) {
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                prefsManager.setRememberPassword(cbRememberPassword.isChecked());
                if (cbRememberPassword.isChecked()) {
                    prefsManager.setLoginId(loginId);
                    prefsManager.setPassword(password);
                } else {
                    prefsManager.setLoginId("");
                    prefsManager.setPassword("");
                }
                this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
                this.finish();
            } else {
                Toast.makeText(this, "登录失败，请检查登录信息和密码", Toast.LENGTH_SHORT).show();
            }
        });

        tvRegisterLink.setOnClickListener(v -> {
            this.startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            this.finish();
        });
    }
}
