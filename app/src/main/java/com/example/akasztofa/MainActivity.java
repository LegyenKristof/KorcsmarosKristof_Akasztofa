package com.example.akasztofa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
    private String[] szavak;
    private char[] abc;
    private String kitalalandoSzo, rejtettSzo;
    private List<Character> tippeltBetuk;
    private int betuIndex, hibakSzama;
    private char tippeltBetu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        ujJatek();

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

        btnTipp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipp();
            }
        });
    }

    private void kepValtas(){
        switch (hibakSzama){
            case 0:
                imageViewAkasztofa.setImageResource(R.drawable.akasztofa00);
                break;

            case 1:
                imageViewAkasztofa.setImageResource(R.drawable.akasztofa01);
                break;

            case 2:
                imageViewAkasztofa.setImageResource(R.drawable.akasztofa02);
                break;

            case 3:
                imageViewAkasztofa.setImageResource(R.drawable.akasztofa03);
                break;

            case 4:
                imageViewAkasztofa.setImageResource(R.drawable.akasztofa04);
                break;

            case 5:
                imageViewAkasztofa.setImageResource(R.drawable.akasztofa05);
                break;

            case 6:
                imageViewAkasztofa.setImageResource(R.drawable.akasztofa06);
                break;

            case 7:
                imageViewAkasztofa.setImageResource(R.drawable.akasztofa07);
                break;

            case 8:
                imageViewAkasztofa.setImageResource(R.drawable.akasztofa08);
                break;

            case 9:
                imageViewAkasztofa.setImageResource(R.drawable.akasztofa09);
                break;

            case 10:
                imageViewAkasztofa.setImageResource(R.drawable.akasztofa10);
                break;

            case 11:
                imageViewAkasztofa.setImageResource(R.drawable.akasztofa11);
                break;

            case 12:
                imageViewAkasztofa.setImageResource(R.drawable.akasztofa12);
                break;

            case 13:
                imageViewAkasztofa.setImageResource(R.drawable.akasztofa13);
                jatekVege(false);
                break;

            default:
                break;
        }
    }

    private void jatekVege(boolean gyozelem){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        if (gyozelem){
            builder.setTitle("Helyes megfejtés!");
        }
        else{
            builder.setTitle("Nem sikerült kitalálni!");
        }
        builder.setMessage("Szeretnél még egyet játszani?");
        builder.setNegativeButton("NEM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setPositiveButton("IGEN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ujJatek();
            }
        });
        builder.create().show();
    }

    private void tipp(){
        if (!tippeltBetuk.contains(tippeltBetu)){
            tippeltBetuk.add(tippeltBetu);
            textViewBetu.setTextColor(Color.parseColor("#000000"));
            boolean joTipp = false;
            StringBuilder stringBuilder = new StringBuilder(rejtettSzo);
            for (int i = 0; i < kitalalandoSzo.length(); i++){
                if (kitalalandoSzo.charAt(i) == tippeltBetu){
                    joTipp = true;
                    stringBuilder.setCharAt(i * 2, tippeltBetu);
                }
            }
            if (joTipp){
                rejtettSzo = stringBuilder.toString();
                textViewSzo.setText(rejtettSzo);
                if (!rejtettSzo.contains("_")){
                    jatekVege(true);
                }
            }
            else{
                hibakSzama++;
                kepValtas();
            }
        }
    }

    private void betuSzinezes(){
        if (tippeltBetuk.contains(tippeltBetu)){
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
        tippeltBetu = abc[betuIndex];
        betuSzinezes();
        textViewBetu.setText(tippeltBetu + "");
    }

    private void rejtettSzoGeneralas(){
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

    private void ujJatek(){
        kitalalandoSzo = szavak[(int) (Math.random() * szavak.length)];
        rejtettSzo = "";
        rejtettSzoGeneralas();
        tippeltBetuk = new ArrayList<>();
        betuIndex = 0;
        hibakSzama = 0;
        tippeltBetu = abc[0];
        betuSzinezes();
        kepValtas();
        textViewBetu.setText(tippeltBetu + "");
    }

    private void init(){
        btnMinusz = findViewById(R.id.btnMinusz);
        btnPlusz = findViewById(R.id.btnPlusz);
        btnTipp = findViewById(R.id.btnTipp);
        imageViewAkasztofa = findViewById(R.id.imageViewAkasztofa);
        textViewSzo = findViewById(R.id.textViewSzo);
        textViewBetu = findViewById(R.id.textViewBetu);
        szavak = new String[] {
                /*"AUTÓ", "HÁZ", "ANANÁSZ", "PROGRAMOZÁS", "TANÁR",
                "PÉNZ", "LÁTOGATÓ", "ŰRLÉNY", "FASZÁLLÍTÓ", "XD",*/
                "KRISTÓF", "KRISA", "DORCI", "DOMI", "SZOFI"
        };
        abc = "aábcdeéfghiíjklmnoóöőpqrstuúüűvwxyz".toUpperCase().toCharArray();
    }
}