package com.dotvoid.drona;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class number_confirm extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS=0;
    String phoneNumber;
    TextView t;
    String code;
    EditText e1;
    Button con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_confirm);
        con=(Button)findViewById(R.id.numcon);
        e1=(EditText)findViewById(R.id.verification_code);
        t=(TextView)findViewById(R.id.verify);

        Intent i=getIntent();
        phoneNumber=i.getStringExtra("phoneNumber");

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(number_confirm.this).setTitle("Confirm Operation").setIcon(R.mipmap.ic_launcher).setMessage("are you sure about this action?").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       send_sms();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getBaseContext(),"You chose to cancel it",Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog alert=alertDialog.create();
                alert.show();
            }
        });

        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                code=e1.getText().toString();
                if(code.equals("664732"))
                {
                    Intent c = new Intent(getApplicationContext(),SignUp.class);
                    c.putExtra("phoneNumber",phoneNumber);
                    startActivity(c);
                    overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Sorry! The entered code is incorrect. Try again",Toast.LENGTH_SHORT).show();
                    e1.setText("");
                }
            }
        });

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
