package com.bloodhouse.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bloodhouse.R;


public class addDonorFragment extends Fragment {


    public addDonorFragment() {
        // Required empty public constructor
    }

    public static addDonorFragment newInstance() {
        addDonorFragment fragment = new addDonorFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_donor, container, false);

        return rootView;
    }


}
