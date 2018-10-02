package com.dotvoid.drona;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class number_register extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS=0;
    String phoneNumber;
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
                phoneNumber=phn.getText().toString();
                if(phoneNumber != null && !phoneNumber.isEmpty() && phoneNumber.length()>9) {
                send_sms();
                Intent b=new Intent(getApplicationContext(),number_confirm.class);

                b.putExtra(phoneNumber,"phone");

                startActivity(b);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                }
                else
                    Toast.makeText(getBaseContext(), "Please enter phone number.", Toast.LENGTH_LONG).show();
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


    public void send_sms()
    {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumber, null, "Your verification code is 664732", null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }

}
