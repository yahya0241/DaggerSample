package com.example.daggerpractice.di.main;

import com.example.daggerpractice.di.ViewModelKey;
import com.example.daggerpractice.ui.main.posts.PostsViewModel;
import com.example.daggerpractice.ui.main.profile.ProfileViewModel;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel.class)
    public abstract ViewModel bindPostViweModel(PostsViewModel viewModel);
}
