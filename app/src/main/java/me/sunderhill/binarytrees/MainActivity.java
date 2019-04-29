package me.sunderhill.binarytrees;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadAirportBTree();

    }

    public static void loadAirportBTree() {
        DbCore.myDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("", "Snapshot received");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("","Failed to reave value.", databaseError.toException());
            }
        });
    }
}
