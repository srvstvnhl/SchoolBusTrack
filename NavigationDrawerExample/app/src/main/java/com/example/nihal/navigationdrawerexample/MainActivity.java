package com.example.nihal.navigationdrawerexample;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nihal.navigationdrawerexample.login.LoginActivity;
import com.example.nihal.navigationdrawerexample.login.LoginBuilder;
import com.example.nihal.navigationdrawerexample.login.LoginResponse;
import com.example.nihal.navigationdrawerexample.login.SaveLoginStatus;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;

    private ActionBarDrawerToggle drawerToggle;

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    //private Boolean exit = false;
    Fragment fragment;

    FragmentManager fragmentManager;

    EditText login_input_email;


    EditText login_input_password;


    CircleImageView circularImage;

    LoginResponse forImage;
String username;
String password;
String token;
TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*login_input_email = findViewById(R.id.input_email);
        login_input_password = findViewById(R.id.input_password);*/
        /*circularImage = findViewById(R.id.circularImageId);*/


        /*username = login_input_email.getText().toString();
        password = login_input_password.getText().toString();*/
        //setImage(username,password);


        //token = SaveLoginStatus.getPref("token",getApplicationContext());
        //Log.e("TAG", "onCreate:  shared preferences "+token );
        //setImage(username,password);


        nvDrawer = findViewById(R.id.nvView);
        header=nvDrawer.getHeaderView(0).findViewById(R.id.headerText);


        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null)
        {
            /*String s = b.getString("myname");
            Log.e("TAG", "onCreate: on use of bundle"+s);*/

            header.setText(" "+b.getString("myname"));
            Log.e("TAG", "onCreate:bundle "+b.getString("myname") );
        }



        //Connect the views of navigation bar
        nvDrawer = findViewById(R.id.nvView);
        circularImage =nvDrawer.getHeaderView(0).findViewById(R.id.circularImageId);


        //The Main Fragment that is open when user start the app
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.add(R.id.flContent, new TestMapFragment());
        tx.commit();


        // Set a Toolbar to replace the ActionBar.
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find our drawer view
        mDrawer = findViewById(R.id.drawer_layout);
        drawerToggle = setUpDrawerToggle();

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);

        // Find our drawer view
        nvDrawer = findViewById(R.id.nvView);

        setupDrawerContent(nvDrawer);

    }

    private ActionBarDrawerToggle setUpDrawerToggle(){

        return  new ActionBarDrawerToggle(this,mDrawer,toolbar,R.string.drawer_open,R.string.drawer_close);

    }
    private void setupDrawerContent(final NavigationView navigationView) {

        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setCheckedItem(R.id.nav_home_fragment);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                selectDrawerItem(menuItem);
                return true;
            }
        });
    }

    private void selectDrawerItem(MenuItem menuItem) {

        //create a new fragment and specify the fragment to show the based on nav item clicked

        //Fragment fragment = null;

        fragmentManager= getSupportFragmentManager();

        //Class fragmentClass;

        switch(menuItem.getItemId()) {
            case R.id.nav_home_fragment:
                //fragmentClass = SettingFragment.class;
                fragment = new TestMapFragment();
                fragmentManager.beginTransaction().replace(R.id.flContent,fragment).commit();
                break;
            case R.id.nav_settings_fragment:
                //fragmentClass = TestMapFragment.class;
                fragment = new SettingFragment();
                fragmentManager.beginTransaction().replace(R.id.flContent,fragment).commit();
                break;
            case R.id.nav_logout_fragment:
                //fragmentClass = TestMapFragment.class;
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this); //Home is name of the activity
                builder.setMessage("Do you want to logout?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        SaveLoginStatus.setLoggedIn(getApplicationContext(), false);
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK|
                                Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);

                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert=builder.create();
                alert.show();
                break;
        }

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);

        // Set action bar title
        setTitle(menuItem.getTitle());

        // Close the navigation drawer
        mDrawer.closeDrawers();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // onPostCreate called when activity start-up is complete after onStart()
    // NOTE 1: Make sure to override the method with only a single Bundle argument
    // Note 2: Make sure you implement the correct onPostCreate(Bundle savedInstanceState) method.
    // There are 2 signatures and only onPostCreate(Bundle savedInstanceState) shows the hamburger icon.
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public void onBackPressed(){
        DrawerLayout layout = findViewById(R.id.drawer_layout);


        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else if (layout.isDrawerOpen(GravityCompat.START)) {

            if (layout.isDrawerOpen(GravityCompat.START)) {
                layout.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
        else{
            //Toast.makeText(getBaseContext(), "Tap back button in order to exit", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            mBackPressed = System.currentTimeMillis();
        }
    }
public void setImage(String username,String password){


        LoginBuilder.getLoginInstance().getUserLogin(username,password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                forImage= response.body();
                Log.e("TAG", "onResponse: "+forImage.getResponseResult().getUsername() );
                Picasso.get().load(forImage.getResponseResult().getUserImage()).into(circularImage);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Log.e("TAG", "onFailure: MainActivity"+t.getLocalizedMessage() );

            }
        });
}




/*
    @Override
    public void onBackPressed() {
        if (exit) {
            super.onBackPressed();
            return;
        }

        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = fragmentManager.findFragmentById(R.id.nav_home_fragment);
            if (fragment != null) {
                if (fragment.isVisible()) {
                    this.exit = true;
                    Toast.makeText(this, "Press Back again to Exit", Toast.LENGTH_SHORT).show();

                }
            }
            else {
                fragment = TestMapFragment.class.newInstance();
                getFragmentManager().popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            }
        } catch (Exception e) {

        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                exit = false;
            }
        }, 2000);
    }*/

}
