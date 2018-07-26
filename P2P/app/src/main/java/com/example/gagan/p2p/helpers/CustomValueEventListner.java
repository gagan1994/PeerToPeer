package com.example.gagan.p2p.helpers;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Gagan on 6/26/2018.
 */

public abstract class CustomValueEventListner<T> implements ValueEventListener {
    private final GenericTypeIndicator<T> indicator;

    public abstract void updatedVal(T item);

    public CustomValueEventListner(GenericTypeIndicator<T> t) {
        this.indicator = t;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        updatedVal(dataSnapshot.getValue(indicator));
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
