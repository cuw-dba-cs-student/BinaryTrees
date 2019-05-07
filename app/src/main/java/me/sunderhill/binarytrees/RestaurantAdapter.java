package me.sunderhill.binarytrees;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.LinkedList;

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {
    public RestaurantAdapter(Context context, LinkedList<Restaurant> restaurants) {
        super(context, 0, restaurants);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Restaurant restaurant = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.yelplistrow, parent, false);

        }

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvReviewCnt = (TextView) convertView.findViewById(R.id.tvReviewCnt);
        TextView tvPhone = (TextView) convertView.findViewById(R.id.tvPhone);
        TextView tvRating = (TextView) convertView.findViewById(R.id.tvRating);
        TextView tvAddress = (TextView) convertView.findViewById(R.id.tvAddress);
        TextView tvUrl = (TextView) convertView.findViewById(R.id.tvUrl);

        tvName.setText(restaurant.name);
        tvReviewCnt.setText("Reviews: " + restaurant.reviewCount);
        tvPhone.setText(restaurant.phone);
        tvRating.setText("Rating: " + restaurant.rating);
        tvAddress.setText(restaurant.address);
        tvUrl.setText(restaurant.url);

        return convertView;
    }
}
