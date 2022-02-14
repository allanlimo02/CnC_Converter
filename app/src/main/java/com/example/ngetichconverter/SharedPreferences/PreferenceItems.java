package com.example.ngetichconverter.SharedPreferences;

public class PreferenceItems {
    private String country1;
    private String country2;
    private String amount;

    public PreferenceItems(String country1, String country2, String amount) {
        this.country1 = country1;
        this.country2 = country2;
        this.amount = amount;
    }

    public String getCountry1() {
        return country1;
    }

    public String getCountry2() {
        return country2;
    }

    public String getAmount() {
        return amount;
    }

    public void setCountry1(String country1) {
        this.country1 = country1;
    }

    public void setCountry2(String country2) {
        this.country2 = country2;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
