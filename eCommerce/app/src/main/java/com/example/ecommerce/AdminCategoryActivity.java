package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminCategoryActivity extends AppCompatActivity {

    private ImageView tshirt, sportTshirt, femaleDresses, sweathers, glasses, purses, hats, shoes, headphones, laptops, watches, mobilephones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        tshirt = findViewById(R.id.t_shirt);
        sportTshirt = findViewById(R.id.sport_Tshirt);
        femaleDresses = findViewById(R.id.female_dress);

        sweathers = findViewById(R.id.sweather);
        purses = findViewById(R.id.bags);
        hats = findViewById(R.id.hats);

        glasses = findViewById(R.id.glasses);
        shoes = findViewById(R.id.shoes);
        watches = findViewById(R.id.watch);

        headphones = findViewById(R.id.headphone);
        laptops = findViewById(R.id.laptop);
        mobilephones = findViewById(R.id.smartphone);

    }

    public void categoryTshirt(View view){
        Intent intent = new Intent(AdminCategoryActivity.this,AdminActivity.class);
        intent.putExtra("category","tShirt");
        startActivity(intent);
    }
    public void categorySportTshirt(View view){
        Intent intent = new Intent(AdminCategoryActivity.this,AdminActivity.class);
        intent.putExtra("category","sportTShirt");
        startActivity(intent);
    }
    public void categoryFemaleDresses(View view){
        Intent intent = new Intent(AdminCategoryActivity.this,AdminActivity.class);
        intent.putExtra("category","femaleDress");
        startActivity(intent);
    }

    public void categorySweather(View view){
        Intent intent = new Intent(AdminCategoryActivity.this,AdminActivity.class);
        intent.putExtra("category","sweather");
        startActivity(intent);
    }
    public void categoryPurses(View view){
        Intent intent = new Intent(AdminCategoryActivity.this,AdminActivity.class);
        intent.putExtra("category","Purses");
        startActivity(intent);
    }
    public void categoryHats(View view){
        Intent intent = new Intent(AdminCategoryActivity.this,AdminActivity.class);
        intent.putExtra("category","hat");
        startActivity(intent);
    }


    public void categoryGlasses(View view){
        Intent intent = new Intent(AdminCategoryActivity.this,AdminActivity.class);
        intent.putExtra("category","glasses");
        startActivity(intent);
    }
    public void categoryShoes(View view){
        Intent intent = new Intent(AdminCategoryActivity.this,AdminActivity.class);
        intent.putExtra("category","shoes");
        startActivity(intent);
    }
    public void categoryWatches(View view){
        Intent intent = new Intent(AdminCategoryActivity.this,AdminActivity.class);
        intent.putExtra("category","watch");
        startActivity(intent);
    }


    public void categoryHeadphones(View view){
        Intent intent = new Intent(AdminCategoryActivity.this,AdminActivity.class);
        intent.putExtra("category","headphones");
        startActivity(intent);
    }
    public void categoryLaptop(View view){
        Intent intent = new Intent(AdminCategoryActivity.this,AdminActivity.class);
        intent.putExtra("category","laptop");
        startActivity(intent);
    }
    public void categoryMobilePhones(View view){
        Intent intent = new Intent(AdminCategoryActivity.this,AdminActivity.class);
        intent.putExtra("category","mobilephone");
        startActivity(intent);
    }
}
