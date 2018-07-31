package com.sample;

import android.app.Application;

import com.sample.dagger.component.DaggerNetComponent;
import com.sample.dagger.component.NetComponent;
import com.sample.dagger.module.AppModule;
import com.sample.dagger.module.LocationModule;
import com.sample.dagger.module.NetModule;

public class App extends Application {
    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("", this))
                .locationModule(new LocationModule(this))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}