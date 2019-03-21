package algo.com.fono.devicedetail;

import algo.com.fono.R;
import algo.com.fono.ViewModelFactory;
import algo.com.fono.utily.ActivityUtils;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class DeviceDetailActivity extends AppCompatActivity {

    public static final String EXTRA_DEVICE_ID = "DEVICE_ID";
    private DeviceDetailViewModel mDeviceDetailModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_detail);

        setupToolbar();
        setupViewFragment();
        mDeviceDetailModel = obtainViewModel(this);

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
    }

    private void setupViewFragment() {
        DeviceDetailFragment detailFragment = findOrCreateViewFragment();
        ActivityUtils.replaceFragmentInActivity(
                getSupportFragmentManager(), detailFragment, R.id.contentFrame);
    }

    @NonNull
    private DeviceDetailFragment findOrCreateViewFragment() {
        // Get the requested device id
//        String deviceId = getIntent().getStringExtra(EXTRA_DEVICE_ID);

        DeviceDetailFragment deviceDetailFragment = (DeviceDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (deviceDetailFragment == null) {
            int deviceId = getIntent().getExtras().getInt(EXTRA_DEVICE_ID);
            deviceDetailFragment = DeviceDetailFragment.newInstance(deviceId);
        }
        return deviceDetailFragment;
    }

    @NonNull
    public static DeviceDetailViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(DeviceDetailViewModel.class);
    }

}
