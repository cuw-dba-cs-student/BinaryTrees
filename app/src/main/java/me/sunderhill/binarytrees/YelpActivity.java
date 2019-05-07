package me.sunderhill.binarytrees;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class YelpActivity extends AppCompatActivity {

    private RestaurantAdapter restaurantAdapter;
    private ListView restaurantListView;
    private TextView airportTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yelp);
        String airport = DataCore.currAp;
        this.restaurantAdapter = new RestaurantAdapter(this, DataCore.yelpRestaurants);
        this.restaurantListView = this.findViewById(R.id.restaurantLV);
        this.airportTV = this.findViewById(R.id.airportTV);
        this.airportTV.setText(airport);
        this.restaurantListView.setAdapter(restaurantAdapter);
    }
}
