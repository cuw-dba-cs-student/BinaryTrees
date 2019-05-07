package me.sunderhill.binarytrees;

import android.app.ProgressDialog;
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
    static ProgressDialog pDialog;
    MainActivity parentClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataCore.apBTree = new BTree<Airport>();
        pDialog = new ProgressDialog(this);
        parentClass = this;
        pDialog.setMessage("Retrieving Airports from Firebase...Please wait");
        pDialog.show();
        loadAirportBTree();
    }

    public static void loadAirportBTree() {
        DbCore.myDbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Airport ap;
                for ( DataSnapshot ds : dataSnapshot.getChildren()) {
                    boolean addToTree = true;
                    ap = ds.getValue(Airport.class);
                    if (ap.iata.startsWith("\"")) {
                        ap.sanitize();
                        if(ap.isLegalCode()) {
                            DbCore.myDbRef.child(ds.getKey()).setValue(ap);
                        }
                        else {
                            DbCore.myDbRef.child(ds.getKey()).removeValue();
                            addToTree = false;
                        }
                    }
                    if(addToTree) { DataCore.apBTree.add(ap); }
                }
                DataCore.currApTreeNode = DataCore.apBTree.root;
                pDialog.hide();
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
