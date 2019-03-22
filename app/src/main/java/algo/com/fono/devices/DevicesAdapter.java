package algo.com.fono.devices;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import algo.com.fono.R;
import algo.com.fono.data.Device;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DevicesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Device> deviceList;
    private DevicesFragment.OnItemClickListener mListener;

    public DevicesAdapter() {
        deviceList = new ArrayList<>();
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
                    mListener.onItemClicked(curDevice.getId());
                }
            }
        });
        vh.setDeviceName(curDevice.getDeviceName());
        vh.setDeviceBrand(curDevice.getBrand());
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }

    public void setListener(DevicesFragment.OnItemClickListener listener) {
        this.mListener = listener;
    }

    public void setDataSet(List<Device> dataSet) {
        if (dataSet != null) {
            this.deviceList = dataSet;
        } else {
            this.deviceList = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView deviceName, deviceBrand;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            deviceName = itemView.findViewById(R.id.device_name);
            deviceBrand = itemView.findViewById(R.id.device_brand);
        }

        void setDeviceName(String name) {
            if (!TextUtils.isEmpty(name)) {
                deviceName.setText(name);
            }
        }

        void setDeviceBrand(String brand) {
            if (!TextUtils.isEmpty(brand)) {
                deviceBrand.setText(brand);
            }
        }

        void setOnClickListener(View.OnClickListener listener) {
            if (listener != null) {
                itemView.setOnClickListener(listener);
            }
        }

    }

}
