package com.zaeem.mychat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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
    private Button button;

    private Toolbar mtoolbar;

    private ProgressDialog mregProgressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        mtoolbar = (Toolbar) findViewById(R.id.Register_page_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Create Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mregProgressDialog = new ProgressDialog(this);

        nname = (TextInputLayout) findViewById(R.id.Register_name_text);
        nemail = (TextInputLayout) findViewById(R.id.Register_email_text);
        npassword = (TextInputLayout) findViewById(R.id.Register_password_text);
        button = (Button) findViewById(R.id.Eegister_submit_bt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = nemail.getEditText().getText().toString();
                String password = npassword.getEditText().getText().toString();
                String name = nname.getEditText().getText().toString();

                if (!TextUtils.isEmpty(name) || !TextUtils.isEmpty(email)|| !TextUtils.isEmpty(password)) {
                    mregProgressDialog.setTitle("Registering");
                    mregProgressDialog.setMessage("Your Account is Creating. Please wait!");
                    mregProgressDialog.setCanceledOnTouchOutside(false);
                    mregProgressDialog.show();
                    registeruser(name, email, password);
                }
            }
        });


    }


    private void registeruser(String name, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    mregProgressDialog.dismiss();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    mregProgressDialog.hide();;
                    Toast.makeText(RegisterActivity.this, "Try again Please", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
