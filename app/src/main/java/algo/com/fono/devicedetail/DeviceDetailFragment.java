package algo.com.fono.devicedetail;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import algo.com.fono.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DeviceDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DeviceDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeviceDetailFragment extends Fragment {

    private DeviceDetailViewModel mDeviceDetailModel;

    private OnFragmentInteractionListener mListener;

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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
