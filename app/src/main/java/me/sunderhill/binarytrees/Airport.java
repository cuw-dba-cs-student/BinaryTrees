package me.sunderhill.binarytrees;

import java.io.Serializable;

public class Airport implements Serializable, Comparable<Airport> {
    public String country;
    public String region;
    public String iata;
    public String name;
    public String city;

    public Airport(String city, String country, String iata, String name, String region) {
        this.country = country;
        this.region = region;
        this.iata = iata;
        this.name = name;
        this.city = city;
    }

    public Airport() {};

    public String toString() {
        return this.city + " | " + this.region + " | " + this.country + " (" + this.iata + ")";
    }

    public boolean filterApplies(String filterString) {
        filterString = filterString.toLowerCase();
        return
                this.iata.toLowerCase().contains(filterString)    ||
                        this.name.toLowerCase().contains(filterString)    ||
                        this.country.toLowerCase().contains(filterString) ||
                        this.city.toLowerCase().contains(filterString);
    }

    @Override public int compareTo(Airport a) {
        return this.iata.compareTo(a.iata);
    }
}

