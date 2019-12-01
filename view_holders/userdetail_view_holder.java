package com.bloodhouse.view_holders;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bloodhouse.R;

public class userdetail_view_holder extends RecyclerView.ViewHolder {

    private View myView;
    public TextView _cardName;
    public TextView _cardBloodGroup;
    public TextView _cardAvailability;
    public TextView _cardCity;
    public TextView _cardContactNumber;
    public Button _buttonLocation;

    public Button _btnCall;



    public userdetail_view_holder(@NonNull View itemView) {
        super(itemView);
        myView = itemView;

        _cardName = myView.findViewById(R.id.cardName);
        _cardBloodGroup = myView.findViewById(R.id.cardbloodGroup);
        _cardAvailability = myView.findViewById(R.id.cardAvailability);
        _cardCity=myView.findViewById(R.id.cardCity);
        _cardContactNumber = myView.findViewById(R.id.cardContactNumber);
        _buttonLocation = myView.findViewById(R.id.btnLocation);
        _btnCall = myView.findViewById(R.id.btnCall);
    }




    public String get_cardName() {
        return _cardName.getText().toString();
    }

    public void set_cardName(String _text) {

    	this._cardName.setText(_text );
    }




    public String get_cardBloodgroup() {
        return _cardBloodGroup.getText().toString();
    }

    public void set_cardBloodGroup(String _text) {

        this._cardBloodGroup.setText(_text );
    }




    public String get_cardAvailability() {
        return _cardAvailability.getText().toString();
    }

    public void set_cardAvailability(String _text) {

        this._cardAvailability.setText(_text );
    }


    public String get_cardCity() {
        return _cardCity.getText().toString();
    }

    public void set_cardCity(String _text) {

        this._cardCity.setText(_text );
    }


    public String get_cardContact() {
        return _cardContactNumber.getText().toString();
    }

    public void set_cardContact(String _text) {

        this._cardContactNumber.setText(_text );
    }


}

