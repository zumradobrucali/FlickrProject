package com.dobrucali.photos.ui;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dobrucali.photos.R;
import com.dobrucali.photos.databinding.DetailPhotoFragmentBinding;
import com.dobrucali.photos.viewModel.DetailPhotoViewModel;
import com.squareup.picasso.Picasso;

public class DetailPhotoFragment extends Fragment {

    private DetailPhotoViewModel mViewModel;
    private static final String PHOTO_URL_KEY = "photoUrl";

    static DetailPhotoFragment newInstance(String photoUrl) {
        DetailPhotoFragment detailPhotoFragment = new DetailPhotoFragment();

        Bundle args = new Bundle();
        args.putString(PHOTO_URL_KEY, photoUrl);
        detailPhotoFragment.setArguments(args);
        return detailPhotoFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        DetailPhotoFragmentBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.detail_photo_fragment, container, false);

        if (getArguments() != null && getArguments().containsKey(PHOTO_URL_KEY)) {
            String photoUrl = getArguments().getString(PHOTO_URL_KEY);
            Picasso.with(getContext()).load(photoUrl).into(binding.detailPhotoImageView);
        } else {
            Toast.makeText(getContext(), getString(R.string.url_not_found), Toast.LENGTH_LONG).show();
        }

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetailPhotoViewModel.class);

    }

}
