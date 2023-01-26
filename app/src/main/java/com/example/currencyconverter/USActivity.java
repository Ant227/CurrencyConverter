package com.example.currencyconverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class USActivity extends AppCompatActivity {


    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar mToolbar;

    private EditText MMRate , ChinaRate , BahtRate , DongRate , US_Value_Input;
    private Button MMConvertButton, ThaiConvertButton, ChinaConvertButton, VietnamConvertButton;

    private Dialog PopUpDialogResult;

    private TextView CountryName , ResultValue , RatingValue;
    private ImageView CountryFlag;
    private ImageButton CancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_us);




        Initialization();
        ToolbarAndNevigationDrawer();
        PopUpDialog();
        CurrencyExchangeCalculation();



    }

    private void CurrencyExchangeCalculation()
    {
        ConvertToMM();
        ConvertToBaht();
        ConvertToYuan();
        ConvertToDong();
    }

    private void ConvertToDong()
    {
        VietnamConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String DongRateString          = DongRate.getText().toString();
                String InputValueString      = US_Value_Input.getText().toString();

                if(TextUtils.isEmpty(InputValueString))
                {
                    Toast.makeText(USActivity.this, "Please Enter Input Value First", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(TextUtils.isEmpty(DongRateString))
                    {
                        Toast.makeText(USActivity.this, "Please Set Exchange Rate...", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        NumberFormat formatter = new DecimalFormat("#,###");

                        Float DongRateFloat         = Float.valueOf(DongRateString);
                        Float InputVlaueFloat     = Float.valueOf(InputValueString);

                        Float ResultValueFloat    = InputVlaueFloat * DongRateFloat;

                        String ResultValueString  = formatter.format(ResultValueFloat);

                        CountryName.setText("Dongs");
                        ResultValue.setText(ResultValueString + " ₫");
                        RatingValue.setText(DongRateString+ " ₫");
                        CountryFlag.setImageResource(R.drawable.vienam_flag);
                        PopUpDialogResult.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                        PopUpDialogResult.show();




                    }

                }


            }
        });
    }

    private void ConvertToYuan() {

        ChinaConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ChinaRateString          = ChinaRate.getText().toString();
                String InputValueString      = US_Value_Input.getText().toString();

                if(TextUtils.isEmpty(InputValueString))
                {
                    Toast.makeText(USActivity.this, "Please Enter Input Value First", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(TextUtils.isEmpty(ChinaRateString))
                    {
                        Toast.makeText(USActivity.this, "Please Set Exchange Rate...", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        NumberFormat formatter = new DecimalFormat("#,###");

                        Float ChinaRateFloat         = Float.valueOf(ChinaRateString);
                        Float InputVlaueFloat     = Float.valueOf(InputValueString);

                        Float ResultValueFloat    = InputVlaueFloat * ChinaRateFloat;

                        String ResultValueString  = formatter.format(ResultValueFloat);

                        CountryName.setText("Yuans");
                        ResultValue.setText(ResultValueString + " ￥");
                        RatingValue.setText(ChinaRateString+ " ￥");
                        CountryFlag.setImageResource(R.drawable.ic_flag_for_flag_china);
                        PopUpDialogResult.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                        PopUpDialogResult.show();




                    }

                }

            }
        });
    }

    private void ConvertToBaht() {

        ThaiConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String BahtRateString          = BahtRate.getText().toString();
                String InputValueString      = US_Value_Input.getText().toString();

                if(TextUtils.isEmpty(InputValueString))
                {
                    Toast.makeText(USActivity.this, "Please Enter Input Value First", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(TextUtils.isEmpty(BahtRateString))
                    {
                        Toast.makeText(USActivity.this, "Please Set Exchange Rate...", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        NumberFormat formatter = new DecimalFormat("#,###");

                        Float BahtRateFloat         = Float.valueOf(BahtRateString);
                        Float InputVlaueFloat     = Float.valueOf(InputValueString);

                        Float ResultValueFloat    = InputVlaueFloat * BahtRateFloat;

                        String ResultValueString  = formatter.format(ResultValueFloat);

                        CountryName.setText("Bahts");
                        ResultValue.setText(ResultValueString + " ฿");
                        RatingValue.setText(BahtRateString+ " ฿");
                        CountryFlag.setImageResource(R.drawable.thailand_flag);
                        PopUpDialogResult.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                        PopUpDialogResult.show();




                    }

                }

            }
        });
    }

    private void ConvertToMM()
    {
        MMConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mmRateString          = MMRate.getText().toString();
                String InputValueString      = US_Value_Input.getText().toString();

                if(TextUtils.isEmpty(InputValueString))
                {
                    Toast.makeText(USActivity.this, "Please Enter Input Value First", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(TextUtils.isEmpty(mmRateString))
                    {
                        Toast.makeText(USActivity.this, "Please Set Exchange Rate...", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        NumberFormat formatter = new DecimalFormat("#,###");

                        Float mmRateFloat         = Float.valueOf(mmRateString);
                        Float InputVlaueFloat     = Float.valueOf(InputValueString);

                        Float ResultValueFloat    = InputVlaueFloat * mmRateFloat;

                        String ResultValueString  = formatter.format(ResultValueFloat);

                        CountryName.setText("Kyats");
                        ResultValue.setText(ResultValueString + " Kyats");
                        RatingValue.setText(mmRateString+ " Kyats");
                        CountryFlag.setImageResource(R.drawable.mm_flag);
                        PopUpDialogResult.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                        PopUpDialogResult.show();




                    }

                }
            }
        });
    }

    private void PopUpDialog()
    {
        PopUpDialogResult = new Dialog(USActivity.this);
        PopUpDialogResult.setContentView(R.layout.popup_dialog);
        CountryName = (TextView) PopUpDialogResult.findViewById(R.id.country_name);
        ResultValue = (TextView) PopUpDialogResult.findViewById(R.id.result_value);
        RatingValue = (TextView) PopUpDialogResult.findViewById(R.id.rating_value);
        CountryFlag = (ImageView) PopUpDialogResult.findViewById(R.id.country_flag);
        CancelButton= (ImageButton) PopUpDialogResult.findViewById(R.id.cancel_button);

        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUpDialogResult.dismiss();
            }
        });
    }

    private void Initialization()
    {

        MMRate                  = findViewById(R.id.us_mm_rate);
        ChinaRate               = findViewById(R.id.us_china_rate);
        BahtRate                = findViewById(R.id.us_baht_rate);
        DongRate                = findViewById(R.id.us_dong_rate);

        US_Value_Input          = findViewById(R.id.us_value_input);



        MMConvertButton         = findViewById(R.id.us_mm_convert_button);
        ThaiConvertButton       = findViewById(R.id.us_thai_convert_button);
        ChinaConvertButton      = findViewById(R.id.us_china_convert_button);
        VietnamConvertButton    = findViewById(R.id.us_vietnam_convert_button);
    }

    private void ToolbarAndNevigationDrawer()
    {
        mToolbar = (Toolbar) findViewById(R.id.us_main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("United States");

        drawerLayout =  (DrawerLayout) findViewById(R.id.us_drawable_layout);
        navigationView =  (NavigationView) findViewById(R.id.us_navigation_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(USActivity.this,drawerLayout,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        View navView = navigationView.inflateHeaderView(R.layout.navigation_header);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                SelectPage(item);
                return false;
            }
        });
    }

    private void SelectPage(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.mm_page:
                SendUserToMmActivity();
                break;

            case R.id.us_page:
                SendUserToUsActivity();
                break;

            case R.id.china_page:
                SendUserToChinaActivity();
                break;

            case R.id.thai_page:
                SendUserToThaiActivity();
                break;

            case R.id.vietnam_page:
                SendUserToVietnamActivity();
                break;
        }
    }

    private void SendUserToVietnamActivity()
    {
        Intent VietnamIntent = new Intent(USActivity.this,VietnamActivity.class);
        VietnamIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(VietnamIntent);
    }

    private void SendUserToThaiActivity()
    {
        Intent ThailandIntent = new Intent(USActivity.this,ThailandActivity.class);
        ThailandIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ThailandIntent);
    }

    private void SendUserToChinaActivity()
    {
        Intent ChinaInetent = new Intent(USActivity.this,ChinaActivity.class);
        ChinaInetent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ChinaInetent);
    }

    private void SendUserToUsActivity()
    {
        Intent UsInetent = new Intent(USActivity.this,USActivity.class);
        UsInetent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(UsInetent);
    }

    private void SendUserToMmActivity()
    {
        Intent MmInetent = new Intent(USActivity.this,MainActivity.class);
        MmInetent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(MmInetent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

}
