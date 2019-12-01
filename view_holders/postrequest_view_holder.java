package com.bloodhouse.view_holders;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bloodhouse.R;

public class postrequest_view_holder extends RecyclerView.ViewHolder {

    private View myView;
    public TextView bloodBag;
    public TextView bloodGroup;
    public TextView contactNum;
    public TextView date;
    public TextView kota;
    public TextView name;
    public TextView nameofhospital;
    public TextView reasonodBlood;
    public TextView requestStatus;



    public Button _btnCall;


    public postrequest_view_holder(@NonNull View itemView) {
        super(itemView);
        myView = itemView;


         bloodBag = myView.findViewById(R.id.bloodAmount);
          bloodGroup = myView.findViewById(R.id.bloodGroup);
          contactNum = myView.findViewById(R.id.contactNumber);

          date = myView.findViewById(R.id.date);
          kota = myView.findViewById(R.id.kota);
          name = myView.findViewById(R.id.name);
          nameofhospital = myView.findViewById(R.id.nameOfhospital);
          reasonodBlood= myView.findViewById(R.id.requestReson);
          requestStatus= myView.findViewById(R.id.statusOfRequest);


    }


    public String get_bloodBag() {
        return bloodBag.getText().toString();
    }

    public void set_bloodBag(String _text) {

        this.bloodBag.setText(_text );
    }




    public String get_bloodGroup() {
        return bloodGroup.getText().toString();
    }

    public void set_bloodGroup(String _text) {

        this.bloodGroup.setText(_text );
    }




    public String get_contactNum() {
        return contactNum.getText().toString();
    }

    public void set_contactNum(String _text) {

        this.contactNum.setText(_text );
    }




    public String get_date() {
        return date.getText().toString();
    }

    public void set_date(String _text) {

        this.date.setText(_text );
    }




    public String get_kota() {
        return kota.getText().toString();
    }

    public void set_kota(String _text) {

        this.kota.setText(_text );
    }




    public String get_name() {
        return name.getText().toString();
    }

    public void set_name(String _text) {

        this.name.setText(_text );
    }





    public String get_hospitalName() {
        return nameofhospital.getText().toString();
    }

    public void set_hospitalName(String _text) {

        this.nameofhospital.setText(_text );
    }




    public String get_reasonodBlood() {
        return reasonodBlood.getText().toString();
    }

    public void set_reasonodBlood(String _text) {

        this.reasonodBlood.setText(_text );
    }




    public String get_requestStatus() {
        return requestStatus.getText().toString();
    }

    public void set_requestStatus(String _text) {

        this.requestStatus.setText(_text );
    }



}
