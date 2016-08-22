package by.oleg.vasilevskiy.insurancerb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class InsuranceActivity extends AppCompatActivity {
    Spinner spinner;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance);
        insuranseAct();
    }
    // Действия при выборе кнопки в спиннере
    public void insuranseAct() {
        spinner = (Spinner) findViewById(R.id.spinner4);
        text = (TextView) findViewById(R.id.textView4);
        final String[] insuranceCompany = new String[]{
                "Страховая компания", "Промтрансинвест", "Белгосстрах", "Белкоопстрах", "Белнефтестрах", "Белэксимгарант", "ТАСК"
        };
        ArrayAdapter<String> adapterComp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, insuranceCompany);
        spinner.setAdapter(adapterComp);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                text.setMovementMethod(LinkMovementMethod.getInstance());
                switch (position) {
                    case 0:
                        text.setText("");
                        break;
                    case 1:
                        text.setText("");
                        text.setText(R.string.promtransinvest);
                        break;
                    case 2:
                        text.setText("");
                        text.setText(R.string.belgosstrah);
                        break;
                    case 3:
                        text.setText("");
                        text.setText(R.string.belkoopstrah);
                        break;
                    case 4:
                        text.setText("");
                        text.setText(R.string.belneftestrah);
                        break;
                    case 5:
                        text.setText("");
                        text.setText(R.string.beleximgarant);
                        break;
                    case 6:
                        text.setText("");
                        text.setText(R.string.task);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
