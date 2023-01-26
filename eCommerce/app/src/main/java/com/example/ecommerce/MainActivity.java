package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.ecommerce.Model.User;
import com.example.ecommerce.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;


public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private DatabaseReference rootRef;
    private static  String parentNode = "users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Paper.init(this);
        progressDialog = new ProgressDialog(this);
        rootRef = FirebaseDatabase.getInstance().getReference();

        String phone = Paper.book().read(Prevalent.UserPhoneKey);
        String password = Paper.book().read(Prevalent.UserPasswordKey);
        String status = Paper.book().read(Prevalent.UserStatus);

        parentNode = status;
        Toast.makeText(this, parentNode, Toast.LENGTH_SHORT).show();

        if(!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password)){
            allowAccess(phone,password);
        }
    }

    private void allowAccess(final String phone, final String password)
    {

        progressDialog.setTitle("Already Login");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(parentNode).child(phone).exists()){

                    User user = dataSnapshot.child(parentNode).child(phone).getValue(User.class);
                    if(user.getPassword().equals(password)){
                        if(parentNode.equals("admins")){
                            progressDialog.dismiss();
                            sendUserToAdminActivity();
                            Toast.makeText(MainActivity.this, "Login Successfully.", Toast.LENGTH_SHORT).show();

                        }

                        else if (parentNode.equals("users")){
                            progressDialog.dismiss();
                            sendUserToHomeActivity();
                            Toast.makeText(MainActivity.this, "Login Successfully.", Toast.LENGTH_SHORT).show();


                        }
                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Password is incorrect!", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Account does not exit with this phone number.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void sendUserToHomeActivity() {
        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void sendUserToAdminActivity() {
        Intent intent = new Intent(MainActivity.this,AdminCategoryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void sendUserToLoginActivity(View v){
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    public void sendUserToRegisterActivity(View v){
        Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
}
