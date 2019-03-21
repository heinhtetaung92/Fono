package algo.com.fono.devices;

import java.util.List;

import algo.com.fono.Event;
import algo.com.fono.data.Device;
import algo.com.fono.data.source.DevicesRepository;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DevicesViewModel extends ViewModel {

    private MutableLiveData<List<Device>> mItems = new MutableLiveData<>();

    private final MutableLiveData<Event<Integer>> mOpenDeviceEvent = new MutableLiveData<>();

    public DevicesViewModel(DevicesRepository mDevicesRepository) {

    }

    public void start() {

    }

    public void loadDevices() {

    }

    public MutableLiveData<Event<Integer>> getOpenDeviceEvent() {
        return mOpenDeviceEvent;
    }

    public void openDeviceDetail(int deviceId) {
        mOpenDeviceEvent.setValue(new Event<Integer>(deviceId));
    }
}
