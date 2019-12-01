package com.bloodhouse.ui;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bloodhouse.R;
import com.bloodhouse.database.userinfo;
import com.bloodhouse.location.GET_CURRENT_LOCATION;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button _continue;
    EditText _firstName;
    EditText _lastName;
    EditText _city;
    EditText _age;
    EditText _contactNumber;

    double _latitude = 0;
    double _longitude = 0;

    String _selectedBloodGroup;


    String[] bloodGroupNames={"Blood Group","A+","A-","B+","B-","AB+","AB-","O+","O-"};

    Spinner bloodGroups;

    protected LocationManager locationManager;

    private userinfo _userinfo;

    AlertDialog dialog;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final GET_CURRENT_LOCATION mylocation=new GET_CURRENT_LOCATION(this);
        _latitude = mylocation.getLatitude();
        _longitude = mylocation.getLongitude();
        auth = FirebaseAuth.getInstance();


         _continue = findViewById(R.id.buttonContinue);
         _firstName = findViewById(R.id.firstName);
         _lastName= findViewById(R.id.lastName);
         _city= findViewById(R.id.city);
         _age= findViewById(R.id.age);
         _contactNumber= findViewById(R.id.contactNumber);


        FirebaseFirestore db = FirebaseFirestore.getInstance();


        AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.progresss_dialog);
        dialog = builder.create();


        bloodGroups = findViewById(R.id.bloodGroup);
        bloodGroups.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,bloodGroupNames);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        bloodGroups.setAdapter(arrayAdapter);


        _continue.setOnClickListener(v->{

            dialog.show();

            Toast.makeText(this, _selectedBloodGroup, Toast.LENGTH_SHORT).show();

            if(_selectedBloodGroup.equals("Blood Group")){
                Toast.makeText(this, "Please select blood group", Toast.LENGTH_SHORT).show();
                dialog.hide();

                return;
            }

            if(TextUtils.isEmpty(_firstName.getText().toString())){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                dialog.hide();

                return;
            }
            if(TextUtils.isEmpty(_lastName.getText().toString())){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                dialog.hide();

                return;
            }
            if(TextUtils.isEmpty(_city.getText().toString())){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                dialog.hide();

                return;
            }
            if(TextUtils.isEmpty(_age.getText().toString())){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                dialog.hide();

                return;
            }
            if(TextUtils.isEmpty(_contactNumber.getText().toString())){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                dialog.hide();

                return;
            }

            _userinfo = new userinfo();
            _userinfo.setAge(_age.getText().toString());
            _userinfo.setAvailability(true);
            _userinfo.setCity(_city.getText().toString());
            _userinfo.setContactNumber(_contactNumber.getText().toString());
            _userinfo.setBloodGroup(_selectedBloodGroup);
            _userinfo.setFirstName(_firstName.getText().toString());
            _userinfo.setLastName(_lastName.getText().toString());
            _userinfo.setLattitude(_latitude);
            _userinfo.setLongitude(_longitude);


            db.collection(_selectedBloodGroup)
                    .document(auth.getCurrentUser().getUid())
                    .set(_userinfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(SignupActivity.this, "Data Stored", Toast.LENGTH_SHORT).show();
                    dialog.hide();

                    startActivity(new Intent(SignupActivity.this,MainActivity.class));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignupActivity.this, "Failed to enter data", Toast.LENGTH_SHORT).show();

                }
            });



            

        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        _selectedBloodGroup = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}

