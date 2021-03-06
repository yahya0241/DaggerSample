package com.example.daggerpractice.ui.main.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.daggerpractice.R;
import com.example.daggerpractice.models.User;
import com.example.daggerpractice.ui.BaseFragment;
import com.example.daggerpractice.ui.auth.AuthResource;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class ProfileFragment extends BaseFragment {
    private static final String TAG = "ProfileFragment";

    private ProfileViewModel viewModel;
    private TextView userName, email, website;

    @Inject
    ViewModelProvider viewModelProvider;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        userName = view.findViewById(R.id.username);
        email = view.findViewById(R.id.email);
        website = view.findViewById(R.id.website);
        viewModel = viewModelProvider.get(ProfileViewModel.class);
        subscribeObservers();
    }

    private void subscribeObservers() {
        viewModel.getAuthenticatedUser().removeObservers(getViewLifecycleOwner());
        viewModel.getAuthenticatedUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case AUTHENTICATED: {
                            setUserDetail(userAuthResource.data);
                            break;
                        }
                        case ERROR: {
                            setErrorDetail(userAuthResource.message);
                        }
                    }
                }
            }
        });

    }

    private void setErrorDetail(String message) {
        email.setText(message);
        userName.setText(R.string.error);
        website.setText(R.string.error);
    }

    private void setUserDetail(User user) {
        userName.setText(user.getUsername());
        email.setText(user.getEmail());
        website.setText(user.getWebsite());
    }
}
