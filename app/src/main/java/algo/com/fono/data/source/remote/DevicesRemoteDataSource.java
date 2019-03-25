package algo.com.fono.data.source.remote;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.google.common.collect.Lists;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import algo.com.fono.data.Device;
import algo.com.fono.data.source.DevicesDataSource;
import algo.com.fono.di.components.DaggerDevicesApiComponent;
import algo.com.fono.di.components.DevicesApiComponent;
import algo.com.fono.di.modules.ContextModule;
import algo.com.fono.utily.Constants;
import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class DevicesRemoteDataSource implements DevicesDataSource {

    private static DevicesRemoteDataSource INSTANCE;

    private static final int SERVICE_LATENCY_IN_MILLIS = 2000;

    private final static Map<Integer, Device> DEVICES_SERVICE_DATA;

    DevicesApi devicesApi;

    static {
        DEVICES_SERVICE_DATA = new LinkedHashMap<>(2);
        addDevice("Huawei", "Nova", "", 0);
        addDevice("Huawei", "Nova1", "", 1);
        addDevice("Huawei", "Nova2", "", 2);
        addDevice("Huawei", "Nova3", "", 3);
        addDevice("Huawei", "Nova4", "", 4);
        addDevice("Huawei", "Nova5", "", 5);
        addDevice("Samsung", "Note1", "", 6);
        addDevice("Samsung", "Note2", "", 7);
        addDevice("Samsung", "Note3", "", 8);
        addDevice("Samsung", "Note4", "", 9);
        addDevice("Samsung", "Note5", "", 10);
        addDevice("Samsung", "Note6", "", 11);
        addDevice("Samsung", "Note7", "", 12);
        addDevice("Apple", "Iphone1", "", 13);
        addDevice("Apple", "Iphone2", "", 14);
        addDevice("Apple", "Iphone3", "", 15);
        addDevice("Apple", "Iphone4", "", 16);
        addDevice("Apple", "Iphone5", "", 17);
        addDevice("Apple", "Iphone6", "", 18);
        addDevice("Apple", "Iphone7", "", 19);
        addDevice("Apple", "Iphone8", "", 20);
        addDevice("Apple", "Iphone9", "", 21);
    }

    public static DevicesRemoteDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DevicesRemoteDataSource(context);
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private DevicesRemoteDataSource(Context context) {
        DevicesApiComponent devicesApiComponent = DaggerDevicesApiComponent.builder()
                .contextModule(new ContextModule(context))
                .build();


        devicesApi = devicesApiComponent.getDevicesApiService();
    }

    private static void addDevice(String name, String brand, String technology, int id) {
        Device newDevice = new Device(name, brand, technology, id);
        DEVICES_SERVICE_DATA.put(newDevice.getId(), newDevice);
    }

    @Override
    public void getDevices(@NonNull final LoadDevicesCallback callback) {

        devicesApi.getDevicesList(10, Constants.API_TOKEN).enqueue(new Callback<List<Device>>() {
            @Override
            public void onResponse(Call<List<Device>> call, Response<List<Device>> response) {
                Timber.i(response.message());
            }

            @Override
            public void onFailure(Call<List<Device>> call, Throwable t) {
                Timber.i(t.getMessage());
            }
        });

        // Simulate network by delaying the execution.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onDevicesLoaded(Lists.newArrayList(DEVICES_SERVICE_DATA.values()));
            }
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    @Override
    public void getDevice(@NonNull int deviceId, @NonNull final GetDeviceCallback callback) {
        final Device device = DEVICES_SERVICE_DATA.get(deviceId);

        // Simulate network by delaying the execution.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onDeviceLoaded(device);
            }
        }, SERVICE_LATENCY_IN_MILLIS);
    }
}
