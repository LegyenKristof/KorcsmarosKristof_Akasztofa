package com.example.akasztofa;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button btnMinusz, btnPlusz, btnTipp;
    private TextView textViewBetu, textViewSzo;
    private ImageView imageViewAkasztofa;
    private String[] szavak = new String[] {
            "AUTÓ", "HÁZ", "ANANÁSZ", "PROGRAMOZÁS", "TANÁR",
            "PÉNZ", "LÁTOGATÓ", "ŰRLÉNY", "FASZÁLLÍTÓ", "XD"
    };
    private String kitalalandoSzo, rejtettSzo;
    private char[] abc;
    private List<Character> tippeltBetuk;
    private int betuIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btnPlusz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                betuValtas(true);
            }
        });

        btnMinusz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                betuValtas(false);
            }
        });
    }

    private void betuSzinezes(){
        if (tippeltBetuk.contains(abc[betuIndex])){
            textViewBetu.setTextColor(Color.parseColor("#000000"));
        }
        else{
            textViewBetu.setTextColor(Color.parseColor("#CC0000"));
        }
    }

    private void betuValtas(boolean elore){
        if (elore){
            if (betuIndex == abc.length - 1){
                betuIndex = 0;
            }
            else{
                betuIndex++;
            }
        }
        else{
            if (betuIndex == 0){
                betuIndex = abc.length - 1;
            }
            else{
                betuIndex--;
            }
        }
        betuSzinezes();
        textViewBetu.setText(abc[betuIndex] + "");
    }

    private void kirajzol(){
        for (int i = 0; i < kitalalandoSzo.length(); i++){
            if (i < kitalalandoSzo.length() - 1){
                rejtettSzo += "_ ";
            }
            else{
                rejtettSzo += "_";
            }
        }
        textViewSzo.setText(rejtettSzo);
    }

    private void init(){
        btnMinusz = findViewById(R.id.btnMinusz);
        btnPlusz = findViewById(R.id.btnPlusz);
        btnTipp = findViewById(R.id.btnTipp);
        imageViewAkasztofa = findViewById(R.id.imageViewAkasztofa);
        textViewSzo = findViewById(R.id.textViewSzo);
        textViewBetu = findViewById(R.id.textViewBetu);
        kitalalandoSzo = szavak[(int) (Math.random() * szavak.length)];
        rejtettSzo = "";
        kirajzol();
        abc = "aábcdeéfghiíjklmnoóöőpqrstuúüűvwxyz".toUpperCase().toCharArray();
        tippeltBetuk = new ArrayList<>();
        betuIndex = 0;
    }
}