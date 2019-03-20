/*
 *  Copyright 2017 Google Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package algo.com.fono;

import android.annotation.SuppressLint;
import android.app.Application;

import algo.com.fono.data.source.DevicesRepository;
import algo.com.fono.devicedetail.DeviceDetailViewModel;
import algo.com.fono.devices.DevicesViewModel;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * A creator is used to inject the product ID into the ViewModel
 * <p>
 * This creator is to showcase how to inject dependencies into ViewModels. It's not
 * actually necessary in this case, as the product ID can be passed in a public method.
 */
public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @SuppressLint("StaticFieldLeak")
    private static volatile ViewModelFactory INSTANCE;

    private final DevicesRepository mDevicesRepository;

    public static ViewModelFactory getInstance(Application application) {

        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(
                            Injection.provideTasksRepository(application.getApplicationContext()));
                }
            }
        }
        return INSTANCE;
    }

    public DevicesRepository getTasksRepository() {
        return mDevicesRepository;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    private ViewModelFactory(DevicesRepository repository) {
        mDevicesRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DevicesViewModel.class)) {
            //noinspection unchecked
            return (T) new DevicesViewModel(mDevicesRepository);
        } else if (modelClass.isAssignableFrom(DeviceDetailViewModel.class)) {
            //noinspection unchecked
            return (T) new DeviceDetailViewModel(mDevicesRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
