package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    //4 editext 1 button
    private EditText userName, phoneNumber, password, confirmPassword;
    private DatabaseReference rootRef;
    private Boolean validatePhone = false;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = findViewById(R.id.register_name);
        phoneNumber =findViewById(R.id.register_phone_number);
        password = findViewById(R.id.register_password);
        confirmPassword = findViewById(R.id.register_confirm_password);

        rootRef = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(RegisterActivity.this);

    }

    public void createAccount(View view){

        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("Please Wait........");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        //geting Value
        String nameString = userName.getText().toString();
        final String phoneNumberString = phoneNumber.getText().toString();
        String passwordString = password.getText().toString();
        String confirmPasswordString = confirmPassword.getText().toString();

        if(TextUtils.isEmpty(nameString) ||
                TextUtils.isEmpty(phoneNumberString) ||
                        TextUtils.isEmpty(passwordString) ||
                                TextUtils.isEmpty(confirmPasswordString)){
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();

        }
        else
        {
            Boolean isValidatePhoneNumber = validatePhoneNumber(phoneNumberString);
            if(isValidatePhoneNumber) {
                if (passwordString.equals(confirmPasswordString)) {
                    HashMap<String, Object> userDataMap = new HashMap<>();
                    userDataMap.put("name", nameString);
                    userDataMap.put("phone", phoneNumberString);
                    userDataMap.put("password", passwordString);

                    rootRef.child("users").child(phoneNumberString).updateChildren(userDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        progressDialog.dismiss();
                                        Toast.makeText(RegisterActivity.this, "Congratulation! Account has been created, please Login in.", Toast.LENGTH_LONG).show();
                                        sendUserToLoginActivity();
                                    } else {
                                        progressDialog.dismiss();
                                        Toast.makeText(RegisterActivity.this, "Error : " + task.getException(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                } else {

                    progressDialog.dismiss();
                    Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child("users").child(phoneNumberString).exists()){
                            sendUserToLoginActivity();
                            Toast.makeText(RegisterActivity.this, "Account with this phone number  already exists", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
                progressDialog.dismiss();

            }
        }


    }

    private void sendUserToLoginActivity() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private Boolean validatePhoneNumber(final String phoneNumberString)
    {

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.child("users").child(phoneNumberString).exists()){
                    validatePhone = true;
                }
                else{
                    validatePhone = false;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return validatePhone;
    }
}
