package com.example.finalprojectdiit;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String CHANNEL_ID = "Channel_ID";
    public static final String CHANNEL_NAME = "Channel_Name";
    public static final String CHANNEL_DESC = "Channel_Desc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent read_int = getIntent();
        String url = read_int.getStringExtra("url");

        if(url != null){
            Intent openWV = new Intent(MainActivity.this, NewsWebView.class);
            openWV.putExtra("url", url);   // optional parameters
            MainActivity.this.startActivity(openWV);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(CHANNEL_DESC);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);

        }


        FragmentManager fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {

                return;
            }
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            HomeFragment homeFragment = new HomeFragment();
            fragmentTransaction.add(R.id.fragment_container, homeFragment, null);
            fragmentTransaction.commit();
        }

//        FirebaseInstanceId.getInstance().getInstanceId()
//                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
//
//                        if (task.isSuccessful()){
//
//                            String token = task.getResult().getToken();
//                            System.out.println("Token : "+token);
//                        }
//                        else {
//                            System.out.println("Token not generate");
//                        }
//                    }
//                });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialContactPhone("0875962796");
            }
        });


        ImageView logo_home = (ImageView) findViewById(R.id.imageView_logo1);
        logo_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).addToBackStack(null).commit();
                Toast.makeText(getApplicationContext(),"หน้าหลัก",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }

    public static class HighlightViewHolder extends RecyclerView.ViewHolder{
        View mView_Highlight;
        public HighlightViewHolder(View itemView){
            super(itemView);
            mView_Highlight = itemView;
        }
        public void setTitle(String title){
            TextView post_title = (TextView)mView_Highlight.findViewById(R.id.post_title);
            post_title.setText(title);
        }
        public void setDesc(String desc){
            TextView post_desc = (TextView)mView_Highlight.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }
        public void setImage(Context ctx, String image){
            ImageView post_image = (ImageView) mView_Highlight.findViewById(R.id.post_image);
            Picasso.get().load(image).into(post_image);
        }
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder{
        View mView_News;
        public NewsViewHolder(View itemView){
            super(itemView);
            mView_News = itemView;
        }
        public void setTitle(String title){
            TextView post_title = (TextView)mView_News.findViewById(R.id.post_title);
            post_title.setText(title);
        }
        public void setDesc(String desc){
            TextView post_desc = (TextView)mView_News.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }
        public void setDate_start(String date_start){
            TextView post_dateStart = (TextView)mView_News.findViewById(R.id.post_dateStart);
            post_dateStart.setText("start : "+date_start);
        }

        public void setImage(Context ctx, String image){
            ImageView post_image = (ImageView) mView_News.findViewById(R.id.post_image);
            Picasso.get().load(image).into(post_image);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).addToBackStack(null).commit();
                Toast.makeText(getApplicationContext(),"หน้าหลัก",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_notification:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new NewsFragment()).addToBackStack(null).commit();
                Toast.makeText(getApplicationContext(),"ข่าวสารและกิจกรรม",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_kanabodee:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new KanabodeeFragment()).addToBackStack(null).commit();
                Toast.makeText(getApplicationContext(),"สาห์นคณบดี",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_structure:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StructureFragment()).addToBackStack(null).commit();
                Toast.makeText(getApplicationContext(),"โครงสร้างองค์กร",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_courses_Bachelor:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new BachelorFragment()).addToBackStack(null).commit();
                Toast.makeText(getApplicationContext(),"ปริญญาตรี",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_courses_Master:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MasterFragment()).addToBackStack(null).commit();
                Toast.makeText(getApplicationContext(),"ปริญญาโท",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_courses_Doctor:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DoctorFragment()).addToBackStack(null).commit();
                Toast.makeText(getApplicationContext(),"ปริญญาเอก",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_quiz:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StartQuizFragment()).addToBackStack(null).commit();
                Toast.makeText(getApplicationContext(),"Start Quiz!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_dm:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DmFragment()).addToBackStack(null).commit();
                Toast.makeText(getApplicationContext(),"สอบถามข้อมูลเพิ่มเติม",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_map:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MapFragment()).addToBackStack(null).commit();
                Toast.makeText(getApplicationContext(),"แผนที่ตั้งมหาวิทยาลัย",Toast.LENGTH_SHORT).show();
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new NewsFragment()).addToBackStack(null).commit();
        Toast.makeText(getApplicationContext(),"ข่าวสารและกิจกรรม",Toast.LENGTH_SHORT).show();

    }
}
