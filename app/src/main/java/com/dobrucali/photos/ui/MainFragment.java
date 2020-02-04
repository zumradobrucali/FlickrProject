package com.dobrucali.photos.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dobrucali.photos.MainViewModel;
import com.dobrucali.photos.adapter.ItemClickListener;
import com.dobrucali.photos.adapter.PhotoAdapter;
import com.dobrucali.photos.R;
import com.dobrucali.photos.databinding.MainFragmentBinding;
import com.dobrucali.photos.model.*;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private PhotoAdapter photoAdapter;
    private ItemClickListener itemClickListener;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        MainFragmentBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.main_fragment, container, false);

        itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(String photoUrl) {
                Log.i("app", photoUrl);
                openDetailsFragment(photoUrl);
            }
        };


        photoAdapter = new PhotoAdapter(new ArrayList<>(), itemClickListener);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        binding.recentPhotoRecyclerView.setLayoutManager(mLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.recentPhotoRecyclerView.getContext(),DividerItemDecoration.VERTICAL);
        binding.recentPhotoRecyclerView.addItemDecoration(dividerItemDecoration);
        binding.recentPhotoRecyclerView.setAdapter(photoAdapter);

        return binding.getRoot();
    }

    private void openDetailsFragment(String photoUrl) {
        DetailPhotoFragment detailPhotoFragment = DetailPhotoFragment.newInstance(photoUrl);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, detailPhotoFragment, "detailPhotoFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.getRecentPhoto().observe(getViewLifecycleOwner(), recentPhotoResponse -> {
            if (recentPhotoResponse == null) {
                Toast.makeText(getContext(), "Request Failed",Toast.LENGTH_LONG).show();
            } else {
                List<Photo> photoList = recentPhotoResponse.getPhotos().getPhoto();
                photoAdapter.setPhotoList(photoList);
                photoAdapter.notifyDataSetChanged();
            }
        });

    }

}
