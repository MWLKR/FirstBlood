package com.bloodhouse.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.bloodhouse.R;
import com.bloodhouse.database.bloodrequests;
import com.bloodhouse.database.userinfo;
import com.bloodhouse.utils.helper_variables;
import com.bloodhouse.view_holders.postrequest_view_holder;
import com.bloodhouse.view_holders.userdetail_view_holder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class RequestListFragment extends Fragment {


    Button _requestBtn;


    private RecyclerView _myRecycler;
    private FirebaseAuth auth;


    private FirebaseFirestore databaseReferencef;
    FirestoreRecyclerAdapter<bloodrequests, postrequest_view_holder> firestoreRecyclerAdapter;




    public RequestListFragment() {
        // Required empty public constructor
    }


    public static RequestListFragment newInstance() {
        RequestListFragment fragment = new RequestListFragment();

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
        View rootView = inflater.inflate(R.layout.fragment_request_list, container, false);

        _requestBtn = rootView.findViewById(R.id.buttonRequest);







        _requestBtn.setOnClickListener(v->{


            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, Requests.newInstance());
            transaction.commit();
        });
        _myRecycler = rootView.findViewById(R.id.RecyclerView);
        databaseReferencef = FirebaseFirestore.getInstance();

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        _myRecycler.setLayoutManager(llm);

        auth = FirebaseAuth.getInstance();

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Query query = databaseReferencef.collection("BloodRequests");

        try{
            FirestoreRecyclerOptions<bloodrequests> response = new FirestoreRecyclerOptions.Builder<bloodrequests>()
                    .setQuery(query, bloodrequests.class)
                    .build();

            firestoreRecyclerAdapter =
                    new FirestoreRecyclerAdapter<bloodrequests, postrequest_view_holder>(
                            response
                    ) {
                        @Override
                        protected void onBindViewHolder(postrequest_view_holder holder, int position, bloodrequests model) {





                            holder.set_bloodBag(model.getBloodBagRequired());
                            holder.set_bloodGroup(model.getBloodGroup());
                            holder.set_contactNum(model.getContactNumber());
                            holder.set_date(model.getDate());
                            holder.set_kota(model.getkota());
                            holder.set_hospitalName(model.getNameOfHospital());
                            holder.set_requestStatus(model.getRequestStatus());
                            holder.set_name(model.getName());
                            holder.set_reasonodBlood(model.getReasonOfRequesting());





//



                        }

                        @NonNull
                        @Override
                        public postrequest_view_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


                            View view = LayoutInflater.from(viewGroup.getContext())
                                    .inflate(R.layout.request_card, viewGroup, false);


                            return new postrequest_view_holder(view);

                        }
                    };
            _myRecycler.setAdapter(firestoreRecyclerAdapter);
        }catch (Exception e){
            //  Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return rootView;

    }



    @Override
    public void onStart() {
        super.onStart();

        firestoreRecyclerAdapter.startListening();

    }



    @Override
    public void onStop() {
        super.onStop();


        firestoreRecyclerAdapter.stopListening();

    }



}
