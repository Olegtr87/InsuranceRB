package by.oleg.vasilevskiy.insurancerb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // Открытие активити О программе
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, MainAbout.class);
        startActivity(intent);
    }
    // открытие активити Расчет Зеленая карта
    public void onClick1(View view) {
        Intent intent = new Intent(MainActivity.this, GreenCard.class);
        startActivity(intent);
    }
    // Открытие активити Курс НБ РБ
    public void onClick2(View view) {
        Intent intent = new Intent(MainActivity.this, CourseActivity.class);
        startActivity(intent);
    }
    // Открытие активити Страховые компании
    public void activeInsuranseActivity(View view) {
        Intent intent = new Intent(MainActivity.this, InsuranceActivity.class);
        startActivity(intent);
    }
    // Расчет ОСГО
    public void activeOsgoActivity(View view) {
        Intent intent = new Intent(MainActivity.this, OsgoActivity.class);
        startActivity(intent);
    }
    // Открытие активити Справка
    public void activeReferenceActivity(View view) {
        Intent intent = new Intent(MainActivity.this, ReferenceActivity.class);
        startActivity(intent);
    }
}

