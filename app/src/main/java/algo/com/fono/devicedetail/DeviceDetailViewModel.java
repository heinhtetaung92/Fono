package algo.com.fono.devicedetail;

import android.util.Log;

import algo.com.fono.data.Device;
import algo.com.fono.data.source.DevicesDataSource;
import algo.com.fono.data.source.DevicesRepository;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DeviceDetailViewModel extends ViewModel implements DevicesDataSource.GetDeviceCallback {

    private final MutableLiveData<Device> mDevice = new MutableLiveData<>();

    private DevicesRepository mRepository;

    public DeviceDetailViewModel(DevicesRepository mDevicesRepository) {
        mRepository = mDevicesRepository;
    }

    public void start(int deviceId) {
        mRepository.getDevice(deviceId, this);
    }

    public void setDevice(Device device) {
        mDevice.setValue(device);
    }

    public MutableLiveData<Device> getDevice() {
        return mDevice;
    }

    @Override
    public void onDeviceLoaded(Device device) {
        setDevice(device);
    }

    @Override
    public void onDataNotAvailable() {

    }

    public void setFakeDevice() {
        setDevice(new Device("Oneplus", "OnePlus 6t", "Oxygen", 99));
    }
}
