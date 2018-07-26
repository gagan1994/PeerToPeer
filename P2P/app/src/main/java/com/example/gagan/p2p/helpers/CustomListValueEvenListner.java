package com.example.gagan.p2p.helpers;

import android.support.annotation.NonNull;

import com.example.gagan.p2p.users.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gagan on 6/26/2018.
 */

public abstract class CustomListValueEvenListner<T> implements ValueEventListener {
    private final GenericTypeIndicator<T> indicator;

    abstract void updateList(List<T> items);

    CustomListValueEvenListner(GenericTypeIndicator<T> t) {
        this.indicator = t;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        List<T> items = new ArrayList<>();
        for (DataSnapshot item : dataSnapshot.getChildren()) {
            T itemValue = item.getValue(indicator);
            if (itemValue instanceof UserModel) {
                if (!((UserModel) items).getId().
                        equals(ObjectCreationHelper.getInstance().getCurrentUser().getId()))
                    items.add(itemValue);
            } else {
                items.add(itemValue);
            }
        }
        updateList(items);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
