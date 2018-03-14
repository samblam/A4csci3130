package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class Business implements Serializable {

    public  String uid;
    public  String name;

    public  String businessnum;
    public String businesstype;
    public String address;
    public String province;


    public Business() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public Business(String uid, String name, String businessnum, String businesstype, String address, String province){
        this.uid = uid;
        this.name = name;

        this.businessnum = businessnum;
        this.businesstype = businesstype;
        this.address = address;
        this.province = province;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);

        result.put("bsinessnum", businessnum);
        result.put("businesstype",businesstype);
        result.put("address", address);
        result.put("province", province);
        return result;
    }
}
