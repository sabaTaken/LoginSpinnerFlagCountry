package com.example.mycountry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //آداپتور برای جایگذاری کشور
    private ArrayAdapter<String> adapter;
    //اسپینر اختصاصی
    private CountryCodeSpinner ccs;
    //آیکون فلش به سمت پایین کنار اسپینر
    private ImageView imgDown;
    //ادیت تکسک کد کشور
    private EditText edtCode;
    //ادیت تکست شماره موبایل
    private EditText edtMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //کد کشور
        edtCode = findViewById(R.id.edtCode);
        //نام کشور
        //final TextView txtName = findViewById(R.id.txtName);
        //پرچم کشور
        ccs = findViewById(R.id.ccs);
        //آیکون اسپینر
        imgDown = findViewById(R.id.imgDown);
        //شماره موبایل
        edtMobile = findViewById(R.id.edtMobile);

        //آداپتور برای انتخاب پرچم ، هنگامی که کد کشور وارد شد
        adapter = new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1,
                android.R.id.textAssist, ccs.getArrayPhoneCode(ccs.getArray()));

        //انتخاب پیش فرض ایران
        int pos = getArrayPos("+98");
        ccs.setSelection(pos);
        edtMobile.requestFocus();

        edtCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                adapter.getFilter().filter("+" + cs);
                int pos = getArrayPos(cs.toString());
                ccs.setSelection(pos);

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });

        imgDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ccs.performClick();
            }
        });

        // انتخاب از اسپینر
        ccs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CountryCodeSpinner.CountryModel model = (CountryCodeSpinner.CountryModel) parent.getAdapter().getItem(position);
                edtCode.setText(model.getCountryCode());
                edtCode.setSelection(edtCode.getText().length());
                Log.e("tolu", ": " + model.getCountryCode());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    /**
     * برگرداندن موقعیت در آرایه
     *
     * @param phone
     * @return
     */
    public final int getArrayPos(String phone) {
        return ccs.getArrayPhoneCode(ccs.getArray()).indexOf(phone);
    }

}
