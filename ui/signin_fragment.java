package com.bloodhouse.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.bloodhouse.R;
import com.google.firebase.auth.FirebaseAuth;

import static android.content.Context.MODE_PRIVATE;

public class signin_fragment extends Fragment {
    private EditText editText;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public signin_fragment() {
        // Required empty public constructor
    }


    public static signin_fragment newInstance() {
        signin_fragment fragment = new signin_fragment();





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
        View rootView = inflater.inflate(R.layout.fragment_signin_fragment, container, false);


        sharedPreferences=getActivity().getSharedPreferences("Phone",MODE_PRIVATE);
       editor=sharedPreferences.edit();




        editText = rootView.findViewById(R.id.editTextPhone);

        rootView.findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNumber = editText.getText().toString().trim();

                if (phoneNumber.isEmpty() || phoneNumber.length() < 10) {
                    editText.setError("Valid number is required");
                    editText.requestFocus();
                    return;
                }


                editor.putString("number",phoneNumber);
                editor.apply();

                Intent intent = new Intent(getContext(), VerifyPhoneCodeActivity.class);

                intent.putExtra("phonenumber",phoneNumber);
                startActivity(intent);

            }
        });







        return rootView;

    }
    @Override
    public void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, signupFragment.newInstance());
            transaction.commit();
        }
    }

}
