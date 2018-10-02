package com.dotvoid.drona;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Teacher extends AppCompatActivity {

    String subject;
    TextView t,des,qua,mailID,sub;
    ImageView img;
    Gallery g;
    int[] images={R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);


                img=(ImageView)findViewById(R.id.image);
                g=(Gallery)findViewById(R.id.gallery);
                t=(TextView)findViewById(R.id.teacher);
                des=(TextView)findViewById(R.id.summary);
                qua=(TextView)findViewById(R.id.quaification);
                mailID=(TextView)findViewById(R.id.emailID);
        sub=(TextView)findViewById(R.id.subject);

Intent get=getIntent();
        subject=get.getStringExtra("sub");


        sub.setText("Learn "+subject+" from,");

                CustomGalleryAdapter adapter=new CustomGalleryAdapter(getApplicationContext(),images);
                g.setAdapter(adapter);
                g.setAnimationDuration(1000);
                g.setSpacing(5);
                g.setUnselectedAlpha((float)0.25);

                g.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        img.setImageResource(images[position]);
                        if(position==0)
                        {
                            
                            t.setText("Vipin Rathi");
                            des.setText("Research Scholar at JNU,Internship at CERN,Research Fellow at NISCAIR(CSIR),etc");
                            qua.setText("RHCSA, RHCSE, RHCA, MCSA, Mtech(CSE) ");
                            mailID.setText("vipinrathi@gmail.com");  


                        }
                        if(position==1)
                        {
                            t.setText("Shivam Gupta");
                            des.setText("UI UX Designer. Android Enthusiast. Love Guru.");
                            qua.setText("BCA");
                            mailID.setText("shivamgupta@gmail.com");
                        }
                        if(position==2)
                        {
                            t.setText("Sukhjeet Singh");
                            des.setText("Coder for life. Pooper. Beemar hone vala hai. Century Link Employee");
                            qua.setText("BCA");
                            mailID.setText("gulati@gmail.com");
                        }
                        if(position==3)
                        {
                            t.setText("Nitish Chaudhary");
                            des.setText("UI UX Designer. Graphic Designing Expert. Game Developer");
                            qua.setText("BSc");
                            mailID.setText("c.nitish@gmail.com");
                        }
                        if(position==4)
                        {
                            t.setText("Arjun Chopra");
                            des.setText("Pharmacist. Chemistry Enthusiast.");
                            qua.setText("BSc");
                            mailID.setText("c.arjun@gmail.com");
                        }
                    }
                });

            }

            public void confirm_action(View view)
            {
                final AlertDialog.Builder dialogalert = new AlertDialog.Builder(this);

                dialogalert.setMessage("Would you like to notify this teacher?");
                dialogalert.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener()/* onclicklistener, normal jaisa */ {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(getApplicationContext(),"Teacher has been notified",Toast.LENGTH_LONG).show();

                                //notification
                                addNotification();
                            }
                        });

                dialogalert.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                dialogalert.create().show();
            }


    private void addNotification()
    {
        NotificationCompat.Builder builder=(NotificationCompat.Builder)new NotificationCompat.Builder(this).setSmallIcon(R.drawable.logo1).setContentTitle("Teacher Notified").setContentText("Check out other skills now!");

        Intent notificationIntent=new Intent(this,Skill_Chooser.class);
        PendingIntent contentIntent=PendingIntent.getActivity(this,0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }
        }




        class CustomGalleryAdapter extends BaseAdapter
        {
            private Context context;
            private int[] images;

            public CustomGalleryAdapter(Context c,int[] images)
            {
                context=c;
                this.images=images;
            }

            public int getCount()
            {
                return images.length;
            }

            @Override
            public Object getItem(int position)
            {
                return position;
            }

            @Override
            public long getItemId(int position)
            {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                ImageView imageView=new ImageView(context);
                imageView.setImageResource(images[position]);
                imageView.setLayoutParams(new Gallery.LayoutParams(200,200));
                return imageView;
            }



        }

