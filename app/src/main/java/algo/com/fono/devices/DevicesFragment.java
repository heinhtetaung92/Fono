package algo.com.fono.devices;

import android.content.Context;
import android.os.Bundle;

import algo.com.fono.data.Device;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import algo.com.fono.R;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DevicesFragment extends Fragment {

    private static final int DATASET_COUNT = 60;

    private DevicesViewModel mDevicesViewModel;

    protected RecyclerView mRecyclerView;
    protected DevicesAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<Device> mDataset;

    public DevicesFragment() {
        // Required empty public constructor
    }

    public static DevicesFragment newInstance() {
        DevicesFragment fragment = new DevicesFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mDevicesViewModel.start();
        mDevicesViewModel.getItems().observeForever(new Observer<List<Device>>() {
            @Override
            public void onChanged(List<Device> devices) {
                mDataset = devices;
                if (mAdapter != null) {
                    mAdapter.setDataSet(mDataset);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_devices, container, false);

        mDevicesViewModel = DevicesActivity.obtainViewModel(getActivity());

        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        setupRecyclerView();

        return rootView;
    }

    private void setupRecyclerView() {
        mLayoutManager = new LinearLayoutManager(getActivity());

        mAdapter = new DevicesAdapter(mDataset);
        mAdapter.setListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(int deviceId) {
                mDevicesViewModel.openDeviceDetail(deviceId);
            }
        });
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public interface OnItemClickListener {
        void onItemClicked(int deviceId);
    }

}
