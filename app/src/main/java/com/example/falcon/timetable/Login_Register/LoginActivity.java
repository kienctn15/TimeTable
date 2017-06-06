package com.example.falcon.timetable.Login_Register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.falcon.timetable.DBHandler.DBHandler;
import com.example.falcon.timetable.MainActivity;
import com.example.falcon.timetable.R;

public class LoginActivity extends AppCompatActivity {

    EditText edt_login_username, edt_login_password;
    TextView tv_login_register;
    Button btn_login;
    private ProgressDialog progressDialog;
    DBHandler db;
    SharedPreferences.Editor editor;
    UserSessionManager userSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        db = new DBHandler(getApplicationContext());
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        edt_login_username = (EditText) findViewById(R.id.edt_login_username);
        edt_login_password = (EditText) findViewById(R.id.edt_login_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        tv_login_register = (TextView) findViewById(R.id.tv_login_register);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        tv_login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void userLogin() {
        String u = edt_login_username.getText().toString().trim();
        String p = edt_login_password.getText().toString().trim();

        if(u.length()>0 && p.length()>0) {
            ShowDialog("Đang đăng nhập ...");
            if(db.check_login(u,p)){
                CreateUserLoginSeassion(u,p);
                HideDialog();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }else{
                HideDialog();
                Toast.makeText(this, "Sai thông tin đăng nhập !", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Hãy điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }

    }
    public void CreateUserLoginSeassion(String user, String password) {
        editor = getSharedPreferences(userSessionManager.PREFER_NAME, userSessionManager.PRIVATE_MODE).edit();
        editor.putBoolean(userSessionManager.IS_USER_LOGIN, true);
        editor.putString(userSessionManager.KEY_USERNAME, user);
        editor.putString(userSessionManager.KEY_PASSWORD, password);

        editor.commit();
    }

    public void ShowDialog(String message) {
        if (!progressDialog.isShowing()) {
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }

    public void HideDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
