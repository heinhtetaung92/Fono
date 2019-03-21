package algo.com.fono.devicedetail;

import android.os.Bundle;

import algo.com.fono.data.Device;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import algo.com.fono.R;
import androidx.lifecycle.Observer;

public class DeviceDetailFragment extends Fragment {

    private final static String DEVICE_ID = "DEVICE_ID";

    private DeviceDetailViewModel mDeviceDetailModel;

    public DeviceDetailFragment() {
        // Required empty public constructor
    }

    public static DeviceDetailFragment newInstance(int deviceId) {
        DeviceDetailFragment fragment = new DeviceDetailFragment();
        Bundle args = new Bundle();
        args.putInt(DEVICE_ID, deviceId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_device_detail, container, false);
        final TextView nameTv = rootView.findViewById(R.id.device_name);
        final TextView brandTv = rootView.findViewById(R.id.device_brand);

        mDeviceDetailModel = DeviceDetailActivity.obtainViewModel(getActivity());

        mDeviceDetailModel.getDevice().observeForever(new Observer<Device>() {
            @Override
            public void onChanged(Device device) {
                nameTv.setText(device.getDeviceName());
                brandTv.setText(device.getBrand());
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        mDeviceDetailModel.start(getArguments().getInt(DEVICE_ID));

    }

}
