package com.dotvoid.drona;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    String user_name,password,fname,lname;
    EditText u,p;
    Button login;
    int i;
    DBHelp DBHelp;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        u=(EditText)findViewById(R.id.usern);
        p=(EditText)findViewById(R.id.pass);
        login=(Button)findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent f = new Intent(getApplicationContext(), Skill_Chooser.class);
                startActivity(f);

                user_name=u.getText().toString();
                password=p.getText().toString();
                DBHelp = new DBHelp(getApplicationContext());
                sqLiteDatabase=DBHelp.getReadableDatabase();
                Cursor cursor=DBHelp.getContact(user_name,password,sqLiteDatabase);

               /* if(cursor.moveToFirst())
                {

                    fname=cursor.getString(0);
                    lname=cursor.getString(1);
                    Intent f = new Intent(getApplicationContext(), Skill_Selector.class);
                    f.putExtra(fname, "fname");
                    f.putExtra(lname, "lname");
                    startActivity(f);
                    overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

                }*/
            /*  else
                    {

                        Toast.makeText(getBaseContext(), "Wrong username or password", Toast.LENGTH_LONG).show();
                    }
*/

            }
        });

    }
    public void link_to_facebook(View view)
    {
        Intent intent1=new Intent(this,login_with_facebook.class);
        startActivity(intent1);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
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
    public void signup(View view)
    {
        Intent a = new Intent(this,number_register.class);
        startActivity(a);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }
}