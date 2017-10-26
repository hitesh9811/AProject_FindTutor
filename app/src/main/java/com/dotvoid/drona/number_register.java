package com.dotvoid.drona;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class number_register extends AppCompatActivity {

    String pn;
    Button reg;
    EditText phn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_register);

        reg=(Button)findViewById(R.id.numreg);
        phn=(EditText)findViewById(R.id.phone);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b=new Intent(getApplicationContext(),number_confirm.class);
                pn=phn.getText().toString();
                b.putExtra(pn,"phone");

                startActivity(b);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });

    }

    public void link_to_facebook(View view)
    {
        Intent intent1=new Intent(Intent.ACTION_VIEW);
        intent1.setData(Uri.parse("http://www.facebook.com"));
        startActivity(intent1);
    }

    public void link_to_twitter(View view)
    {
        Intent intent2=new Intent(Intent.ACTION_VIEW);
        intent2.setData(Uri.parse("http://www.twitter.com"));
        startActivity(intent2);
    }

    public void link_to_google_plus(View view)
    {
        Intent intent3=new Intent(Intent.ACTION_VIEW);
        intent3.setData(Uri.parse("http://plus.google.com"));
        startActivity(intent3);
    }

    public void go_to_main_act(View view)
    {
        Intent revert=new Intent(this,MainActivity.class);
        startActivity(revert);
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }

    /*public void numreg(View view)
    {
        Intent b=new Intent(this,number_confirm.class);
        startActivity(b);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }*/
}
