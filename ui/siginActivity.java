package com.bloodhouse.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bloodhouse.R;
import com.google.firebase.auth.FirebaseAuth;

public class siginActivity extends AppCompatActivity {
    private EditText editText;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin);

        sharedPreferences=getSharedPreferences("Phone",MODE_PRIVATE);
        editor=sharedPreferences.edit();




        editText = findViewById(R.id.editTextPhone);

        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
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

                Intent intent = new Intent(siginActivity.this, VerifyPhoneCodeActivity.class);

                intent.putExtra("phonenumber",phoneNumber);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, SignupActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }
}
