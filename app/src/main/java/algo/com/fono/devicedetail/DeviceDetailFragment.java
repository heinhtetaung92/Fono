package algo.com.fono.devicedetail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import algo.com.fono.R;

public class DeviceDetailFragment extends Fragment {

    private DeviceDetailViewModel mDeviceDetailModel;

    public DeviceDetailFragment() {
        // Required empty public constructor
    }

    public static DeviceDetailFragment newInstance() {
        DeviceDetailFragment fragment = new DeviceDetailFragment();
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

        mDeviceDetailModel = DeviceDetailActivity.obtainViewModel(getActivity());

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
