package algo.com.fono.di.components;

import algo.com.fono.data.source.remote.DevicesApi;
import algo.com.fono.di.modules.DevicesApiModule;
import dagger.Component;

@Component(modules = DevicesApiModule.class)
public interface DevicesApiComponent {
    DevicesApi getDevicesApiService();
}
