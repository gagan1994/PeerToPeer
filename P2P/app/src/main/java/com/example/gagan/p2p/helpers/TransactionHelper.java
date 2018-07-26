package com.example.gagan.p2p.helpers;

import android.os.Build;
import android.support.annotation.NonNull;

import com.example.gagan.p2p.users.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Gagan on 6/26/2018.
 */

public class TransactionHelper {

    private static TransactionHelper instance = new TransactionHelper();
    private final DatabaseReference transactionDb;
    private final List<TransactionModel> transactions = new ArrayList();
    private ValueEventListener valueEventListner =
            new CustomValueEventListner<List<TransactionModel>>(
                    new GenericTypeIndicator<List<TransactionModel>>()) {

                @Override
                public void updatedVal(List<TransactionModel> item) {
                    transactions.clear();
                    transactions.addAll(item);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        transactions.sort(new Comparator<TransactionModel>() {
                            @Override
                            public int compare(TransactionModel o1, TransactionModel o2) {
                                return Integer.compare(o1.getMoneyToBeSent(), o2.getMoneyToBeSent());
                            }
                        });
                    }
                    notifyDataSetchanged();
                }
            };


    public static TransactionHelper getInstance() {
        return instance;
    }

    private TransactionHelper() {
        transactionDb = ObjectCreationHelper.getInstance().getDbRef().child("transactions");
        transactionDb.addValueEventListener(valueEventListner);
    }

    public void sentMoneyTo(UserModel toUser, int money) {
        String transactionId = transactionDb.push().getKey();
        TransactionModel transactionModel = new TransactionModel(
                ObjectCreationHelper.getInstance().getCurrentUser()
                , toUser, money, transactionId);
        transactionDb.child(transactionId).setValue(transactionModel);
    }

    private void notifyDataSetchanged() {

    }


}
