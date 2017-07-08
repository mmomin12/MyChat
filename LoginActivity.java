package com.zaeem.mychat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private TextInputLayout nemail;
    private TextInputLayout npassword;
    private Button nlogin;
    private Toolbar mtoolbar;

    private ProgressDialog mlogProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mtoolbar = (Toolbar) findViewById(R.id.LoginActivity_tool_bar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nemail = (TextInputLayout) findViewById(R.id.LoginActivity_Email_text);
        npassword = (TextInputLayout) findViewById(R.id.LoginActivity_password_text);
        nlogin = (Button) findViewById(R.id.LoginActivity_login_bt);

        mlogProgress = new ProgressDialog(this);
        nlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = nemail.getEditText().getText().toString();
                String password = npassword.getEditText().getText().toString();

                if(!TextUtils.isEmpty(email)|| !TextUtils.isEmpty(password)){
                    mlogProgress.setTitle("Signing In");
                    mlogProgress.setMessage("Please wait while signing in!");
                    mlogProgress.setCanceledOnTouchOutside(false);
                    mlogProgress.show();

                    loginuser(email,password);
                }

            }

        });
    }

    private void loginuser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            mlogProgress.dismiss();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();

                        }else{
                            mlogProgress.hide();
                            Toast.makeText(LoginActivity.this,"Try Again! Please check your username and password",Toast.LENGTH_LONG).show();
                        }

                        }

                });
    }

}
