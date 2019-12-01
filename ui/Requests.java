package com.bloodhouse.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bloodhouse.R;
import com.bloodhouse.database.bloodrequests;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class Requests extends Fragment {



    Spinner _kota;
    Spinner _bloodGroup;
    Spinner _amountOfBlood;

    TextView _fullName;
    TextView _contactNumber;
    TextView _date;
    TextView nameOfhospital;
    TextView reasonForBlood;


    String[] bloodGroupNames={"Blood Group","A+","A-","B+","B-","AB+","AB-","O+","O-"};

    String[] kota={"City","Ambon","Balikpapan","Banda Aceh","Bandar Lampung","Bandung","Banjar","Banjarbaru",
    "Banjarmasin","Batam","Batu","Baubau","Bekasi","Bengkulu","Bima","Binjai","Bitung","Blitar","Bogor","Bontang",
    "Bukittinggi","Cilegon","Cimahi","Cirebon","Denpasar","Depok","Dumai","Gorontalo","Gunungsitoli",
    "Jakarta Barat","Jakarta Pusat","Jakarta Selatan","Jakarta Timur","Jakarta Utara","Jambi","Jayapura",
    "Kediri","Kendari","Kepulauan Tidore","Kotamobagu","Kupang","Langsa","Lhokseumawe","Lubuklinggau",
    "Madiun","Magelang","Makassar","Malang","Manado","Mataram","Medan","Metro","Mojokerto","Padang",
    "Padang Panjang","Pagar Alam","Palangka Raya","Palembang","Palopo","Palu","Pangkalpinang","Parepare",
    "Pariaman ","Pasuruan","Payakumbuh","Pekalongan","Pekanbaru","Pematangsiantar","Pontianak","Prabumulih",
    "Probolinggo","Sabang","Salatiga","Samarinda","Sawahlunto","Semarang","Serang","Sibolga","Sidempuan",
    "Singkawang","Solok","Sorong","Subulussalam","Sukabumi","Sungaipenuh","Surabaya","Surakarta",
    "Tangerang","Tangerang Selatan","Tanjungbalai","Tanjungpinang","Tarakan","Tasikmalaya","Tebing Tinggi",
    "Tegal","Ternate","Tomohon","Tual","Yogyakarta"};
    String[] bagsRequired={"Bags Required","1 bag","2 bag","3 bag","4 bag"};


    Button _postRequest;

    AlertDialog dialog;

    bloodrequests _bloodRequests;

    public Requests() {
        // Required empty public constructor
    }


    public static Requests newInstance() {
        Requests fragment = new Requests();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infvlate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_requests, container, false);

        _bloodGroup = rootView.findViewById(R.id.bloodGroup);
        _kota = rootView.findViewById(R.id.kota);
        _amountOfBlood = rootView.findViewById(R.id.amountOfBlood);

        _postRequest = rootView.findViewById(R.id.requestForBlood);

         _fullName = rootView.findViewById(R.id.fullName);
         _contactNumber = rootView.findViewById(R.id.contactNumber);
         _date = rootView.findViewById(R.id.date);
        nameOfhospital = rootView.findViewById(R.id.nameOfhospital);
        reasonForBlood = rootView.findViewById(R.id.reasonforBlood);

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.progresss_dialog);
        dialog = builder.create();


        _postRequest.setOnClickListener(v->{
            dialog.show();

            if(_bloodGroup.getSelectedItem().toString().equals("Blood Group")){

                Toast.makeText(getContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();

                dialog.hide();

                return;

            }
            if(_bloodGroup.getSelectedItem().toString().equals("kota")){
                Toast.makeText(getContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
                dialog.hide();

                return;
            }
            if(_bloodGroup.getSelectedItem().toString().equals("Bags Required")){
                Toast.makeText(getContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
                dialog.hide();

                return;
            }
            if(TextUtils.isEmpty(_fullName.getText().toString())){
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                dialog.hide();

                return;
            }
            if(TextUtils.isEmpty(_contactNumber.getText().toString())){
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                dialog.hide();

                return;
            }
            if(TextUtils.isEmpty(_date.getText().toString())){
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                dialog.hide();

                return;
            }
            if(TextUtils.isEmpty(nameOfhospital.getText().toString())){
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                dialog.hide();

                return;
            }
            if(TextUtils.isEmpty(reasonForBlood.getText().toString())){
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                dialog.hide();

                return;
            }

            _bloodRequests = new bloodrequests();
            _bloodRequests.setName(_fullName.getText().toString());
            _bloodRequests.setkota(_kota.getSelectedItem().toString());
            _bloodRequests.setBloodGroup(_bloodGroup.getSelectedItem().toString());
            _bloodRequests.setBloodBagRequired(_amountOfBlood.getSelectedItem().toString());
            _bloodRequests.setContactNumber(_contactNumber.getText().toString());
            _bloodRequests.setDate(_date.getText().toString());
            _bloodRequests.setNameOfHospital(nameOfhospital.getText().toString());
            _bloodRequests.setReasonOfRequesting(reasonForBlood.getText().toString());
            _bloodRequests.setRequestStatus("Active");



            db.collection("BloodRequests")
                    .add(_bloodRequests).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(getContext(), "Sucess : Request Posted", Toast.LENGTH_LONG).show();
                    dialog.hide();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "Error : Please contact network provider", Toast.LENGTH_SHORT).show();

                }
            });











        });





        ArrayAdapter<String> arrayAdapterkota = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,kota);
        arrayAdapterkota.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        _kota.setAdapter(arrayAdapterkota);





        ArrayAdapter<String> amountRequired = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,bagsRequired);
        amountRequired.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        _amountOfBlood.setAdapter(amountRequired);







        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,bloodGroupNames);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        _bloodGroup.setAdapter(arrayAdapter);





        return rootView;
    }



}
