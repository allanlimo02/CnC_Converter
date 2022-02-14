package com.example.ngetichconverter.Network;

import com.example.ngetichconverter.Model.CurrencyResponseRetrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API_Interface {
    @GET("/v1/convertcurrency")
    Call<CurrencyResponseRetrofit> getCurrecy(
            @Query("have")String have,
            @Query("want") String want,
            @Query("amount")String amount
    );


}
