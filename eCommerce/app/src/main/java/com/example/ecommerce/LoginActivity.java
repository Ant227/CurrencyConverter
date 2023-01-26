package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerce.Model.User;
import com.example.ecommerce.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    private EditText phoneNumber , password;
    private CheckBox rememberMe;
    private TextView forgetPassword, notAdminLink , adminLink;
    private Button loginBtn;

    private ProgressDialog progressDialog;
    private DatabaseReference rootRef;

    private  String parentNode = "users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phoneNumber = findViewById(R.id.login_phone_number);
        password = findViewById(R.id.login_password);
        rememberMe = findViewById(R.id.login_remember_me);
        forgetPassword = findViewById(R.id.login_forget_password);
        notAdminLink = findViewById(R.id.not_admin_panel_link);
        adminLink = findViewById(R.id.admin_panel_link);
        loginBtn = findViewById(R.id.login_login_button);

        rootRef = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(this);
        Paper.init(this);

        adminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginBtn.setText("Login Admin");
                loginBtn.setAllCaps(false);
                adminLink.setVisibility(View.INVISIBLE);
                notAdminLink.setVisibility(View.VISIBLE);
                parentNode = "admins";
            }
        });
        notAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginBtn.setText("Login");
                loginBtn.setAllCaps(false);
                adminLink.setVisibility(View.VISIBLE);
                notAdminLink.setVisibility(View.INVISIBLE);
                parentNode = "users";
            }
        });

    }

    public void loginAccount(View view){

        final String phoneNumberString = phoneNumber.getText().toString();
        final String passwordString = password.getText().toString();

        if(TextUtils.isEmpty(phoneNumberString) || TextUtils.isEmpty(passwordString)){
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
        }else{
            progressDialog.setTitle("Logging In");
            progressDialog.setMessage("Please wait...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(parentNode).child(phoneNumberString).exists()){

                        User user = dataSnapshot.child(parentNode).child(phoneNumberString).getValue(User.class);
                        if(user.getPassword().equals(passwordString)){

                            if(parentNode.equals("admins")){
                                progressDialog.dismiss();
                                sendUserToAdminActivity();
                                Toast.makeText(LoginActivity.this, "Login Successfully.", Toast.LENGTH_SHORT).show();
                                if(rememberMe.isChecked()){
                                    Paper.book().write(Prevalent.UserPhoneKey,phoneNumberString);
                                    Paper.book().write(Prevalent.UserPasswordKey,passwordString);
                                    Paper.book().write(Prevalent.UserStatus,"admins");
                                }
                            }

                            else if (parentNode.equals("users")){
                                progressDialog.dismiss();
                                sendUserToHomeActivity();
                                Toast.makeText(LoginActivity.this, "Login Successfully.", Toast.LENGTH_SHORT).show();

                                if(rememberMe.isChecked()){
                                    Paper.book().write(Prevalent.UserPhoneKey,phoneNumberString);
                                    Paper.book().write(Prevalent.UserPasswordKey,passwordString);
                                    Paper.book().write(Prevalent.UserStatus,"users");
                                }
                            }




                        }
                        else{
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Password is incorrect!", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Account does not exit with this phone number.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }


    }

    private void sendUserToAdminActivity() {
        Intent intent = new Intent(LoginActivity.this,AdminCategoryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void sendUserToHomeActivity() {
        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
