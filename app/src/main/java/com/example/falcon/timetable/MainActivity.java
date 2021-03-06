package com.example.falcon.timetable;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.falcon.timetable.DongBoDuLieu.GGWP;
import com.example.falcon.timetable.DongBoDuLieu.SyncDataFragment;
import com.example.falcon.timetable.Login_Register.LoginActivity;
import com.example.falcon.timetable.Login_Register.UserSessionManager;
import com.example.falcon.timetable.TatCaCongViec.TatCaCongViecFragment;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences sharedPreferences;
    UserSessionManager userSessionManager;
    NavigationView navigationView;
    TextView tv_header_username, tv_header_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new ThoiGianBieu_Fragment())
                .addToBackStack(null)
                .commit();

        CheckLogin();


    }

    private void CheckLogin() {
        userSessionManager = new UserSessionManager(getApplicationContext());
        if(userSessionManager.checkLogin()){
            userSessionManager = new UserSessionManager(getApplicationContext());
            HashMap<String, String> user = userSessionManager.getUserDetails();
            String username = user.get(UserSessionManager.KEY_USERNAME);


            View headerView = navigationView.getHeaderView(0);
            tv_header_username = (TextView) headerView.findViewById(R.id.tv_header_username);
            tv_header_name = (TextView) headerView.findViewById(R.id.tv_header_name);

            tv_header_username.setText(username);
        }else{

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_tatca_cac_congviec) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, new TatCaCongViecFragment())
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.nav_caidat) {

        } else if (id == R.id.nav_dongbo_dulieu) {
//            Intent i = new Intent(MainActivity.this, GGWP.class);
//            startActivity(i);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, new SyncDataFragment())
                    .addToBackStack(null)
                    .commit();

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_logout) {
            userSessionManager.logoutUser();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
