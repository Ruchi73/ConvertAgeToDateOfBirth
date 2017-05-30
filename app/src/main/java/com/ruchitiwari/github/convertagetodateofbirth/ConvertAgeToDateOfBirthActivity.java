package com.ruchitiwari.github.convertagetodateofbirth;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ruchitiwari.github.util.AgeCalculate;
import com.ruchitiwari.github.util.ApproxAge;
import com.ruchitiwari.github.util.Util;
import com.ruchitiwari.github.util.Validate;
import com.ruchitiwari.github.util.Year;

import java.util.Calendar;

import static com.ruchitiwari.github.util.ApproxAge.format2LenStr;
import static com.ruchitiwari.github.util.Validate.*;

/**
 * Created by Ruchi Tiwari on 29-05-2017.
 */


public class ConvertAgeToDateOfBirthActivity extends AppCompatActivity implements View.OnClickListener {
    String years = "";
    String months = "";
    String days = "";
    Context context;
    TextView age_edittext, dateofbirth;
    Button btn_confirm;
    String ageresult ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agetodob);
        context = ConvertAgeToDateOfBirthActivity.this;
        age_edittext = (TextView) findViewById(R.id.age_edittext);
        dateofbirth = (TextView) findViewById(R.id.dateofbirth);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);

        btn_confirm.setOnClickListener(this);
        age_edittext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.age_edittext:

                years = "";
                months = "";
                days = "";
                Util.hideSoftKeyboard(context, v);


                ApproxAge approxage = new ApproxAge.Builder(ConvertAgeToDateOfBirthActivity.this, new ApproxAge.OnDatePickedListener() {
                    @Override
                    public void onDatePickCompleted(int year, int month, int day, String dateDesc) {
                        years = String.valueOf(year);
                        months = ApproxAge.monthList.get(month - 1);
                        days = format2LenStr(day);
                        StringBuffer sb = new StringBuffer();
                        if (years.length() == 1)
                            sb.append("0" + years);
                        else
                            sb.append(years);
                        sb.append("-");
                        sb.append(months);
                        sb.append("-");
                        sb.append(days);

                        if (years.equalsIgnoreCase("0") && months.equalsIgnoreCase("00") && days.equalsIgnoreCase("00")) {
                            Toast.makeText(context, "Approximate age cannot be zero", Toast.LENGTH_SHORT).show();
                        } else
                            age_edittext.setText(sb.toString());
                    }
                }).minYear(00) //min year in loop
                        .maxYear(100).build();
                approxage.showPopWin(ConvertAgeToDateOfBirthActivity.this);

                break;

            case R.id.btn_confirm:

                String age = age_edittext.getText().toString().trim();
                if (notEmpty(age)) {
                    String dob = getAge(years, months, days);
                    if (notEmpty(dob)) {
                        dateofbirth.setText("Your date of birth is : " + dob);
                        dateofbirth.setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(context, "Please enter age", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    /**
     * @param ayear  selected age year
     * @param amonth selected age month
     * @param adate  selected age date
     * @return
     */
    private String getAge(String ayear, String amonth, String adate) {

        ageresult = "";

        try {
            Calendar calendar = Calendar.getInstance();
            int ageyear = calendar.get(Calendar.YEAR);
            int agemonth = calendar.get(Calendar.MONTH);
            int agedate = calendar.get(Calendar.DATE);


            Year birthYear = new Year(Integer.valueOf(adate), Integer.valueOf(amonth), Integer.valueOf(adate));
            Year endYear = new Year(agedate, agemonth + 1, ageyear);

            new AgeCalculate() {
                @Override
                protected void getResult(String result) {
                    System.out.println(result);
                }
            }.calculateAGE(birthYear, endYear);

            System.out.println("\n");
            Year date = new Year(agedate, agemonth + 1, ageyear);
            Year ageAt = new Year(Integer.valueOf(adate), Integer.valueOf(amonth), Integer.valueOf(ayear));
            new AgeCalculate() {
                @Override
                protected void getResult(final String result) {
                    ageresult = result;

                    Log.e("agere", ageresult);
                }
            }.calculateDOB(date, ageAt);

        } catch (Exception e) {

        }

        return ageresult;
    }

}
