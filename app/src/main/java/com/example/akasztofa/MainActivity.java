package com.example.akasztofa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnMinusz, btnPlusz, btnTipp;
    private TextView textViewBetu, textViewSzo;
    private ImageView imageViewAkasztofa;
    private String[] szavak = new String[] {
            "AUTÓ"/*, "HÁZ", "ANANÁSZ", "PROGRAMOZÁS", "TANÁR",
            "PÉNZ", "LÁTOGATÓ", "ŰRLÉNY", "FASZÁLLÍTÓ", "XD"*/
    };
    private String kitalalandoSzo, rejtettSzo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
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
    }
}