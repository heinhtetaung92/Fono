package algo.com.fono.devices;

import java.util.List;

import algo.com.fono.Event;
import algo.com.fono.data.Device;
import algo.com.fono.data.source.DevicesDataSource;
import algo.com.fono.data.source.DevicesRepository;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DevicesViewModel extends ViewModel {

    private MutableLiveData<List<Device>> mItems = new MutableLiveData<>();

    private final MutableLiveData<Event<Integer>> mOpenDeviceEvent = new MutableLiveData<>();

    private final DevicesRepository mDevicesRepository;

    public DevicesViewModel(DevicesRepository repo) {
        mDevicesRepository = repo;
    }

    public void start() {
        loadDevices();
    }

    public void loadDevices() {
        mDevicesRepository.getDevices(new DevicesDataSource.LoadDevicesCallback() {
            @Override
            public void onDevicesLoaded(List<Device> devices) {
                mItems.setValue(devices);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    public MutableLiveData<List<Device>> getItems() {
        return mItems;
    }

    public MutableLiveData<Event<Integer>> getOpenDeviceEvent() {
        return mOpenDeviceEvent;
    }

    public void openDeviceDetail(int deviceId) {
        mOpenDeviceEvent.setValue(new Event<Integer>(deviceId));
    }
}
