
package com.dotvoid.drona;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignUp extends AppCompatActivity {

    String ph;
    EditText e1,e2,e3;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        e1=(EditText)findViewById(R.id.username);
        e2=(EditText)findViewById(R.id.pass);
        e3=(EditText)findViewById(R.id.passconfirm);
        next=(Button)findViewById(R.id.next);

        Intent in=getIntent();
        ph=in.getStringExtra("ph");


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String E1,E2,E3;
                E1=e1.getText().toString();
                E2=e2.getText().toString();
                E3=e3.getText().toString();
                if(!E1.isEmpty() && !E2.isEmpty() && !E3.isEmpty() && E2.length()>7 ) {
                if(e2.getText().toString().equals(e3.getText().toString()))
                {
                    Intent d = new Intent(getApplicationContext(),SignUp2.class);
                    d.putExtra("uname",e1.getText().toString());
                    d.putExtra("pass",e2.getText().toString());
                    d.putExtra("ph",ph);
                    startActivity(d);
                    overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Passwords do not match. Please retry",Toast.LENGTH_LONG).show();
                }
                }
                else
                    Toast.makeText(getBaseContext(), "Please enter all the details.", Toast.LENGTH_LONG).show();
            }
        });

    }
}
