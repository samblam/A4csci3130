package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, businessNumberField, addressField, primaryTypeField, provinceField;

    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        businessNumberField = (EditText) findViewById(R.id.businessnumber);
        addressField = (EditText) findViewById(R.id.address);
        primaryTypeField = (EditText) findViewById(R.id.primarybusiness);
        provinceField = (EditText) findViewById(R.id.province);

    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String businessID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String businessnum = businessNumberField.getText().toString();
        String type = primaryTypeField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();

        Business business = new Business(businessID, name, businessnum ,type,address,province);

        appState.firebaseReference.child(businessID).setValue(business);

        finish();

    }
}
