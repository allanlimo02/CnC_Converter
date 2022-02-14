
package com.example.ngetichconverter.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CurrencyResponseRetrofit {

    @SerializedName("new_amount")
    @Expose
    private Double newAmount;
    @SerializedName("new_currency")
    @Expose
    private String newCurrency;
    @SerializedName("old_currency")
    @Expose
    private String oldCurrency;
    @SerializedName("old_amount")
    @Expose
    private Double oldAmount;


    public CurrencyResponseRetrofit() {
    }

    public CurrencyResponseRetrofit(Double newAmount, String newCurrency, String oldCurrency, Double oldAmount) {
        super();
        this.newAmount = newAmount;
        this.newCurrency = newCurrency;
        this.oldCurrency = oldCurrency;
        this.oldAmount = oldAmount;
    }

    public Double getNewAmount() {
        return newAmount;
    }

    public void setNewAmount(Double newAmount) {
        this.newAmount = newAmount;
    }

    public String getNewCurrency() {
        return newCurrency;
    }

    public void setNewCurrency(String newCurrency) {
        this.newCurrency = newCurrency;
    }

    public String getOldCurrency() {
        return oldCurrency;
    }

    public void setOldCurrency(String oldCurrency) {
        this.oldCurrency = oldCurrency;
    }

    public Double getOldAmount() {
        return oldAmount;
    }

    public void setOldAmount(Double oldAmount) {
        this.oldAmount = oldAmount;
    }

}
