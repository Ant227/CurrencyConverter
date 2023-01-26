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

public class MainActivity extends AppCompatActivity {

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar mToolbar;


    private EditText MMRate , ChinaRate , BahtRate , DongRate , MM_Value_Input;
    private Button USConvertButton, ThaiConvertButton, ChinaConvertButton, VietnamConvertButton;

    private Dialog PopUpDialogResult;

    private TextView CountryName , ResultValue , RatingValue;
    private ImageView CountryFlag;
    private ImageButton CancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Initialization();
        ToolbarAndNevigationDrawer();
        PopUpDialog();
        CurrencyExchangeCalculation();







    }

    private void PopUpDialog()
    {
        PopUpDialogResult = new Dialog(MainActivity.this);
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
        MMRate                  = findViewById(R.id.mm_mm_rate);
        ChinaRate               = findViewById(R.id.mm_china_rate);
        BahtRate                = findViewById(R.id.mm_baht_rate);
        DongRate                = findViewById(R.id.mm_dong_rate);

        MM_Value_Input          = findViewById(R.id.mm_value_input);



        USConvertButton         = findViewById(R.id.mm_us_convert_button);
        ThaiConvertButton       = findViewById(R.id.mm_thai_convert_button);
        ChinaConvertButton      = findViewById(R.id.mm_china_convert_button);
        VietnamConvertButton    = findViewById(R.id.mm_vietnam_convert_button);
    }

    private void CurrencyExchangeCalculation()
    {
       ConvertToUS();
        ConvertToBaht();
        ConvertToYuan();
        ConvertToDong();

    }

    private void ConvertToDong()
    {
        VietnamConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mmRateString          = MMRate.getText().toString();
                String InputValueString      = MM_Value_Input.getText().toString();

                String DongRateString        = DongRate.getText().toString();

                if(TextUtils.isEmpty(InputValueString))
                {
                    Toast.makeText(MainActivity.this, "Please Enter Input Value First", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(TextUtils.isEmpty(mmRateString) || TextUtils.isEmpty(DongRateString))
                    {
                        Toast.makeText(MainActivity.this, "Please Set Exchange Rate...", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        NumberFormat formatter = new DecimalFormat("#,###");


                        Float mmRateFloat         = Float.valueOf(mmRateString);
                        Float InputVlaueFloat     = Float.valueOf(InputValueString);
                        Float DongRateFloat       = Float.valueOf(DongRateString);

                        Float USDValueFloat       = InputVlaueFloat/mmRateFloat;

                        Float ResultValueFloat    = USDValueFloat * DongRateFloat;

                        String ResultValueString  = formatter.format(ResultValueFloat);

                        CountryName.setText("Dong");
                        ResultValue.setText(ResultValueString + " ₫");
                        RatingValue.setText(DongRateString + " ₫");
                        CountryFlag.setImageResource(R.drawable.vienam_flag);
                        PopUpDialogResult.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                        PopUpDialogResult.show();




                    }

                }
            }
        });
    }

    private void ConvertToYuan()
    {
        ChinaConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mmRateString          = MMRate.getText().toString();
                String InputValueString      = MM_Value_Input.getText().toString();

                String ChinaRateString        = ChinaRate.getText().toString();

                if(TextUtils.isEmpty(InputValueString))
                {
                    Toast.makeText(MainActivity.this, "Please Enter Input Value First", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(TextUtils.isEmpty(mmRateString) || TextUtils.isEmpty(ChinaRateString))
                    {
                        Toast.makeText(MainActivity.this, "Please Set Exchange Rate...", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        NumberFormat formatter = new DecimalFormat("#,###");

                        Float mmRateFloat         = Float.valueOf(mmRateString);
                        Float InputVlaueFloat     = Float.valueOf(InputValueString);
                        Float ChinaRateFloat       = Float.valueOf(ChinaRateString);

                        Float USDValueFloat       = InputVlaueFloat/mmRateFloat;

                        Float ResultValueFloat    = USDValueFloat * ChinaRateFloat;

                        String ResultValueString  = formatter.format(ResultValueFloat);

                        CountryName.setText("Yuan");
                        ResultValue.setText(ResultValueString + " ￥");
                        RatingValue.setText(ChinaRateString + " ￥");
                        CountryFlag.setImageResource(R.drawable.ic_flag_for_flag_china);
                        PopUpDialogResult.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                        PopUpDialogResult.show();

                    }

                }
            }
        });
    }

    private void ConvertToBaht()
    {
        ThaiConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mmRateString          = MMRate.getText().toString();
                String InputValueString      = MM_Value_Input.getText().toString();

                String BahtRateString        = BahtRate.getText().toString();

                if(TextUtils.isEmpty(InputValueString))
                {
                    Toast.makeText(MainActivity.this, "Please Enter Input Value First", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(TextUtils.isEmpty(mmRateString) || TextUtils.isEmpty(BahtRateString))
                    {
                        Toast.makeText(MainActivity.this, "Please Set Exchange Rate...", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                        NumberFormat formatter = new DecimalFormat("#,###");

                        Float mmRateFloat         = Float.valueOf(mmRateString);
                        Float InputVlaueFloat     = Float.valueOf(InputValueString);
                        Float BahtRateFloat       = Float.valueOf(BahtRateString);

                        Float USDValueFloat       = InputVlaueFloat/mmRateFloat;

                        Float ResultValueFloat    = USDValueFloat * BahtRateFloat;

                        String ResultValueString  = formatter.format(ResultValueFloat);


                        CountryName.setText("Baht");
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

    private void ConvertToUS()
    {

        USConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mmRateString          = MMRate.getText().toString();
                String InputValueString           = MM_Value_Input.getText().toString();

                if(TextUtils.isEmpty(InputValueString))
                {
                    Toast.makeText(MainActivity.this, "Please Enter Input Value First", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(TextUtils.isEmpty(mmRateString))
                    {
                        Toast.makeText(MainActivity.this, "Please Set Exchange Rate...", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        NumberFormat formatter = new DecimalFormat("#,###");

                        Float mmRateFloat         = Float.valueOf(mmRateString);
                        Float InputVlaueFloat     = Float.valueOf(InputValueString);

                        Float ResultValueFloat    = InputVlaueFloat/mmRateFloat;

                        String ResultValueString  = formatter.format(ResultValueFloat);

                        CountryName.setText("USD");
                        ResultValue.setText(ResultValueString + " $");
                        RatingValue.setText(mmRateString+ " Kyats");
                        CountryFlag.setImageResource(R.drawable.us_flag);
                        PopUpDialogResult.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                        PopUpDialogResult.show();




                    }

                }
            }
        });
    }


    private void ToolbarAndNevigationDrawer()
    {
        mToolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Myanmar");
        drawerLayout =  (DrawerLayout) findViewById(R.id.drawable_layout);
        navigationView =  (NavigationView) findViewById(R.id.navigation_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,R.string.drawer_open,R.string.drawer_close);
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
        Intent VietnamIntent = new Intent(MainActivity.this,VietnamActivity.class);
        VietnamIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(VietnamIntent);
    }



    private void SendUserToThaiActivity()
    {
        Intent ThailandIntent = new Intent(MainActivity.this,ThailandActivity.class);
        ThailandIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ThailandIntent);
    }

    private void SendUserToChinaActivity()
    {
        Intent ChinaInetent = new Intent(MainActivity.this,ChinaActivity.class);
        ChinaInetent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ChinaInetent);
    }

    private void SendUserToUsActivity()
    {
        Intent UsInetent = new Intent(MainActivity.this,USActivity.class);
        UsInetent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(UsInetent);
    }

    private void SendUserToMmActivity()
    {
        Intent MmInetent = new Intent(MainActivity.this,MainActivity.class);
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
