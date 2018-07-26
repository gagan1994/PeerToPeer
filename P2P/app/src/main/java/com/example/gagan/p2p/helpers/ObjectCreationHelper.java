package com.example.gagan.p2p.helpers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.gagan.p2p.users.UserModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

/**
 * Created by Gagan on 6/25/2018.
 */

public class ObjectCreationHelper {
    private static final String USERS = "users";
    private static ObjectCreationHelper _instance;
    private UserModel user;


    public static void CreateObj(@NonNull DatabaseReference firebaseDatabase) {
        _instance = new ObjectCreationHelper(firebaseDatabase);
    }

    public static ObjectCreationHelper getInstance() {
        return _instance;
    }

    private final DatabaseReference firebaseDatabase;

    private ObjectCreationHelper(DatabaseReference firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
    }

    public UserModel createUserObj(String name) {
        Random r = new Random();
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setAmount(r.nextInt());
        return userModel;
    }

    public void getUseresRef(@NonNull UsersList usersList) {
        getUseresRefrence().addValueEventListener(usersList);
    }

    private DatabaseReference getUseresRefrence() {
        return firebaseDatabase.child(USERS);
    }

    public UserModel getCurrentUser() {
        return user;
    }

    public void addUser(final FirebaseUser account, final Context context) {
        getUseresRefrence().child(account.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null) {
                    user = new UserModel();
                    user.setName(account.getDisplayName());
                    user.setId(account.getUid());
                    user.setPicUri(account.getPhotoUrl().toString());
                    Task<Void> task = getUseresRefrence().child(account.getUid()).setValue(user);
                    task.addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(context, "Succesfully log in", Toast.LENGTH_SHORT).show();
                        }
                    });
                    task.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, "failed to log in", Toast.LENGTH_SHORT).show();

                        }
                    });
                } else {
                    user = dataSnapshot.getValue(UserModel.class);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public DatabaseReference getDbRef() {
        return firebaseDatabase;
    }
}
