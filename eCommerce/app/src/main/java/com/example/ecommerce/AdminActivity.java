package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

import io.paperdb.Paper;

public class AdminActivity extends AppCompatActivity {

    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        category = getIntent().getExtras().get("category").toString();
        Toast.makeText(this, category, Toast.LENGTH_SHORT).show();
    }

    public void logoutAdmin(View view) {
        sendUserToLoginActivity();
    }

    private void sendUserToLoginActivity() {
        Paper.book().destroy();
        Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
