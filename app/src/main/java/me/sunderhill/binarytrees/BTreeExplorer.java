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

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BTreeExplorer extends AppCompatActivity {

    TextView nodeTV;
    Button leftChildBtn;
    Button rightChildBtn;
    ProgressDialog pDialog;
    BTreeExplorer thisInstance;
    private LinkedList<Restaurant> yelpRestaurants = new LinkedList<Restaurant>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traverse_btree);

        this.nodeTV = (TextView) findViewById(R.id.bTreeNodeValET);
        this.nodeTV.setText(DataCore.currApTreeNode.getValue().iata);
        this.leftChildBtn = (Button) findViewById(R.id.leftChildBtn);
        this.rightChildBtn = (Button) findViewById(R.id.rightChildBtn);

        if(DataCore.currApTreeNode.getLeft() != null) {leftChildBtn.setVisibility(View.VISIBLE);}
        if (DataCore.currApTreeNode.getRight() != null){rightChildBtn.setVisibility(View.VISIBLE);}

        this.pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...PLease wait");
        pDialog.show();

        thisInstance = this;
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

    public void getYelp() {
        Airport ap = DataCore.currApTreeNode.getValue();
        String url = "https://api.yelp.com/v3/businesses/search?location=Cedar Grove US-WI&categories=restaurants";
        //String url = "https://api.yelp.com/v3/businesses/search?location="+ap.city+" " + ap.region + "&categories=restaurants";
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
                        String name = rjson.getString("name");
                        String url = rjson.getString("url");
                        String rating = rjson.getString("rating");
                        String reviewCount = rjson.getString("review_count");
                        JSONObject da = rjson.getJSONObject("location");
                        JSONArray das = da.getJSONArray("display_address");
                        String phone = rjson.getString("display_phone");
                        System.out.println("*** Name: " + name);
                        System.out.println("*** URL: " + url);
                        System.out.println("*** Rating: " + rating);
                        System.out.println("*** Review Count: " + reviewCount);
                        System.out.println("*** Location: " + rjson.getJSONObject("location"));
                        System.out.println("*** DA1: " + das.getString(0));
                        System.out.println("*** DA2: " + das.getString(1));

                    }
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
                // TODO: Handle error
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
