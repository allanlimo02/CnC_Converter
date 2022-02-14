package com.example.ngetichconverter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ngetichconverter.Model.CurrencyResponseRetrofit;

import java.util.List;

public class ResponseAdapter  extends RecyclerView.Adapter<ResponseAdapter.ResponseVh> {

    private List<CurrencyResponseRetrofit> currencyResponseRetrofits;
    Context context;

//    public ResponseAdapter() {
//    }
    public void setData(List<CurrencyResponseRetrofit> currencyResponseRetrofitList) {
        this.currencyResponseRetrofits=currencyResponseRetrofitList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ResponseVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new ResponseAdapter.ResponseVh(LayoutInflater.from(context).inflate(R.layout.response_display,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ResponseVh holder, int position) {
        CurrencyResponseRetrofit cres=currencyResponseRetrofits.get(position);

        String newAmount=cres.getNewCurrency();
        String newSign=cres.getNewCurrency();

    }

    @Override
    public int getItemCount() {
        return currencyResponseRetrofits.size();
    }

    public class ResponseVh extends RecyclerView.ViewHolder {
        TextView receivedAMT,receivedCode;
        public ResponseVh(@NonNull View itemView) {
            super(itemView);
            receivedAMT=itemView.findViewById(R.id.recevedAmount);
            receivedCode=itemView.findViewById(R.id.recevedtitle);
        }
    }
}
