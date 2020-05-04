package com.example.daggerpractice.di.main;

import android.app.Application;

import com.example.daggerpractice.network.main.MainApi;
import com.example.daggerpractice.ui.main.posts.PostRecyclerAdapter;
import com.example.daggerpractice.util.VerticalSpaceItemDecoration;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {
    @Provides
    public MainApi provideMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }

    @Provides
    public PostRecyclerAdapter getAdapter(){
        return new PostRecyclerAdapter();
    }

    @Provides
    public VerticalSpaceItemDecoration getVerticalSpace(){
        return new VerticalSpaceItemDecoration(15);
    }

    @Provides
    public static RecyclerView.LayoutManager getLayoutManager(Application application) {
        return new LinearLayoutManager(application);
    }
}
