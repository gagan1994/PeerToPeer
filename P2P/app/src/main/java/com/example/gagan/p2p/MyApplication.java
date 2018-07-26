package com.example.gagan.p2p;

import android.app.Application;

import com.example.gagan.p2p.helpers.ObjectCreationHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Gagan on 6/25/2018.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseReference database=FirebaseDatabase.getInstance().getReference().child("p2p");
        ObjectCreationHelper.CreateObj(database);
    }
}
