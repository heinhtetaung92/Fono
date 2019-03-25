package algo.com.fono.data.source.remote;

import java.util.List;

import algo.com.fono.data.Device;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DevicesApi {
    //https://fonoapi.freshpixl.com/v1/getlatest?limit=10&token=112d7fa64e14243447c500d09d060904836fbad31c5c43d6

    @GET("/v1/getlatest")
    Call<List<Device>> getDevicesList(@Query("limit") int limit,
                                      @Query("token") String token);

}
