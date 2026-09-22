package com.example.myapplication; // Оставь здесь свою верхнюю строчку, если имя пакета другое

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Находим кнопку на первом белом экране
        Button myButton = findViewById(R.id.myButton);

        // 2. Вешаем на неё событие клика (Event Listener)
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 3. Явный Intent для перехода на экран деталей лофта
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });
    }
}
