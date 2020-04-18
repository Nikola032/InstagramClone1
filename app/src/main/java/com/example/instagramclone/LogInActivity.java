package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmailLogIn, edtPasswordLogIn;
    private Button btnSignUpLogIn, btnLogInLogIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        setTitle("Log In");

        edtEmailLogIn = findViewById(R.id.edtEmailLogIn);
        edtPasswordLogIn = findViewById(R.id.edtPasswordLogIn);

        btnLogInLogIn = findViewById(R.id.btnLogInLogIn);
        btnSignUpLogIn = findViewById(R.id.btnSignUpLogIn);

        btnSignUpLogIn.setOnClickListener(this);
        btnLogInLogIn.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null){
            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogInLogIn:
                if (edtEmailLogIn.getText().toString().equals("") || edtPasswordLogIn.getText().toString().equals("")){
                    FancyToast.makeText
                            (LogInActivity.this,"Email, Password is requierd!",
                                    FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();
                }
                ParseUser.logInInBackground(edtEmailLogIn.getText().toString(),
                        edtPasswordLogIn.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if ( user != null && e == null) {
                            FancyToast.makeText(LogInActivity.this,user.getUsername()
                                    + " is loged in",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            transitionToSocialMediaActivity();
                        }else {
                            FancyToast.makeText(LogInActivity.this,e.getMessage(),FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();

                        }

                    }
                });

                break;
            case R.id.btnSignUpLogIn:
                setContentView(R.layout.activity_main);
                break;

        }

    }
    public void loginLayoutTapped(View View) {
        try {
            InputMethodManager inputMethodManager =  (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        } catch (Exception e) {
            e.printStackTrace();}

    }
    private void transitionToSocialMediaActivity() {
        Intent intent = new Intent(LogInActivity.this, SocialMediaActivity.class);
        startActivity(intent);
    }
}
