package me.sunderhill.binarytrees;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    BTree<Integer> bint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadAirportBTree();
        DataCore.integerBTree = new BTree<Integer>();
        DataCore.integerBTree.add(5);
        DataCore.integerBTree.add(3);
        DataCore.integerBTree.add(4);
        DataCore.integerBTree.add(8);
        DataCore.integerBTree.add(6);
        DataCore.integerBTree.add(9);
        DataCore.currBTreeNode = DataCore.integerBTree.root;

    }

    public static void loadAirportBTree() {
        DbCore.myDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Airport ap;
                for ( DataSnapshot ds : dataSnapshot.getChildren()) {
                    ap = ds.getValue(Airport.class);
                    if (ap.iata != null) {
                        DataCore.apBTree.add(ap);
                    }
                }
                DataCore.currApTreeNode = DataCore.apBTree.root;
                Log.d("", "Snapshot received");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("","Failed to reave value.", databaseError.toException());
            }
        });
    }

    public void onExploreBTreeBtnPress(View v) {
        Intent i = new Intent(this, BTreeExplorer.class);
        startActivity(i);
    }
}
