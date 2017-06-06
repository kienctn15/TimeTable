package com.example.falcon.timetable.Login_Register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.falcon.timetable.DBHandler.DBHandler;
import com.example.falcon.timetable.R;

public class RegisterActivity extends AppCompatActivity {

    EditText edt_register_username,edt_register_password;
    TextView tv_register_login;
    Button btn_register;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DBHandler(getApplicationContext());

        edt_register_username = (EditText) findViewById(R.id.edt_register_username);
        edt_register_password = (EditText) findViewById(R.id.edt_register_password);
        btn_register  = (Button) findViewById(R.id.btn_register);
        tv_register_login = (TextView) findViewById(R.id.tv_register_login);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserRegister();

            }
        });
        tv_register_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void UserRegister() {

        String u = edt_register_username.getText().toString().trim();
        String p = edt_register_password.getText().toString().trim();

        if(u.length()>0 && p.length() >0){
            if(db.check_register(u)){
                User user = new User();
                user.setUsername(u);
                user.setPassword(p);
                db.insert_table_user(user);

                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);

            }else{
                Toast.makeText(this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Hãy nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }

    }
}
