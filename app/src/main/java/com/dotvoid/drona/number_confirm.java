package com.dotvoid.drona;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class number_confirm extends AppCompatActivity {

    String phone;
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
        phone=i.getStringExtra("phone");

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
                    c.putExtra(phone,"phone");
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
        Uri uri = Uri.parse("smsto:9902676373");
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", "Your verification code is 664732");
        startActivity(it);
    }

}