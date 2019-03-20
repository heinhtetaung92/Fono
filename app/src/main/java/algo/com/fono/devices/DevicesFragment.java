package algo.com.fono.devices;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import algo.com.fono.model.Device;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import algo.com.fono.R;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DevicesFragment extends Fragment {

    private static final int DATASET_COUNT = 60;

    private OnFragmentInteractionListener mListener;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_devices, container, false);

        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());

        mAdapter = new DevicesAdapter(mDataset);
        mAdapter.setListener(mListener);
        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

//    public void onDeviceSelected(int deviceId) {
//        if (mListener != null) {
//            mListener.onDeviceSelected(deviceId);
//        }
//    }

    private void initDataset() {
        mDataset = new ArrayList<>();
        for (int i = 0; i < DATASET_COUNT; i++) {
            mDataset.add(new Device());
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onDeviceSelected(int deviceId);
    }
}
