package algo.com.fono.di.modules;

import android.content.Context;

import algo.com.fono.di.interfaces.ApplicationContext;
import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @ApplicationContext
    @Provides
    public Context context(){ return context.getApplicationContext(); }

}
