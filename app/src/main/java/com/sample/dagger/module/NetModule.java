package com.sample.dagger.module;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {
    String mBaseUrl;
    private final Context context;

    public NetModule(String mBaseUrl, Context context) {
        this.mBaseUrl = mBaseUrl;
        this.context = context;
    }

    @Provides
    @Singleton
    @Named("context")
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor interceptor) {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
//        okBuilder.cache(cache);
        okBuilder.addInterceptor(interceptor);
        return okBuilder.build();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    ConnectivityManager provideConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Provides
    @Singleton
    ProgressDialog provideProgressDialog(Context context) {
        return  new ProgressDialog(context);
    }
}
