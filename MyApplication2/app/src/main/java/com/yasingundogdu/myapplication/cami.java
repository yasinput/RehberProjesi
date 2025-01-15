package com.yasingundogdu.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class cami extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cami);  // Layout dosyanızı belirleyin
    }

    // c1 butonuna tıklama
    public void c1(View view) {
        Intent intent = new Intent(cami.this, ca1.class);
        startActivity(intent);
    }

    // c2 butonuna tıklama
    public void c2(View view) {
        Intent intent = new Intent(cami.this, ca2.class);
        startActivity(intent);
    }

    // c3 butonuna tıklama
    public void c3(View view) {
        Intent intent = new Intent(cami.this, ca3.class);
        startActivity(intent);
    }
}