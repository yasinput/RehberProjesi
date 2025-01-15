package com.yasingundogdu.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class tarih extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarih);  // XML dosyasını bağlıyoruz

        // Butonları tanımlıyoruz
        Button buttonCami = findViewById(R.id.buttonCami);
        Button buttonMuze = findViewById(R.id.buttonMuze);
        Button buttonGorselEkle = findViewById(R.id.buttonGorselEkle);


        // Cami butonuna tıklama
        buttonCami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tarih.this, cami.class); // CamiActivity'ye yönlendirme
                startActivity(intent);
            }
        });

        // Müze butonuna tıklama
        buttonMuze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tarih.this, muze.class); // MuzeActivity'ye yönlendirme
                startActivity(intent);
            }
        });

        // Görsel Ekle butonuna tıklama
        buttonGorselEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tarih.this, gorselekle.class); // GorselEkleActivity'ye yönlendirme
                startActivity(intent);
            }
        });


    }
}
