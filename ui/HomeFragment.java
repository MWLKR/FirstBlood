package com.bloodhouse.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bloodhouse.R;
import com.bloodhouse.utils.helper_variables;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


public class HomeFragment extends Fragment {


    Button _b_positive;
    Button _b_negative;

    Button _o_positive;
    Button _o_negative;

    Button _a_positive;
    Button _a_negative;


    Button _ab_positive;
    Button _ab_negative;


    TextView _totalUsers;

    TextView _totalDonors;

    int total_donors = 0;
    private FirebaseFirestore databaseReferencef;


    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        databaseReferencef = FirebaseFirestore.getInstance();


        _b_positive = rootView.findViewById(R.id.btnBpositive);
         _b_negative= rootView.findViewById(R.id.btnBnegative);

         _o_positive= rootView.findViewById(R.id.btnOpositive);
         _o_negative= rootView.findViewById(R.id.btnOnegative);

         _a_positive= rootView.findViewById(R.id.btnApositive);
         _a_negative= rootView.findViewById(R.id.btnAnegative);


         _ab_positive= rootView.findViewById(R.id.btnABpositive);
         _ab_negative= rootView.findViewById(R.id.btnABnegative);
         _totalDonors = rootView.findViewById(R.id.totalDonors);
         _totalUsers = rootView.findViewById(R.id.totalUser);


      //  Toast.makeText(getContext(), getTotalCount() ,Toast.LENGTH_SHORT).show(); getTotalCount();
        getTotalCount();
        _b_positive.setOnClickListener(v ->{

            helper_variables.bloodgroup_identifier = "B+";
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, bloodgroupDetailsFragment.newInstance());
            transaction.commit();
        });


        _b_negative.setOnClickListener(v ->{

            helper_variables.bloodgroup_identifier = "B-";
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, bloodgroupDetailsFragment.newInstance());
            transaction.commit();
        });

        _o_positive.setOnClickListener(v ->{

            helper_variables.bloodgroup_identifier = "O+";
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, bloodgroupDetailsFragment.newInstance());
            transaction.commit();
        });

        _o_negative.setOnClickListener(v ->{

            helper_variables.bloodgroup_identifier = "O-";
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, bloodgroupDetailsFragment.newInstance());
            transaction.commit();
        });

        _a_positive.setOnClickListener(v ->{

            helper_variables.bloodgroup_identifier = "A+";
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, bloodgroupDetailsFragment.newInstance());
            transaction.commit();
        });

        _a_negative.setOnClickListener(v ->{

            helper_variables.bloodgroup_identifier = "A-";
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, bloodgroupDetailsFragment.newInstance());
            transaction.commit();
        });

        _ab_positive.setOnClickListener(v ->{

            helper_variables.bloodgroup_identifier = "AB+";
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, bloodgroupDetailsFragment.newInstance());
            transaction.commit();
        });

        _ab_negative.setOnClickListener(v ->{

            helper_variables.bloodgroup_identifier = "AB-";
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, bloodgroupDetailsFragment.newInstance());
            transaction.commit();
        });

        return rootView;
    }

    private void getTotalCount() {
        databaseReferencef.collection("B+")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        total_donors = total_donors +  queryDocumentSnapshots.size();

                        databaseReferencef.collection("B-")
                                .get()
                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                        total_donors = total_donors +  queryDocumentSnapshots.size();

                                        databaseReferencef.collection("A+")
                                                .get()
                                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                    @Override
                                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                        total_donors = total_donors +  queryDocumentSnapshots.size();

                                                        databaseReferencef.collection("A-")
                                                                .get()
                                                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                                    @Override
                                                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                                        total_donors = total_donors +  queryDocumentSnapshots.size();

                                                                        databaseReferencef.collection("AB+")
                                                                                .get()
                                                                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                                                    @Override
                                                                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                                                        total_donors = total_donors +  queryDocumentSnapshots.size();

                                                                                        databaseReferencef.collection("AB-")
                                                                                                .get()
                                                                                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                                                                    @Override
                                                                                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                                                                        total_donors = total_donors +  queryDocumentSnapshots.size();

                                                                                                        databaseReferencef.collection("O-")
                                                                                                                .get()
                                                                                                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                                                                                    @Override
                                                                                                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                                                                                        total_donors = total_donors +  queryDocumentSnapshots.size();

                                                                                                                        databaseReferencef.collection("O+")
                                                                                                                                .get()
                                                                                                                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                                                                                                    @Override
                                                                                                                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                                                                                                                        total_donors = total_donors +  queryDocumentSnapshots.size();

                                                                                                                                        _totalDonors.setText(total_donors+"");

                                                                                                                                        _totalUsers.setText(total_donors+"");
                                                                                                                                    }
                                                                                                                                });

                                                                                                                    }
                                                                                                                });

                                                                                                    }
                                                                                                });
                                                                                    }
                                                                                });
                                                                    }
                                                                });
                                                    }
                                                });

                                    }
                                });


                    }
                });



    }

}
