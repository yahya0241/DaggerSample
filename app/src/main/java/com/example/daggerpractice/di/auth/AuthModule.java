package com.example.daggerpractice.di.auth;

import com.example.daggerpractice.models.User;
import com.example.daggerpractice.network.auth.AuthApi;
import com.example.daggerpractice.ui.auth.AuthActivity;
import com.example.daggerpractice.viewmodels.ViewModelProviderFactory;

import javax.inject.Named;

import androidx.lifecycle.ViewModelProvider;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AuthModule {

    @AuthScope
    @Provides
    static AuthApi provideAuthApi(Retrofit retrofit) {
        return retrofit.create(AuthApi.class);
    }

    @AuthScope
    @Provides
    @Named("auth_user")
    static User getUserSample() {
        return new User();
    }

    @AuthScope
    @Provides
    static ViewModelProvider provideViewModelProvider(AuthActivity activity, ViewModelProviderFactory factory) {
        return new ViewModelProvider(activity, factory);
    }
}
