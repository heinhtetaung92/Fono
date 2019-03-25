package algo.com.fono.di.modules;

import com.google.gson.Gson;

import algo.com.fono.data.source.remote.DevicesApi;
import algo.com.fono.utily.UrlUtils;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = OkHttpClientModule.class)
public class DevicesApiModule {

    @Provides
    public DevicesApi devicesApi(Retrofit retrofit) {
        return retrofit.create(DevicesApi.class);
    }

    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(UrlUtils.base_url)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    public Gson gson(){
        return new Gson();
    }

    @Provides
    public GsonConverterFactory gsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

}
