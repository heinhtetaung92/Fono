package algo.com.fono.devicedetail;

import algo.com.fono.R;
import algo.com.fono.utily.ActivityUtils;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.net.Uri;
import android.os.Bundle;

public class DeviceDetailActivity extends AppCompatActivity implements DeviceDetailFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_detail);

        setupToolbar();
        setupViewFragment();
    }


    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
    }

    private void setupViewFragment() {
        DeviceDetailFragment detailFragment =
                (DeviceDetailFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (detailFragment == null) {
            // Create the fragment
            detailFragment = DeviceDetailFragment.newInstance();
            ActivityUtils.replaceFragmentInActivity(
                    getSupportFragmentManager(), detailFragment, R.id.contentFrame);
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
