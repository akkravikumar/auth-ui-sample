package com.sample.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sample.App;
import com.sample.R;
import com.sample.util.UUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Context mContext;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @Inject
    ConnectivityManager connectivityManager;

    protected static class HeaderViewHolder {
        @BindView(R.id.drawer_email)
        TextView txtEmail;

        @BindView(R.id.drawer_name)
        TextView txtName;

        HeaderViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private MenuItem nav_profile, nav_logout;
    private Menu navMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawyer_activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ((App) getApplication()).getNetComponent().inject(this);
        mContext = this;
        if (!UUtil.isNetworkConnected(connectivityManager)) {
            UUtil.showToast(mContext, "Internet connection not available, Please try again");
            return;
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        View navHeader = navigationView.getHeaderView(0);
        new HeaderViewHolder(navHeader);
        navMenu = navigationView.getMenu();
        nav_profile = navMenu.findItem(R.id.nav_profile);
        nav_logout = navMenu.findItem(R.id.nav_logout);

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void loggedIn() {
        nav_profile.setTitle("Profile");
        nav_logout.setVisible(true);

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_profile) {
            UUtil.navigateIntent(mContext, ProfilesActivity.class);
        } else if (id == R.id.nav_logout) {
            UUtil.logoutApp(mContext, LoginActivity.class);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == DrawerActivity.RESULT_OK) {
                MenuItem nav_profile = navMenu.findItem(R.id.nav_profile);
                MenuItem nav_logout = navMenu.findItem(R.id.nav_logout);
                nav_profile.setTitle("Profile");
                nav_logout.setVisible(true);
                loggedIn();
                navigationView.setNavigationItemSelectedListener(this);
            }
            if (resultCode == DrawerActivity.RESULT_CANCELED) {
                UUtil.showToast(mContext, "Login Failed");
            }
        }
    }
}
