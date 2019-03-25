package algo.com.fono.devices;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import algo.com.fono.Event;
import algo.com.fono.R;
import algo.com.fono.ViewModelFactory;
import algo.com.fono.devicedetail.DeviceDetailActivity;
import algo.com.fono.utily.ActivityUtils;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class DevicesActivity extends AppCompatActivity implements DeviceItemNavigator {

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

        mViewModel.getOpenDeviceEvent().observeForever(new Observer<Event<Integer>>() {
            @Override
            public void onChanged(Event<Integer> event) {
                Integer deviceId = event.getContentIfNotHandled();
                if (deviceId != null) {
                    openDeviceDetails(deviceId);
                }
            }
        });

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
    public void openDeviceDetails(int deviceId) {
        Intent intent = new Intent(this, DeviceDetailActivity.class);
        intent.putExtra(DeviceDetailActivity.EXTRA_DEVICE_ID, deviceId);
        startActivity(intent);
    }

    @RequiresApi(28)
    private static class OnUnhandledKeyEventListenerWrapper implements View.OnUnhandledKeyEventListener {
        private ViewCompat.OnUnhandledKeyEventListenerCompat mCompatListener;

        OnUnhandledKeyEventListenerWrapper(ViewCompat.OnUnhandledKeyEventListenerCompat listener) {
            this.mCompatListener = listener;
        }

        public boolean onUnhandledKeyEvent(View v, KeyEvent event) {
            return this.mCompatListener.onUnhandledKeyEvent(v, event);
        }
    }

}
