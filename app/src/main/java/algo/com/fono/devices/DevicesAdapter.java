package algo.com.fono.devices;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import algo.com.fono.R;
import algo.com.fono.data.Device;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DevicesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Device> deviceList;
    private DevicesFragment.OnFragmentInteractionListener mListener;

    public DevicesAdapter(List<Device> mDataset) {
        setDataSet(mDataset);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_device_row, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolder vh = (ViewHolder) holder;
        final Device curDevice = deviceList.get(position);

        vh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onDeviceSelected(curDevice.getId());
                }
            }
        });
        vh.setIdView(curDevice.getId());
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }

    public void setListener(DevicesFragment.OnFragmentInteractionListener mListener) {
        this.mListener = mListener;
    }

    public void setDataSet(List<Device> dataSet) {
        if (dataSet != null) {
            this.deviceList = dataSet;
        } else {
            this.deviceList = new ArrayList<>();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView idView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            idView = itemView.findViewById(R.id.show_id);
        }

        void setIdView(int id) {
            idView.setText(String.valueOf(id));
        }

        void setOnClickListener(View.OnClickListener listener) {
            if (listener != null) {
                itemView.setOnClickListener(listener);
            }
        }

    }

}
