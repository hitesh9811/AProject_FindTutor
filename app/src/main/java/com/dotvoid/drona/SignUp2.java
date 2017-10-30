package com.dotvoid.drona;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Date;

public class SignUp2 extends AppCompatActivity {

    String user_name,pass,phone;
    Button next;
    ToggleButton t;
    LinearLayout l;
    Spinner gender;
    int a,b,c;
    EditText age,mail,fname,lname;
    AutoCompleteTextView m;
    String[] cities={"Ajmer","Alahabad","Delhi","Bangalore","Chennai","Panji","Mumbai","Bhopal","Shimla","Koorg"};

    String[] gen={"Gender","Male","Female","Other"};
    Calendar calendar=Calendar.getInstance();
    private int curYear,curMonth,curDate,changeYear,changeMonth,changeDate;
    static final int id=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        l=(LinearLayout)findViewById(R.id.layout_second);
        gender=(Spinner)findViewById(R.id.gender);
        age=(EditText)findViewById(R.id.age);
        fname=(EditText)findViewById(R.id.first_name);
        lname=(EditText)findViewById(R.id.last_name);
        mail=(EditText)findViewById(R.id.email);
        t=(ToggleButton)findViewById(R.id.invert_color);
        next=(Button)findViewById(R.id.next);


        Intent getuname = getIntent();
        user_name=getuname.getStringExtra("uname");
        pass=getuname.getStringExtra("pass");
        phone=getuname.getStringExtra("phone");



        ArrayAdapter<String> adaptercities=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,cities);
        m=(AutoCompleteTextView)findViewById(R.id.city);
        m.setAdapter(adaptercities);
        m.setThreshold(1);


        ArrayAdapter<String> adaptergen=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,gen);
        gender.setAdapter(adaptergen);

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        curYear=calendar.get(Calendar.YEAR);
        curMonth=calendar.get(Calendar.MONTH);
        curDate=calendar.get(Calendar.DAY_OF_MONTH);

        age.setText("D.O.B");

        calendar.set(
                calendar.getActualMaximum(Calendar.YEAR),
                calendar.getActualMaximum(Calendar.MONTH),
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        );
        age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(id);
            }
        });


        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                boolean on = t.isChecked();

                if (on) {
                    l.setBackgroundColor(Color.BLACK);
                } else {
                    l.setBackground(getResources().getDrawable(R.drawable.background));
                }

            }
        });



        //database entry
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent q = new Intent(getApplicationContext(),SignUpDone.class);

                String u,p,pn,f,l,e,c,g,d;

                u=user_name;
                p=pass;
                pn=phone;
                e=mail.getText().toString();
                f=fname.getText().toString();
                l=lname.getText().toString();
                c=m.getText().toString();
                g=gender.getSelectedItem().toString();
                d=age.getText().toString();
                if(!e.isEmpty() && !f.isEmpty() && !l.isEmpty() && !c.isEmpty() &&  !g.isEmpty() && !d.isEmpty()) {

                    q.putExtra("uname", u);
                    q.putExtra("pass", p);
                    q.putExtra("phone", pn);
                    q.putExtra("email", e);
                    q.putExtra("fname", f);
                    q.putExtra("lname", l);
                    q.putExtra("city", c);
                    q.putExtra("gender", g);
                    q.putExtra("age", d);

                    startActivity(q);
                    overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                }
                else
                    Toast.makeText(getBaseContext(), "Please enter all the details.", Toast.LENGTH_LONG).show();
            }
        });


    }



    @Override
    protected Dialog onCreateDialog(int a)
    {
        switch (a)
        {
            case id: DatePickerDialog dialog = new DatePickerDialog(this, DatesetListener, curYear, curMonth, curDate);
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                return dialog;


        }
        return  null;
    }

    private DatePickerDialog.OnDateSetListener DatesetListener=new DatePickerDialog.OnDateSetListener()
    {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
        {
            changeYear=year;
            changeMonth=monthOfYear;
            changeDate=dayOfMonth;
            display();
        }
    };

    private void display()
    {
        calage();
        caldate();
        calmonth();
        if(a==0 && b==0)
        {
            age.setText(c+ " days");
        }
        else if(a==0)
        {
            age.setText(b+" months "+c+ " days");
        }
        else
        {
            age.setText(a+" years "+b+" months "+c+ " days");
        }
    }

    public void calage()
    {
        a=curYear-changeYear;

    }
    public void calmonth()
    {
        if(curYear>=changeYear)
        {
            a=curYear-changeYear;
            if(curMonth>=changeMonth)
            {
                b= curMonth-changeMonth;
            }
            else
            {
                b=curMonth-changeMonth;
                b=12+b;
                a--;
            }
        }


    }
    public void caldate()
    {

        if(curDate>=changeDate)
        {
            c= curDate-changeDate;
        }
        else
        {
            c=curDate-changeDate;
            c=30+c;

            if(b==0)
            {
                b=11;
                a--;
            }
            else
            {
                b--;
            }

        }
    }
}
