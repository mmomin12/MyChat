package com.zaeem.mychat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.name;
import static android.R.attr.password;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private TextInputLayout nname;
    private TextInputLayout nemail;
    private TextInputLayout npassword;
    private TextInputLayout nPhone;


    @OnClick(R.id.Eegister_submit_bt)
    public void submit(View v) {
        nemail = (TextInputLayout) findViewById(R.id.Register_email_text);
        String email = nemail.getEditText().getText().toString();
        npassword = (TextInputLayout) findViewById(R.id.Register_password_text);
        String password = npassword.getEditText().getText().toString();
        nPhone = (TextInputLayout) findViewById(R.id.Register_Phone_text);
        String phone = nPhone.getEditText().getText().toString();
        nname = (TextInputLayout) findViewById(R.id.Register_name_text);
        String name = nname.getEditText().getText().toString();

        registeruser( email, password);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

    }


    private void registeruser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, "Try again Please", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
