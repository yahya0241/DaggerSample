package com.example.daggerpractice.di.main;

import android.app.Application;

import com.example.daggerpractice.network.main.MainApi;
import com.example.daggerpractice.ui.main.MainActivity;
import com.example.daggerpractice.ui.main.posts.PostRecyclerAdapter;
import com.example.daggerpractice.util.VerticalSpaceItemDecoration;
import com.example.daggerpractice.viewmodels.ViewModelProviderFactory;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @MainScope
    @Provides
    static ViewModelProvider provideViewModelProvider(MainActivity activity, ViewModelProviderFactory factory) {
        return new ViewModelProvider(activity, factory);
    }

    @MainScope
    @Provides
    public MainApi provideMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }

    @MainScope
    @Provides
    public PostRecyclerAdapter getAdapter() {
        return new PostRecyclerAdapter();
    }

    @MainScope
    @Provides
    public VerticalSpaceItemDecoration getVerticalSpace() {
        return new VerticalSpaceItemDecoration(15);
    }

    @MainScope
    @Provides
    public static RecyclerView.LayoutManager getLayoutManager(Application application) {
        return new LinearLayoutManager(application);
    }

}
