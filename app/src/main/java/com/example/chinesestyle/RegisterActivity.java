package com.example.chinesestyle;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import com.example.chinesestyle.helpers.DatabaseHelper;
import com.example.chinesestyle.helpers.SharedPrefsManager;

public class RegisterActivity extends AppCompatActivity {
    private EditText etNickname, etContact, etPassword;
    private RadioGroup rgGender;
    private Button btnRegister;
    private TextView tvLoginLink;
    private DatabaseHelper dbHelper;
    private SharedPrefsManager prefsManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DatabaseHelper(this);
        prefsManager = SharedPrefsManager.getInstance(this);

        etNickname = this.findViewById(R.id.etNickname);
        etContact = this.findViewById(R.id.etContact);
        etPassword = this.findViewById(R.id.etPassword);
        rgGender = this.findViewById(R.id.rgGender);
        btnRegister = this.findViewById(R.id.btnRegister);
        tvLoginLink = this.findViewById(R.id.tvLoginLink);

        btnRegister.setOnClickListener(v -> {
            String nickname = etNickname.getText().toString().trim();
            String contact = etContact.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String gender = rgGender.getCheckedRadioButtonId() == R.id.rbMale ? "男" : "女";

            if (nickname.isEmpty() || contact.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "请填写所有字段", Toast.LENGTH_SHORT).show();
                return;
            }

            long userId = dbHelper.registerUser(nickname, gender, contact, password);
            if (userId != -1) {
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                prefsManager.setLoggedIn(true);
                prefsManager.setUserId(userId);
                prefsManager.setNickname(nickname);
                this.startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                this.finish();
            } else {
                Toast.makeText(this, "注册失败，昵称或联系方式已存在", Toast.LENGTH_SHORT).show();
            }
        });

        tvLoginLink.setOnClickListener(v -> {
            this.startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            this.finish();
        });
    }
}
