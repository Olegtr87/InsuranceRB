package by.oleg.vasilevskiy.insurancerb;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CourseActivity extends AppCompatActivity {
    TextView text;
    Date date;
    final static String dateStrFormat="dd/MM/yyyy";
    // Действия при создании активити
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        showCourse();
    }
    // Показать в TextView курсы валют
    public void showCourse() {
        text = (TextView) findViewById(R.id.textView3);
        date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateStrFormat);
        if (!Course.getCourse(Course.EUR).equals(Course.error)) {
            text.setTextColor(Color.BLACK);
            text.setText("Курсы валют на " + dateFormat.format(date) + " в бел.руб.:\n\n Евро - " + Course.getCourse(Course.EUR) + "\n Доллар США - " + Course.getCourse(Course.USD) +
                    "\n 100 росс. руб. - " + Course.getCourse(Course.RUB) + "\n 10 пол. зл. - " + Course.getCourse(Course.PLN) + "\n");
        } else {
            text.setTextColor(Color.RED);
            text.setText("Подключите устройство к интернету!\n");
        }
    }
    // Действие при нажатии кнопки Обновить в текущем активити
    public void onClick4(View view) {
        showCourse();
    }
}
