package algo.com.fono.devices;

import java.util.List;

import algo.com.fono.data.Device;
import algo.com.fono.data.source.DevicesRepository;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DevicesViewModel extends ViewModel {

    private MutableLiveData<List<Device>> mItems = new MutableLiveData<>();

    public DevicesViewModel(DevicesRepository mDevicesRepository) {

    }

    public void start() {

    }

    public void loadDevices() {

    }

}
