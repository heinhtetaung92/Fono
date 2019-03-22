package algo.com.fono.devicedetail;

import android.os.Bundle;

import algo.com.fono.data.Device;
import algo.com.fono.databinding.FragmentDeviceDetailBinding;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeviceDetailModel.setFakeDevice();
            }
        });

        mDeviceDetailModel = DeviceDetailActivity.obtainViewModel(getActivity());

        final FragmentDeviceDetailBinding binding = FragmentDeviceDetailBinding.bind(rootView);
        binding.setViewmodel(mDeviceDetailModel);
        binding.setLifecycleOwner(getActivity());

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        mDeviceDetailModel.start(getArguments().getInt(DEVICE_ID));

    }

}
