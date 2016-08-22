package by.oleg.vasilevskiy.insurancerb;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Yulya on 24.10.2015.
 */
public class GreenCard extends AppCompatActivity {
    Spinner spinner;
    Spinner spinner2;
    Spinner spinner3;
    TextView text;
    TextView text1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greentarifs);
        createAdapter();
    }
    // Присваиваем спиннерам строки
    void createAdapter() {
        spinner = (Spinner) findViewById(R.id.spinner);
        final String[] tarifs = new String[]{
                "Тип ТС", "A  Легковые автомобили до 9 мест", "F1 Прицепы к легковым автомобилям", "C  Грузовые автомобили и тягачи, тракторы", "F2 Прицепы к грузовым автомобилям и тракторам", "B  Мотоциклы, мотоколяски и мотороллеры", "D  Мопеды", "E  Автобусы с числом мест больше 9", "C+F Грузовые автомобили и тягачи с прицепом", "G  Прочие ТС"
        };
        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tarifs);
        spinner.setAdapter(adapterType);

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        final String[] periods = new String[]{
                "Срок страхования", "15 дней", "1 месяц", "2 месяца", "3 месяца", "4 месяца", "5 месяцев", "6 месяцев", "7 месяцев", "8 месяцев", "9 месяцев", "10 месяцев", "11 месяцев", "1 год"
        };
        ArrayAdapter<String> adapterPeriod = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, periods);
        spinner2.setAdapter(adapterPeriod);

        spinner = (Spinner) findViewById(R.id.spinner3);
        final String[] territory = new String[]{
                "Территория действия", "Все страны системы ЗК", "Российская Федерация", "Украина и Республика Молдова"
        };
        ArrayAdapter<String> adapterTerritory = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, territory);
        spinner.setAdapter(adapterTerritory);
    }

    public void onClick2(View view) {
        valid();
    }

    // Получить тариф в евро из таблицы
    public String getTarif() {
        return Tables.getElement(String.valueOf(spinner.getSelectedItem()).substring(0, 3), String.valueOf(spinner2.getSelectedItem()), Tables.getTable(String.valueOf(spinner3.getSelectedItem())));
    }
    // Показать тариф и взнос в TextView
    public void showTarif() {
        if (!Course.getFee("145", getTarif()).equals("?")) {
            text1.setVisibility(View.INVISIBLE);
            if (spinner3.getSelectedItemPosition() == 1)
                text.setText("\nПодлежащий к уплате страховой взнос:\n  " + getTarif() + " EURO\n  " + Course.getFee(Course.USD, getTarif()) + " USD\n  " + Course.getFee(Course.PLN, getTarif()) + " PLN ");
            if (spinner3.getSelectedItemPosition() == 2)
                text.setText("\nПодлежащий к уплате страховой взнос:\n  " + getTarif() + " EURO\n  " + Course.getFee(Course.USD, getTarif()) + " USD\n  " + Course.getFee(Course.RUB, getTarif()) + " RUB ");
            if (spinner3.getSelectedItemPosition() == 3)
                text.setText("\nПодлежащий к уплате страховой взнос:\n  " + getTarif() + " EURO\n  " + Course.getFee(Course.USD, getTarif()) + " USD\n  " + Course.getFee(Course.PLN, getTarif()) + " PLN ");
        } else {
            text.setText("\nК оплате:\n  " + getTarif() + " EURO \n");
            text1.setVisibility(View.VISIBLE);
            text1.setText("Для того, чтобы узнать сумму в USD, RUB, PLN, подключите устройство к интернету!");
        }
    }

    // Проверка на правильный выбор спиннеров

    public void valid() {
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);

        text = (TextView) findViewById(R.id.textView2);
        text1 = (TextView) findViewById(R.id.textView9);
        text.setTextColor(Color.RED);
        text1.setTextColor(Color.RED);
        if (spinner.getSelectedItemPosition() == 0) text.setText("Выберите тип ТС");
        else if (spinner2.getSelectedItemPosition() == 0) text.setText("Выберите срок страхования");
        else if (spinner3.getSelectedItemPosition() == 0)
            text.setText("Выберите территорию действия");
        else {
            text.setTextColor(Color.BLACK);
            showTarif();
        }
    }
}
