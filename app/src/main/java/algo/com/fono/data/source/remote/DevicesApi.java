package algo.com.fono.data.source.remote;

import java.util.List;

import algo.com.fono.data.Device;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DevicesApi {

    @GET("/v1/")
    Call<List<Device>> getDevicesList();

}
