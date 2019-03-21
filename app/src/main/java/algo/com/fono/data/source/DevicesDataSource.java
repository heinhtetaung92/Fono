package algo.com.fono.data.source;

import java.util.List;

import algo.com.fono.data.Device;
import androidx.annotation.NonNull;

public interface DevicesDataSource {

    interface LoadDevicesCallback {

        void onDevicesLoaded(List<Device> Devices);

        void onDataNotAvailable();
    }

    interface GetDeviceCallback {

        void onDeviceLoaded(Device Device);

        void onDataNotAvailable();
    }

    void getDevices(@NonNull LoadDevicesCallback callback);

    void getDevice(@NonNull String DeviceId, @NonNull GetDeviceCallback callback);

}
