package by.oleg.vasilevskiy.insurancerb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ReferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);
    }
    // Открытие активити для Действия участников ДТП
    public void activeReference1(View view) {
        Intent intent = new Intent(ReferenceActivity.this, TextReferenceActivity.class);
        startActivity(intent);
        TextReferenceActivity.option = "1";
    }
    // Доплата по факту ремонта
    public void activeReference2(View view) {
        Intent intent = new Intent(ReferenceActivity.this, TextReferenceActivity.class);
        startActivity(intent);
        TextReferenceActivity.option = "2";
    }
    //  Комплексное страхование
    public void activeReference3(View view) {
        Intent intent = new Intent(ReferenceActivity.this, TextReferenceActivity.class);
        startActivity(intent);
        TextReferenceActivity.option = "3";
    }
    // Извещение до 400 евро
    public void activeReference4(View view) {
        Intent intent = new Intent(ReferenceActivity.this, TextReferenceActivity.class);
        startActivity(intent);
        TextReferenceActivity.option = "4";
    }
}
