package com.example.ngetichconverter.Network;

import static com.example.ngetichconverter.MainConstants.x_rapidapi_host;
import static com.example.ngetichconverter.MainConstants.x_rapidapi_key;

import com.example.ngetichconverter.MainConstants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API_Client {
    private static Retrofit retrofit = null;
    public static API_Interface getClient(){
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request newRequest=chain.request().newBuilder()
                                    .addHeader("x-rapidapi-key", x_rapidapi_key)
                                    .addHeader("x-rapidapi-host",x_rapidapi_host)
                                    .build();
                            return chain.proceed(newRequest);
                        }
                    }).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(MainConstants.baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(API_Interface.class);
    }

}
