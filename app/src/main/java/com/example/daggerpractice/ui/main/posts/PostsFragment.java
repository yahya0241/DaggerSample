package com.example.daggerpractice.ui.main.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daggerpractice.R;
import com.example.daggerpractice.models.Post;
import com.example.daggerpractice.ui.BaseFragment;
import com.example.daggerpractice.ui.main.Resource;
import com.example.daggerpractice.util.VerticalSpaceItemDecoration;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

public class PostsFragment extends BaseFragment {
    private static final String TAG = "PostsFragment";

    private RecyclerView recyclerView;
    private PostsViewModel viewModel;

    @Inject
    PostRecyclerAdapter recyclerAdapter;

    @Inject
    VerticalSpaceItemDecoration spaceItemDecoration;

    @Inject
    Provider<RecyclerView.LayoutManager> layoutManagerProvider;

    @Inject
    ViewModelProvider mViewModelProvider;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_view);
        viewModel = mViewModelProvider.get(PostsViewModel.class);

        initRecyclerView();
        subscribeObserver();
    }

    private void subscribeObserver() {
        viewModel.observePosts().removeObservers(getViewLifecycleOwner());
        viewModel.observePosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {
                if (listResource != null) {
                    switch (listResource.status) {
                        case LOADING: {
                            Log.d(TAG, "onChanged: PostsFragment: LOADING...");
                            break;
                        }
                        case ERROR: {
                            Log.e(TAG, "onChanged: Error ..." + listResource.message);
                            break;
                        }
                        case SUCCESS: {
                            recyclerAdapter.setPosts(listResource.data);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(layoutManagerProvider.get());
        recyclerView.addItemDecoration(spaceItemDecoration);
        recyclerView.setAdapter(recyclerAdapter);
    }
}
