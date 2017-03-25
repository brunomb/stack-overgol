package com.github.brunomb.stackovergol.activity.main;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
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

import com.github.brunomb.stackovergol.R;
import com.github.brunomb.stackovergol.activity.login.LoginActivity;
import com.github.brunomb.stackovergol.activity.matches.MatchesFragment;
import com.github.brunomb.stackovergol.service.StackOvergolService;
import com.github.brunomb.stackovergol.utils.MyLog;

public class MainScreenActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MatchesFragment.OnFragmentInteractionListener, MainMVP.ViewOps {

    private boolean boundToStackOvergolService = false;

    private MainMVP.PresenterOps mPresenter;

    final String[] fragments ={"com.github.brunomb.stackovergol.activity.matches.MatchesFragment"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
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
        TextView tvUserRole = (TextView) header.findViewById(R.id.sog_nav_menu_tv_role);
        tvUserRole.setText(getResources().getString(R.string.admin));
        TextView tvUser = (TextView) header.findViewById(R.id.sog_nav_menu_tv_user);
        //TODO GET USER NAME
        tvUser.setText("Temp");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            mPresenter.logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
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
//        MatchesFragment teste = MatchesFragment.newInstance("teste1","teste2");
        tx.replace(R.id.main_fragment, Fragment.instantiate(MainScreenActivity.this, fragments[fragmentCont]), fragments[fragmentCont]);
        tx.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

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
    }

    @Override
    public void stackOvergolServiceDisconnected() {
        boundToStackOvergolService = false;
    }

    @Override
    public void onLogout() {
        MyLog.i("MainScreenActivity -Logout, going to Login");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
