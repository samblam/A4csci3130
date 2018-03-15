package com.acme.a3csci3130;
/** Shows all of the detail of an individual business
 * Also offers functionality for Business Info to be updated, or deleted entirely
        * @author Sam Barefoot and Teaching staff of CSCI 3130
        * @author B00721422
        * @version 1.0
        *
        */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailViewActivity extends Activity {

    private EditText nameField, businessNumberField, addressField, primaryTypeField, provinceField;

    Business receivedBusinessInfo;
    Button update;
    Button delete;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    /**
     * Dictates what's supposed to be displayed, initiated, or created when the activity starts up
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedBusinessInfo = (Business)getIntent().getSerializableExtra("businesses");
        update = (Button) findViewById(R.id.updateButton);
        delete = (Button) findViewById(R.id.deleteButton);

        nameField = (EditText) findViewById(R.id.name);
        businessNumberField = (EditText) findViewById(R.id.businessnumber);
        addressField = (EditText) findViewById(R.id.address);
        primaryTypeField = (EditText) findViewById(R.id.primarybusiness);
        provinceField = (EditText) findViewById(R.id.province);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();


        if(receivedBusinessInfo != null){
            nameField.setText(receivedBusinessInfo.name);
            businessNumberField.setText(receivedBusinessInfo.businessnum);
           addressField.setText(receivedBusinessInfo.address);
            primaryTypeField.setText(receivedBusinessInfo.businesstype);
            provinceField.setText(receivedBusinessInfo.province);
        }
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateContact(view);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eraseContact(view);
            }
        });
    }

    /**
     * updates Business information in Firebase
     * @param v
     */
    public void updateContact(View v) {

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRef.child("businesses").child(receivedBusinessInfo.ID).child("name").setValue(String.valueOf(nameField.getText()));
        myRef.child("businesses").child(receivedBusinessInfo.ID).child("address").setValue(String.valueOf(addressField.getText()));
        myRef.child("businesses").child(receivedBusinessInfo.ID).child("businessnum").setValue(String.valueOf(businessNumberField.getText()));
        myRef.child("businesses").child(receivedBusinessInfo.ID).child("businesstype").setValue(String.valueOf(primaryTypeField.getText()));
        myRef.child("businesses").child(receivedBusinessInfo.ID).child("province").setValue(String.valueOf(provinceField.getText()));
        finish();
    }

    /**
     * Erases Contact from Firebase
     * @param v
     */
    public void eraseContact(View v)
    {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRef.child("businesses").child(receivedBusinessInfo.ID).removeValue();
        finish();
    }
}
