package com.example.mytown;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mytown.objectives.mytown.MuzeuFragment;
import com.google.firebase.auth.FirebaseAuth;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    int[] images = {R.drawable.ghh2, R.drawable.primaria_gh, R.drawable.ghh, R.drawable.ghh4, R.drawable.ghh3, R.drawable.ghh5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextAppearance(this, R.style.AppTheme_NoActionBar_Title);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            Fragment homeFragment = new HomeFragment();
            firebaseAuth = FirebaseAuth.getInstance();
            firebaseUser = firebaseAuth.getCurrentUser();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_place, new HomeFragment(), "home").addToBackStack(String.valueOf(homeFragment)).commit();
            navigationView.setCheckedItem(R.id.nav_info);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        MenuInflater inflater = getMenuInflater();
        if (firebaseUser == null) {
            inflater.inflate(R.menu.login_menu, menu);
        } else {
            inflater.inflate(R.menu.user_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment loginFragment = new LoginFragment();
        switch (item.getItemId()) {
            case R.id.reset_pwd:
                Fragment resetFragment = new ResetPasswordFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_place, new ResetPasswordFragment(), "reset").addToBackStack(String.valueOf(resetFragment)).commit();
                break;
            case R.id.logout_option:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.login_option:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_place, new LoginFragment(), "login").addToBackStack(String.valueOf(loginFragment)).commit();
                break;
            case R.id.register_option:
                Fragment registerFragment = new RegisterFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_place, new RegisterFragment(), "register").addToBackStack(String.valueOf(registerFragment)).commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        switch (menuItem.getItemId()) {
            case R.id.nav_objectives:
                Fragment objFragment = new ObjectivesFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_place, new ObjectivesFragment(), "second").addToBackStack(String.valueOf(objFragment)).commit();
                break;
            case R.id.nav_info:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.nav_atractii:
                Fragment atractiiFragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_place, new AtractiiFragment(), "first").addToBackStack(String.valueOf(atractiiFragment)).commit();
                break;

            case R.id.nav_events:
                Fragment eventsFragment = new EventsFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_place, new EventsFragment(), "events").addToBackStack(String.valueOf(eventsFragment)).commit();
                break;

            case R.id.nav_cazare:
                if (firebaseUser == null) {
                    Toast.makeText(MainActivity.this, "Vă rugăm să vă logați pentru a putea accesa această secțiune!", Toast.LENGTH_LONG).show();
                } else {
                    Fragment cazareFragment = new CazareFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_place, new CazareFragment(), "cazare").addToBackStack(String.valueOf(cazareFragment)).commit();
                }
                break;
            case R.id.nav_restaurants:
                Fragment restauranteFragment = new RestauranteFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_place, new RestauranteFragment(), "rest").addToBackStack(String.valueOf(restauranteFragment)).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
