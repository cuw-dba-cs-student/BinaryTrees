package me.sunderhill.binarytrees;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BTreeExplorer extends AppCompatActivity {

    TextView nodeTV;
    Button leftChildBtn;
    Button rightChildBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traverse_btree);
        this.nodeTV = (TextView) findViewById(R.id.bTreeNodeValET);
        this.nodeTV.setText(DataCore.currBTreeNode.getValue().toString());
        this.leftChildBtn = (Button) findViewById(R.id.leftChildBtn);
        this.rightChildBtn = (Button) findViewById(R.id.rightChildBtn);
        if(DataCore.currBTreeNode.getLeft() != null)
        {
            leftChildBtn.setVisibility(View.VISIBLE);
        }
        if (DataCore.currBTreeNode.getRight() != null)
        {
            rightChildBtn.setVisibility(View.VISIBLE);
        }
    }

    public void onLeftChildBtnPress(View v){
        DataCore.currBTreeNode = DataCore.currBTreeNode.getLeft();
        launchBTreeExplorer(v);
    }

    public void onRightChildBtnPress(View v) {
        DataCore.currBTreeNode = DataCore.currBTreeNode.getRight();
        launchBTreeExplorer(v);
    }

    public void launchBTreeExplorer(View v) {
        Intent i = new Intent(this, BTreeExplorer.class);
        startActivity(i);
    }
}
