package algo.com.fono.devices;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import algo.com.fono.R;
import algo.com.fono.ViewModelFactory;
import algo.com.fono.devicedetail.DeviceDetailActivity;
import algo.com.fono.utily.ActivityUtils;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

public class DevicesActivity extends AppCompatActivity implements DevicesFragment.OnFragmentInteractionListener {

    private DrawerLayout mDrawerLayout;

    private DevicesViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);

        setupToolbar();
        setupNavigationDrawer();
        setupViewFragment();
        mViewModel = obtainViewModel(this);

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void setupNavigationDrawer() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        NavigationView navigationView = findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            default:
                                break;
                        }
                        // Close the navigation drawer when an item is selected.
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    public static DevicesViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        DevicesViewModel viewModel =
                ViewModelProviders.of(activity, factory).get(DevicesViewModel.class);

        return viewModel;
    }

    private void setupViewFragment() {
        DevicesFragment devicesFragment =
                (DevicesFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (devicesFragment == null) {
            devicesFragment = DevicesFragment.newInstance();
            ActivityUtils.replaceFragmentInActivity(
                    getSupportFragmentManager(), devicesFragment, R.id.contentFrame);
        }
    }

    @Override
    public void onDeviceSelected(int deviceId) {
        Intent intent = new Intent(this, DeviceDetailActivity.class);
        startActivity(intent);
    }
}
