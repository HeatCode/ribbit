package com.heatcode.ribbit;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.EmptyStackException;

public class SignUpActivity extends AppCompatActivity {

    protected EditText mUsername;
    protected EditText mPassword;
    protected EditText mEmail;
    protected Button mSignUpButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mUsername = (EditText)findViewById(R.id.usernameField);
        mPassword = (EditText)findViewById(R.id.passwordField);
        mEmail = (EditText)findViewById(R.id.emailField);
        mSignUpButton = (Button)findViewById(R.id.signUpButton);


        mSignUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String username =  mUsername.getText().toString();
                String password =  mPassword.getText().toString();
                String email =  mEmail.getText().toString();

                //Next trim and spaces accidentally added by the user
                username = username.trim();
                password = password.trim();
                email = email.trim();


                //if statement to check for empty
                if (username.isEmpty() || password.isEmpty() || email.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    builder.setMessage(R.string.sign_error_message)
                        .setTitle(R.string.sign_error_title)
                        .setPositiveButton(android.R.string.ok,null);
                        //linked builders together by removing builder from the settitle and setpositivebutton

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {//create the new user in the else block

                    ParseUser newUser = new ParseUser();
                    newUser.setUsername(username);
                    newUser.setPassword(password);
                    newUser.setEmail(email);
                    newUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {

                                //Success!
                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                builder.setMessage(e.getMessage())
                                        .setTitle(R.string.sign_error_title)
                                        .setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }

                        }
                    });

                }


            }//end of onClick

        });//mSignUpButton OnClickListener

    }//end of onCreate

//    @Override
//    public boolean onCreateOptionMenu(Menu menu){
//        //Inflate the menu; this add items to the actionbar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main,menu);
//        return true;
//}

}//end of SignUpActivity
