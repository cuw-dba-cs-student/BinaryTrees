package me.sunderhill.binarytrees;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DbCore {
    public static FirebaseDatabase db = FirebaseDatabase.getInstance();
    public static DatabaseReference myDbRef = db.getReference("airports");
}
