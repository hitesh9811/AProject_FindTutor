package com.dotvoid.drona;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SignUpDone extends AppCompatActivity {

    String u,p,pn,f,l,e,c,g,a;
    ToggleButton t;
    LinearLayout l1;
    Button log;
    DBHelp DBHelp;
    SQLiteDatabase sqLiteDatabase;
    Context context=this;
    CheckBox check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_done);
        t=(ToggleButton)findViewById(R.id.invert_color);
        l1=(LinearLayout)findViewById(R.id.layout_done);
        log=(Button)findViewById(R.id.login);
        check=(CheckBox)findViewById(R.id.check);
        Intent getdet = getIntent();
        u=getdet.getStringExtra("uname");
        p=getdet.getStringExtra("pass");
        pn=getdet.getStringExtra("phone");
        e=getdet.getStringExtra("email");
        f=getdet.getStringExtra("fname");
        l=getdet.getStringExtra("lname");
        c=getdet.getStringExtra("city");
        g=getdet.getStringExtra("gender");
        a=getdet.getStringExtra("age");


        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent revert_now=new Intent(getApplicationContext(),MainActivity.class);

                DBHelp=new DBHelp(context);
                sqLiteDatabase=DBHelp.getWritableDatabase();

                //user declaredd function
                DBHelp.addInfo(u,p,pn,e,f,l,c,g,a,sqLiteDatabase);

                DBHelp.close();


                if(check.isChecked()) {
                    startActivity(revert_now);
                    overridePendingTransition(R.anim.push_out_right, R.anim.pull_in_left);
                }
                else
                    Toast.makeText(getBaseContext(), "Accept terms and conditions.", Toast.LENGTH_LONG).show();
            
            }
        });


        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                boolean on = t.isChecked();

                if (on) {
                    l1.setBackgroundColor(Color.GRAY);
                } else {
                   l1.setBackground(getResources().getDrawable(R.drawable.background));
                }

            }
        });
    }


}
