package algo.com.fono.data.source;

import java.util.List;

import algo.com.fono.data.Device;
import androidx.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class DevicesRepository implements DevicesDataSource {

    private volatile static DevicesRepository INSTANCE = null;

    private final DevicesDataSource mDevicesLocalDataSource;

    private DevicesRepository(@NonNull DevicesDataSource tasksRemoteDataSource) {
        mDevicesLocalDataSource = checkNotNull(tasksRemoteDataSource);
    }

    public static DevicesRepository getInstance(DevicesDataSource devicesRemoteDataSource) {
        if (INSTANCE == null) {
            synchronized (DevicesRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DevicesRepository(devicesRemoteDataSource);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getDevices(@NonNull final LoadDevicesCallback callback) {
        checkNotNull(callback);

        mDevicesLocalDataSource.getDevices(new LoadDevicesCallback() {
            @Override
            public void onDevicesLoaded(List<Device> devices) {
                callback.onDevicesLoaded(devices);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getDevice(@NonNull String deviceId, @NonNull final GetDeviceCallback callback) {
        checkNotNull(callback);

        mDevicesLocalDataSource.getDevice(deviceId, new GetDeviceCallback() {
            @Override
            public void onDeviceLoaded(Device device) {
                callback.onDeviceLoaded(device);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }
}
