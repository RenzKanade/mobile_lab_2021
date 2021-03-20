package com.example.uts_31720;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    ImageView appLogo;
    EditText Iusername,Ipassword;
    Button submitInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        appLogo = findViewById(R.id.AppLogo2);
        Iusername = findViewById(R.id.EditUsername);
        Ipassword = findViewById(R.id.EditPass);
        submitInfo = findViewById(R.id.btnLogin);
        appLogo.setBackgroundResource(R.drawable.applogo);

        Iusername.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN)
                        && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    Iusername.clearFocus();
                    Ipassword.requestFocus();
                    return true;
                }
                return false;
            }
        });

        Ipassword.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN)
                        && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    Ipassword.clearFocus();
                    submitInfo.requestFocus();
                    hideKeypad();
                    return true;
                }
                return false;
            }
        });

        submitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Iusername.getText().toString();
                String password = Ipassword.getText().toString();
                String errIncomplete = "Please fill in username and password";
                String errWrong = "Incorrect Username / Password";
                if(username.isEmpty() ==false|| password.isEmpty()==false){
                    if(username.equals("uasmobile")){
                        if(password.equals("uasmobilegenap")){
                            Intent libraryIntent = new Intent(LoginActivity.this,SongLibrary.class);
                            startActivityForResult(libraryIntent,1);
                        } else{
                            Toast.makeText(v.getContext(),errWrong,Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(v.getContext(),errWrong,Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(v.getContext(),errIncomplete,Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void hideKeypad() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(Ipassword.getWindowToken(), 0);
    }
}