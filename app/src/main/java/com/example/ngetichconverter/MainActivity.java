package com.example.ngetichconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ngetichconverter.Model.CurrencyResponseRetrofit;
import com.example.ngetichconverter.Network.API_Client;
import com.example.ngetichconverter.Network.API_Interface;
import com.example.ngetichconverter.SharedPreferences.PreferenceItems;
import com.example.ngetichconverter.SharedPreferences.SharedPreferenceAdapter;
import com.example.ngetichconverter.SharedPreferences.SharedPreferenceConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.from) Spinner fromCode;
    @BindView(R.id.to) Spinner toCode;
    @BindView(R.id.value) EditText mVlue;
    @BindView(R.id.submit) Button mSubmit;
    @BindView(R.id.manualpopup) RelativeLayout manualpop;
    @BindView(R.id.finalamount) TextView finalAmount;
    @BindView(R.id.finalcurrency) TextView finalCurrency;
    @BindView(R.id.closebutton) Button closebtn;
    @BindView(R.id.res) TextView res;
    @BindView(R.id.progress_circular) ProgressBar progress_circular;
    @BindView(R.id.recycler) RecyclerView recycler;

    ResponseAdapter responseAdapter;
    public ArrayList<PreferenceItems> recentSearch;
    SharedPreferenceAdapter sharedPreferenceAdapter;
    SharedPreferenceConfig sharedPreferenceConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mSubmit.setOnClickListener(this);
        showItems();
        ArrayAdapter<CharSequence> arrayAdapter=ArrayAdapter.createFromResource(this,R.array.country_codes, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromCode.setAdapter(arrayAdapter);
        toCode.setAdapter(arrayAdapter);
        closebtn.setOnClickListener(this);
        sharedPreferenceAdapter=new SharedPreferenceAdapter();
        recycler.setAdapter(sharedPreferenceAdapter);
        if(recentSearch==null){
            recentSearch=new ArrayList<>();
        }
        recentSearch=SharedPreferenceConfig.readeList(getApplicationContext());

    }

    @Override
    public void onClick(View view) {
        if (view==mSubmit){
            String fromCodeInput=fromCode.getSelectedItem().toString();
            String toCodeInput=toCode.getSelectedItem().toString();
            String mValueInput=mVlue.getText().toString();
            if (fromCodeInput.equals("")){
                Toast.makeText(this, "From Country cannot be blank", Toast.LENGTH_SHORT).show();
            }else
                if(toCodeInput.equals("")){
                    Toast.makeText(this, "To country cannot be blank", Toast.LENGTH_SHORT).show();
                }else
                    if (fromCodeInput.equals(toCodeInput)){
                        Toast.makeText(this, "From and To cannot be same", Toast.LENGTH_SHORT).show();
                    }else
                        if (mValueInput.equals("")){
                            Toast.makeText(this, "Value cannot be empty", Toast.LENGTH_SHORT).show();
                        }else {
                            mSubmit.setVisibility(View.GONE);
                            finalAmount.setVisibility(View.GONE);
                            toCode.setVisibility(View.GONE);
                            fromCode.setVisibility(View.GONE);

                            SharedPreferenceConfig.writeToPreference(getApplicationContext(),recentSearch);
                            calculate(fromCodeInput,toCodeInput,mValueInput);
                        }
        }
        if(view==closebtn){
            mSubmit.setVisibility(View.VISIBLE);
            finalAmount.setVisibility(View.VISIBLE);
            toCode.setVisibility(View.VISIBLE);
            fromCode.setVisibility(View.VISIBLE);
            manualpop.setVisibility(View.GONE);
            mSubmit.setVisibility(View.VISIBLE);
        }
    }
    private void calculate(String a,String b,String c){
        API_Interface api_interface= API_Client.getClient();
        Call<CurrencyResponseRetrofit> call=api_interface.getCurrecy(a,b,c);
        progress_circular.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<CurrencyResponseRetrofit>() {
            @Override
            public void onResponse(Call<CurrencyResponseRetrofit> call, Response<CurrencyResponseRetrofit> response) {

                if (response.isSuccessful()){
                    progress_circular.setVisibility(View.GONE);
                    Intent intent=new Intent(MainActivity.this,Results.class);
                    intent.putExtra("newCurrency",response.body().getNewCurrency());
                    intent.putExtra("newAmount",response.body().getNewAmount().toString());
                    intent.putExtra("oldCurrency",response.body().getOldCurrency());
                    intent.putExtra("oldAmount",response.body().getOldAmount().toString());
                    startActivity(intent);
                }
                else {
                    progress_circular.setVisibility(View.GONE);
                    manualpop.setVisibility(View.VISIBLE);
                    mSubmit.setVisibility(View.GONE);
                    finalAmount.setText("Something went wrong");
//                    Log.d(response.code(),"Response Code");
                }
            }

            @Override
            public void onFailure(Call<CurrencyResponseRetrofit> call, Throwable t) {
                manualpop.setVisibility(View.VISIBLE);
                finalAmount.setText("Didn't go through.Try again Later");
            }
        });

    }
    private void saveToSharedPreference(){
        SharedPreferences sharedPreferences=getSharedPreferences("fromCodeInput",MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(recentSearch);
        editor.putString("previous search",json);
        editor.apply();
    }
    private void updateList(){
        SharedPreferences sharedPreferences=getSharedPreferences("",MODE_PRIVATE);
        Gson gson=new Gson();
        String json =sharedPreferences.getString("previous search",null);
        Type type = new TypeToken<ArrayList<PreferenceItems>>() {}.getType();
        recentSearch=gson.fromJson(json,type);

        if (recentSearch==null){
            recentSearch=new ArrayList<>();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    void showItems(){
        mSubmit.setVisibility(View.VISIBLE);
        finalAmount.setVisibility(View.VISIBLE);
        toCode.setVisibility(View.VISIBLE);
        fromCode.setVisibility(View.VISIBLE);
        manualpop.setVisibility(View.GONE);
        mSubmit.setVisibility(View.VISIBLE);
    }
}