package com.bloodhouse.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bloodhouse.R;
import com.bloodhouse.database.userinfo;
import com.bloodhouse.utils.helper_variables;
import com.bloodhouse.view_holders.userdetail_view_holder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.common.collect.Maps;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class bloodgroupDetailsFragment extends Fragment {



    private RecyclerView _myRecycler;
    private FirebaseAuth auth;


    private FirebaseFirestore databaseReferencef;
    FirestoreRecyclerAdapter<userinfo, userdetail_view_holder> firestoreRecyclerAdapter;



String  number = "tel:";

    public bloodgroupDetailsFragment() {
        // Required empty public constructor
    }


    public static bloodgroupDetailsFragment newInstance() {
        bloodgroupDetailsFragment fragment = new bloodgroupDetailsFragment();

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
        View rootView = inflater.inflate(R.layout.fragment_bloodgroup_details, container, false);


        _myRecycler = rootView.findViewById(R.id.RecyclerView);
        databaseReferencef = FirebaseFirestore.getInstance();

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        _myRecycler.setLayoutManager(llm);

        auth = FirebaseAuth.getInstance();

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Query query = databaseReferencef.collection(helper_variables.bloodgroup_identifier);

        try{
        FirestoreRecyclerOptions<userinfo> response = new FirestoreRecyclerOptions.Builder<userinfo>()
                .setQuery(query, userinfo.class)
                .build();

        firestoreRecyclerAdapter =
                new FirestoreRecyclerAdapter<userinfo, userdetail_view_holder>(
                        response
                ) {
                    @Override
                    protected void onBindViewHolder(userdetail_view_holder holder, int position, userinfo model) {




                        holder.set_cardBloodGroup(model.getBloodGroup());

                        holder.set_cardCity(model.getCity());
                        holder.set_cardName(model.getFirstName() + " " + model.getLastName());

                        holder.set_cardContact(model.getContactNumber());

                        if(model.getAvailability()){
                            holder.set_cardAvailability("AVAILABLE to donate");
                        }else{
                            holder.set_cardAvailability("NOT AVAILABLE to donate");

                        }

                        holder._buttonLocation.setOnClickListener(v->{
                            Toast.makeText(getContext(), model.getLattitude() + " " + model.getLongitude(),  Toast.LENGTH_SHORT).show();

                            helper_variables.bloodgroup_identifier = "AB+";

                            helper_variables.lat=model.getLattitude();
                            helper_variables.lon=model.getLongitude();




                            startActivity(new Intent(getContext(), MapsActivity.class));

//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                            transaction.replace(R.id.frame_layout, locationFragment.newInstance());
//                            transaction.commit();
                        });


                        holder._btnCall.setOnClickListener(v->{

                            number = number + model.getContactNumber();
                            onCall();
                        });

                    }

                    @NonNull
                    @Override
                    public userdetail_view_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


                        View view = LayoutInflater.from(viewGroup.getContext())
                                .inflate(R.layout.user_detail_card, viewGroup, false);


                        return new userdetail_view_holder(view);

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


    public void onCall() {
        int permissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    new String[]{Manifest.permission.CALL_PHONE},
                    123);
        } else {
            startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse(number)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case 123:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    onCall();
                } else {

                    Toast.makeText(getContext(), "Call Permission Not Granted", Toast.LENGTH_SHORT).show();
                }
                break;


            default:
                break;
        }
    }

}
