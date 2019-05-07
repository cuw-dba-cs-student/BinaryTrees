package me.sunderhill.binarytrees;

import java.io.Serializable;

public class Airport implements Serializable, Comparable<Airport> {
    public String country;
    public String region;
    public String iata;
    public String name;
    public String city;
    public boolean legalCode;

    public Airport(String city, String country, String iata, String name, String region, Boolean legalCode) {
        this.country = country;
        this.region = region;
        this.iata = iata;
        this.name = name;
        this.city = city;
        this.legalCode = true;
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

    private String removeQuotes(String s) {
        String retVal = "";
        for(int i = 0; i <s.length(); i++) {
            if(s.charAt(i) != '"') { retVal += s.charAt(i); }
        }
        return retVal;
    }

    public void sanitize() {
        this.name = this.removeQuotes(this.name);
        this.country = this.removeQuotes(this.country);
        this.iata = this.removeQuotes(this.iata );
        this.city = this.removeQuotes(this.city);
        this.region = this.removeQuotes(this.region);
    }

    public boolean isLegalCode()
    {
        return this.iata.length() == 3;
    }

}

