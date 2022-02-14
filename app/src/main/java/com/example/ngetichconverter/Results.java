package com.example.ngetichconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Results extends AppCompatActivity {
    @BindView(R.id.oldCurrency) TextView oldCurrency1;
    @BindView(R.id.oldAmount) TextView oldAmount1;
    @BindView(R.id.newAmount) TextView newAmount1;
    @BindView(R.id.newCurrency) TextView newCurrency1;
    @BindView(R.id.btn) Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results2);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        String oldCurrency=intent.getStringExtra("oldCurrency");;
        String newCurrency=intent.getStringExtra("newCurrency");;
        String oldAmount=intent.getStringExtra("oldAmount");;
        String newAmount=intent.getStringExtra("newAmount");

        oldAmount1.setText(oldAmount);
        oldCurrency1.setText(oldCurrency);
        newAmount1.setText(newAmount);
        newCurrency1.setText(newCurrency);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Results.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}