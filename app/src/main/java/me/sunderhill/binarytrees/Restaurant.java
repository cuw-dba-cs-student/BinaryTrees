package me.sunderhill.binarytrees;

public class Restaurant {
    public String name;
    public String url;
    public String rating;
    public String reviewCount;
    public String address;
    public String phone;

    public void Restaurant(
            String name, String url, String rating, String reviewCount,
            String address, String phone )
    {
        this.name = name;
        this.url = url;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.address = address;
        this.phone = phone;
    }
}
