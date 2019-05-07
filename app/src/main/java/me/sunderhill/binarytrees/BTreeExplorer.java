package me.sunderhill.binarytrees;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BTreeExplorer extends AppCompatActivity {

    TextView nodeTV;
    Button leftChildBtn;
    Button rightChildBtn;
    Button viewYelpBtn;
    ProgressDialog pDialog;
    BTreeExplorer thisInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traverse_btree);

        this.nodeTV = (TextView) findViewById(R.id.bTreeNodeValET);
        this.nodeTV.setText(DataCore.currApTreeNode.getValue().name + " (" + DataCore.currApTreeNode.getValue().iata +")" );
        this.leftChildBtn = (Button) findViewById(R.id.leftChildBtn);
        this.rightChildBtn = (Button) findViewById(R.id.rightChildBtn);
        this.viewYelpBtn = (Button) findViewById(R.id.viewYelpBtn);

        if(DataCore.currApTreeNode.getLeft() != null) {leftChildBtn.setVisibility(View.VISIBLE);}
        if (DataCore.currApTreeNode.getRight() != null){rightChildBtn.setVisibility(View.VISIBLE);}

        this.pDialog = new ProgressDialog(this);
        pDialog.setMessage("Checking Yelp...Please wait");
        pDialog.show();
        thisInstance = this;
        DataCore.yelpRestaurants.clear();
        getYelp();
    }

    public void onLeftChildBtnPress(View v){
        DataCore.currApTreeNode = DataCore.currApTreeNode.getLeft();
        launchBTreeExplorer(v);
    }

    public void onRightChildBtnPress(View v) {
        DataCore.currApTreeNode = DataCore.currApTreeNode.getRight();
        launchBTreeExplorer(v);
    }

    public void launchBTreeExplorer(View v) {
        Intent i = new Intent(this, BTreeExplorer.class);
        startActivity(i);

    }

    public void onViewYelpBtnPress(View v) {
        Intent i = new Intent(this, YelpActivity.class);
        DataCore.currAp = "" + this.nodeTV.getText();
        this.startActivity(i);
    }
    public void getYelp() {
        Airport ap = DataCore.currApTreeNode.getValue();
        //String url = "https://api.yelp.com/v3/businesses/search?location=Cedar Grove US-WI&categories=restaurants";
        String url = "https://api.yelp.com/v3/businesses/search?location="+ap.city+" " + ap.region + "&categories=restaurants";
        System.out.println("*** Yelp Request URL  = " + url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest (
                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray businesses = response.getJSONArray("businesses");
                    for(int i = 0; i < businesses.length(); i++)
                    {

                        JSONObject rjson = businesses.getJSONObject(i);
                        JSONObject location = rjson.getJSONObject("location");
                        JSONArray dispAddr = location.getJSONArray("display_address");
                        String r[] = new String[6];
                        r[0] = rjson.getString("name");
                        r[1] = rjson.getString("url");
                        r[2] = rjson.getString("rating");
                        r[3] = rjson.getString("review_count");
                        r[4] = rjson.getString("display_phone");
                        r[5] = dispAddr.getString(0) + ", " + dispAddr.getString(1);

                        Restaurant rt = new Restaurant(r[0],r[1],r[2],r[3],r[5],r[4]);

                        DataCore.yelpRestaurants.add(rt);

                        System.out.println("*** Name: " + r[0]);
                        System.out.println("*** URL: " + r[1]);
                        System.out.println("*** Rating: " + r[2]);
                        System.out.println("*** Review Count: " + r[3]);
                        System.out.println("*** Phone " + r[4]);
                        System.out.println("*** Address " + r[5]);

                    }
                    thisInstance.viewYelpBtn.setVisibility(View.VISIBLE);
                }
                catch(Exception e)
                {
                    System.out.println("*** " + e.toString());
                }
                thisInstance.pDialog.hide();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("*** " + error.toString());
                thisInstance.pDialog.hide();
            }
        }) {
            @Override
            public Map getHeaders() throws AuthFailureError
            {
                HashMap headers = new HashMap();
                headers.put("Authorization","Bearer 1TPchpZB3kzAgrUXMjF9kJWdQng55XRQVtISLlXqTsC5lnNrwlv47UWGOgpRwwR9goe1-0MClzcUUA-M1m0uOnomTsfVcBNCgTZHawoAVghf3AgdMkMY0TUNTl7OXHYx");
                return headers;

            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }


}
