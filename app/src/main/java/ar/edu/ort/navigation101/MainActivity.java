package ar.edu.ort.navigation101;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private FragmentTabHost tabHost;
    public String userName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        inicializarToolbar(); // Setear Toolbar como action bar

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setearListener(navigationView);
        }

    }




    private void inicializarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, 0,0);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        tabHost.addTab(
                tabHost.newTabSpec("tab1").setIndicator("Tab 1", null),
                FirstFragment.class, null);
        tabHost.addTab(
                tabHost.newTabSpec("tab2").setIndicator("Tab 2", null),
                SecondFragment.class, null);


    }

    private void setearListener(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                Fragment fragment = null;
                switch(item.getItemId()) {
                    case R.id.nav_camera:
                        Log.d("Choose:","Camera");
                        tabHost.setCurrentTab(0);
                        break;
                    case R.id.nav_gallery:
                        Log.d("Choose:","Gallery");
                        tabHost.setCurrentTab(1);
                        break;
                    case R.id.nav_send:
                        Log.d("Choose:","Send");
                        FragmentManager fm = getSupportFragmentManager();
                        UserNameDialog userNameDialog = new UserNameDialog();
                        userNameDialog.show(fm, "fragment_edit_name");

                        break;

                }


//                FragmentManager fm = getSupportFragmentManager();
//                fm.beginTransaction()
//                        .replace(R.id.,fragment)
//                        .commit();

                drawerLayout.closeDrawers();
                return true;
            }
        });

    }


    // Abre el drawer al clickear el burger
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // Infla el menu dentro del toolbar (settings, iconos)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
