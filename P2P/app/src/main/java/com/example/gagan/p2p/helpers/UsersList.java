package com.example.gagan.p2p.helpers;

import android.support.annotation.NonNull;

import com.example.gagan.p2p.users.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gagan on 6/25/2018.
 */

public abstract class UsersList implements ValueEventListener {

    public abstract void usersUpdated(List<UserModel> usersLists);

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        List<UserModel> usersLists = new ArrayList<>();
        for (DataSnapshot item : dataSnapshot.getChildren()) {
            UserModel user = item.getValue(UserModel.class);
            if (!user.getId().equals(ObjectCreationHelper.getInstance().getCurrentUser().getId()))
                usersLists.add(user);
        }
        usersUpdated(usersLists);

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
