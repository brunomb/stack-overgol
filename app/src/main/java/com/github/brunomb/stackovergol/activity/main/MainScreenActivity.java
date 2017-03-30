package com.github.brunomb.stackovergol.activity.main;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.brunomb.stackovergol.R;
import com.github.brunomb.stackovergol.service.StackOvergolService;

public class MainScreenActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainMVP.ViewOps {

    private boolean boundToStackOvergolService = false;

    private MainMVP.PresenterOps mPresenter;
    private TextView tvUsername;
    private TextView tvUserRole;

    final String[] fragments ={"com.github.brunomb.stackovergol.activity.matches.MatchesFragment"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        initViews();
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));

        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);

        commitFragment(0);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        tvUserRole = (TextView) header.findViewById(R.id.sog_nav_menu_tv_role);
        tvUsername = (TextView) header.findViewById(R.id.sog_nav_menu_tv_user);

        toggle.syncState();
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_matches) {
            // Handle the camera action
        } else if (id == R.id.nav_finances) {

        } else if (id == R.id.nav_monthly) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter = new MainScreenPresenter(this);
        mPresenter.bindToStackOvergolService();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (boundToStackOvergolService) {
            mPresenter.unbindFromStackOvergolService();
        }
    }

    public void commitFragment(int fragmentCont) {
        FragmentTransaction tx = getFragmentManager().beginTransaction();
        tx.replace(R.id.main_fragment, Fragment.instantiate(MainScreenActivity.this, fragments[fragmentCont]), fragments[fragmentCont]);
        tx.commit();
    }

    @Override
    public boolean doBindToStackOvergolService(ServiceConnection connection) {
        if (!boundToStackOvergolService) {
            Intent intent = new Intent(this, StackOvergolService.class);
            return bindService(intent, connection, Context.BIND_AUTO_CREATE);
        } else {
            return false;
        }
    }

    @Override
    public void doUnbindToStackOvergolService(ServiceConnection connection) {
        unbindService(connection);
        boundToStackOvergolService = false;
    }

    @Override
    public void stackOvergolServiceConnected() {
        boundToStackOvergolService = true;
        tvUsername.setText(mPresenter.getUsername());
        tvUserRole.setText(mPresenter.getUserRole());
    }

    @Override
    public void stackOvergolServiceDisconnected() {
        boundToStackOvergolService = false;
    }
}
