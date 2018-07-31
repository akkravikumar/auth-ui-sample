package com.sample.dagger.component;

import com.sample.activity.DrawerActivity;
import com.sample.activity.LoginActivity;
import com.sample.activity.ProfilesActivity;
import com.sample.dagger.module.AppModule;
import com.sample.dagger.module.LocationModule;
import com.sample.dagger.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class, LocationModule.class})
public interface NetComponent {
    void inject(LoginActivity activity);
    void inject(DrawerActivity activity);
    void inject(ProfilesActivity activity);
}
