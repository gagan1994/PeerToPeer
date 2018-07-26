package com.example.gagan.p2p.helpers;

import android.support.annotation.NonNull;

import com.example.gagan.p2p.users.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gagan on 6/26/2018.
 */

public abstract class TransactionList implements ValueEventListener {

    public abstract void transactionUpdated(List<TransactionModel> usersLists);

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        List<TransactionModel> transactionModels = new ArrayList<>();
        for (DataSnapshot item : dataSnapshot.getChildren()) {
            TransactionModel transactionList = item.getValue(TransactionModel.class);
            transactionModels.add(transactionList);
        }
        transactionUpdated(transactionModels);

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
