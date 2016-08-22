package by.oleg.vasilevskiy.insurancerb;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OsgoActivity extends AppCompatActivity {
    Spinner spinnerPeriod;
    Spinner spinnerType;
    Spinner spinnerK1;
    Spinner spinnerK2;
    Spinner spinnerK3;
    Spinner spinnerDtp;
    Spinner spinnerLgoty;
    Spinner spinnerEngineCapacityAvto;
    Spinner spinnerElektroOrHybrid;
    Spinner spinnerTypeLightTrailer;
    Spinner spinnerLoadCargo;
    Spinner spinnerTraktorCapacity;
    Spinner spinnerLoadTrailer;
    Spinner spinnerMotoCapacity;
    Spinner spinnerAutoBus;
    String osgoOrKomplex;
    TextView textView;
    TextView textView1;
    TextView textView2;
    String period;
    String type;
    String k1;
    String k2;
    String k3;
    String dtp;
    String lgoty;
    String typeAvto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osgo);
    }
    // Действие при выборе вида страхования
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioButton:
                if (checked)
                    allHide();
                osgoOrKomplex = "osgo";
                createSpinner1Osgo();
                break;
            case R.id.radioButton2:
                if (checked)
                    allHide();
                osgoOrKomplex = "komplex";
                createSpinnerPeriodKomplex();
                break;
            case R.id.radioButton3:
                if (checked)
                    allHide();
                createSpinner1Pogran();

                break;
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void createSpinnerPeriodKomplex() {
        spinnerPeriod = (Spinner) findViewById(R.id.spinner5);
        spinnerPeriod.setVisibility(View.VISIBLE);

        final String[] periods = new String[]{
                "Срок страхования", "6 месяцев", "7 месяцев", "8 месяцев", "9 месяцев", "10 месяцев", "11 месяцев", "1 год"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, periods);
        spinnerPeriod.setAdapter(adapter);

        spinnerPeriod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        hideS6S12();
                        break;
                    default:
                        hideS6S12();
                        period = String.valueOf(spinnerPeriod.getSelectedItem());
                        createSpinner2Osgo();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void createSpinner1Pogran() {
        spinnerPeriod = (Spinner) findViewById(R.id.spinner5);
        spinnerType = (Spinner) findViewById(R.id.spinner6);
        textView = (TextView) findViewById(R.id.textView6);
        textView2 = (TextView) findViewById(R.id.textView11);
        spinnerPeriod.setVisibility(View.VISIBLE);

        final String[] periods = new String[]{
                "Срок страхования", "15 дней", "1 месяц", "2 месяца", "3 месяца", "4 месяца", "5 месяцев", "6 месяцев", "7 месяцев", "8 месяцев", "9 месяцев", "10 месяцев", "11 месяцев", "1 год"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, periods);
        spinnerPeriod.setAdapter(adapter);

        spinnerPeriod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        spinnerType.setVisibility(View.INVISIBLE);
                        textView.setVisibility(View.INVISIBLE);
                        textView2.setVisibility(View.INVISIBLE);
                        break;
                    default:
                        textView.setVisibility(View.INVISIBLE);
                        period = String.valueOf(spinnerPeriod.getSelectedItem());

                        spinnerType.setVisibility(View.VISIBLE);
                        createSpinner2Pogran();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void createSpinner2Pogran() {

        final String[] types = new String[]{
                "Тип ТС", "A  Легковые автомобили до 9 мест", "B  Прицепы к легковым автомобилям", "C  Грузовые автомобили и тракторы", "D  Тягачи", "E  Прицепы к грузовым втомобилям и тракторам", "F  Мотоциклы, мотоколяски и мотороллеры, мопеды", "L  Автобусы", "G  Прочие ТС"
        };
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, types);
        spinnerType.setAdapter(adapter1);

        spinnerType.setVisibility(View.VISIBLE);

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        textView.setVisibility(View.INVISIBLE);
                        textView2.setVisibility(View.INVISIBLE);
                        break;
                    default:
                        type = String.valueOf(spinnerType.getSelectedItem().toString().substring(0, 3));
                        textView.setVisibility(View.VISIBLE);

                        String tarif = Tables.getElement(type, period, Tables.osgoNoresident);
                        if (!Course.getFee("145", tarif).equals("?"))
                            textView.setText("Подлежащий к уплате страховой взнос: \n" + tarif + " EURO\n"
                                    + Course.getFee(Course.USD, tarif) + " USD\n" + Course.getFee(Course.RUB, tarif) + " RUB");
                        else {
                            textView.setText("Подлежащий к уплате страховой взнос: \n" + tarif + " EURO\n");
                            textView2.setVisibility(View.VISIBLE);
                            textView2.setTextColor(Color.RED);
                            textView2.setText("Для того, чтобы узнать подлежащий к уплате страховой взнос в USD и RUB, подключите устройство к интернету!");
                        }
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void allHide() {
        spinnerPeriod = (Spinner) findViewById(R.id.spinner5);
        spinnerPeriod.setVisibility(View.INVISIBLE);
        spinnerType = (Spinner) findViewById(R.id.spinner6);
        spinnerType.setVisibility(View.INVISIBLE);
        spinnerK1 = (Spinner) findViewById(R.id.spinner8);
        spinnerK1.setVisibility(View.INVISIBLE);
        spinnerK2 = (Spinner) findViewById(R.id.spinner9);
        spinnerK2.setVisibility(View.INVISIBLE);
        spinnerK3 = (Spinner) findViewById(R.id.spinner11);
        spinnerK3.setVisibility(View.INVISIBLE);
        spinnerDtp = (Spinner) findViewById(R.id.spinner10);
        spinnerDtp.setVisibility(View.INVISIBLE);
        spinnerLgoty = (Spinner) findViewById(R.id.spinner12);
        spinnerLgoty.setVisibility(View.INVISIBLE);
        spinnerEngineCapacityAvto = (Spinner) findViewById(R.id.spinner7);
        spinnerEngineCapacityAvto.setVisibility(View.INVISIBLE);
        textView = (TextView) findViewById(R.id.textView7);
        textView.setVisibility(View.INVISIBLE);
        textView = (TextView) findViewById(R.id.textView6);
        textView.setVisibility(View.INVISIBLE);
        textView1 = (TextView) findViewById(R.id.textView10);
        textView1.setVisibility(View.INVISIBLE);
        textView2 = (TextView) findViewById(R.id.textView11);
        textView2.setVisibility(View.INVISIBLE);
    }

    public void hideS6S12() {
        spinnerType = (Spinner) findViewById(R.id.spinner6);
        spinnerType.setVisibility(View.INVISIBLE);
        spinnerEngineCapacityAvto = (Spinner) findViewById(R.id.spinner7);
        spinnerEngineCapacityAvto.setVisibility(View.INVISIBLE);
        spinnerK1 = (Spinner) findViewById(R.id.spinner8);
        spinnerK1.setVisibility(View.INVISIBLE);
        spinnerK2 = (Spinner) findViewById(R.id.spinner9);
        spinnerK2.setVisibility(View.INVISIBLE);
        spinnerK3 = (Spinner) findViewById(R.id.spinner11);
        spinnerK3.setVisibility(View.INVISIBLE);
        spinnerDtp = (Spinner) findViewById(R.id.spinner10);
        spinnerDtp.setVisibility(View.INVISIBLE);
        spinnerLgoty = (Spinner) findViewById(R.id.spinner12);
        spinnerLgoty.setVisibility(View.INVISIBLE);
        textView = (TextView) findViewById(R.id.textView7);
        textView.setVisibility(View.INVISIBLE);
        textView1 = (TextView) findViewById(R.id.textView10);
        textView1.setVisibility(View.INVISIBLE);


    }

    public void hideS7S12() {

        spinnerK1 = (Spinner) findViewById(R.id.spinner8);
        spinnerK1.setVisibility(View.INVISIBLE);
        spinnerK2 = (Spinner) findViewById(R.id.spinner9);
        spinnerK2.setVisibility(View.INVISIBLE);
        spinnerK3 = (Spinner) findViewById(R.id.spinner11);
        spinnerK3.setVisibility(View.INVISIBLE);
        spinnerDtp = (Spinner) findViewById(R.id.spinner10);
        spinnerDtp.setVisibility(View.INVISIBLE);
        spinnerLgoty = (Spinner) findViewById(R.id.spinner12);
        spinnerLgoty.setVisibility(View.INVISIBLE);
        spinnerEngineCapacityAvto = (Spinner) findViewById(R.id.spinner7);
        spinnerEngineCapacityAvto.setVisibility(View.INVISIBLE);
        textView = (TextView) findViewById(R.id.textView7);
        textView.setVisibility(View.INVISIBLE);
        textView1 = (TextView) findViewById(R.id.textView10);
        textView1.setVisibility(View.INVISIBLE);
    }

    public void hideS8S12() {
        spinnerK1 = (Spinner) findViewById(R.id.spinner8);
        spinnerK1.setVisibility(View.INVISIBLE);
        spinnerK2 = (Spinner) findViewById(R.id.spinner9);
        spinnerK2.setVisibility(View.INVISIBLE);
        spinnerK3 = (Spinner) findViewById(R.id.spinner11);
        spinnerK3.setVisibility(View.INVISIBLE);
        spinnerDtp = (Spinner) findViewById(R.id.spinner10);
        spinnerDtp.setVisibility(View.INVISIBLE);
        spinnerLgoty = (Spinner) findViewById(R.id.spinner12);
        spinnerLgoty.setVisibility(View.INVISIBLE);
        textView = (TextView) findViewById(R.id.textView7);
        textView.setVisibility(View.INVISIBLE);
        textView1 = (TextView) findViewById(R.id.textView10);
        textView1.setVisibility(View.INVISIBLE);
    }

    public void hideS9S12() {

        spinnerK2 = (Spinner) findViewById(R.id.spinner9);
        spinnerK2.setVisibility(View.INVISIBLE);
        spinnerK3 = (Spinner) findViewById(R.id.spinner11);
        spinnerK3.setVisibility(View.INVISIBLE);
        spinnerDtp = (Spinner) findViewById(R.id.spinner10);
        spinnerDtp.setVisibility(View.INVISIBLE);
        spinnerLgoty = (Spinner) findViewById(R.id.spinner12);
        spinnerLgoty.setVisibility(View.INVISIBLE);
        textView = (TextView) findViewById(R.id.textView7);
        textView.setVisibility(View.INVISIBLE);
        textView1 = (TextView) findViewById(R.id.textView10);
        textView1.setVisibility(View.INVISIBLE);

    }

    public void hideS10S12() {


        spinnerK3 = (Spinner) findViewById(R.id.spinner11);
        spinnerK3.setVisibility(View.INVISIBLE);
        spinnerDtp = (Spinner) findViewById(R.id.spinner10);
        spinnerDtp.setVisibility(View.INVISIBLE);
        spinnerLgoty = (Spinner) findViewById(R.id.spinner12);
        spinnerLgoty.setVisibility(View.INVISIBLE);
        textView = (TextView) findViewById(R.id.textView7);
        textView.setVisibility(View.INVISIBLE);
        textView1 = (TextView) findViewById(R.id.textView10);
        textView1.setVisibility(View.INVISIBLE);

    }

    public void hideS11S12() {

        spinnerK3 = (Spinner) findViewById(R.id.spinner11);
        spinnerK3.setVisibility(View.INVISIBLE);
        spinnerLgoty = (Spinner) findViewById(R.id.spinner12);
        spinnerLgoty.setVisibility(View.INVISIBLE);
        textView = (TextView) findViewById(R.id.textView7);
        textView.setVisibility(View.INVISIBLE);
        textView1 = (TextView) findViewById(R.id.textView10);
        textView1.setVisibility(View.INVISIBLE);

    }

    public void hideS12() {

        spinnerLgoty = (Spinner) findViewById(R.id.spinner12);
        spinnerLgoty.setVisibility(View.INVISIBLE);
        textView = (TextView) findViewById(R.id.textView7);
        textView.setVisibility(View.INVISIBLE);
        textView1 = (TextView) findViewById(R.id.textView10);
        textView1.setVisibility(View.INVISIBLE);

    }

    public void hideText() {

        textView = (TextView) findViewById(R.id.textView7);
        textView.setVisibility(View.INVISIBLE);
        textView1 = (TextView) findViewById(R.id.textView10);
        textView1.setVisibility(View.INVISIBLE);

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void createSpinner1Osgo() {
        spinnerPeriod = (Spinner) findViewById(R.id.spinner5);
        spinnerPeriod.setVisibility(View.VISIBLE);

        final String[] periods = new String[]{
                "Срок страхования", "15 дней", "1 месяц", "2 месяца", "3 месяца", "4 месяца", "5 месяцев", "6 месяцев", "7 месяцев", "8 месяцев", "9 месяцев", "10 месяцев", "11 месяцев", "1 год"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, periods);
        spinnerPeriod.setAdapter(adapter);

        spinnerPeriod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        hideS6S12();
                        break;
                    default:
                        hideS6S12();
                        period = String.valueOf(spinnerPeriod.getSelectedItem());
                        createSpinner2Osgo();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    public void createSpinner2Osgo() {
        spinnerType = (Spinner) findViewById(R.id.spinner6);
        spinnerType.setVisibility(View.VISIBLE);
        textView = (TextView) findViewById(R.id.textView6);
        textView.setText("");
        textView.setVisibility(View.INVISIBLE);

        final String[] typeAdapter = new String[]{
                "Тип ТС", "Легковые автомобили (ВАЗ,ЗАЗ,«МОСКВИЧ»,АЗЛК,ИЖ,ГАЗ,ЛУАЗ,УАЗ)", "Легковые автомобили (остальные)", "Легковые автомобили - такси", "Электромобили",
                "Прицепы к легковым автомобилям", "Грузовые и грузопассажирские автомобили", "Тягачи",
                "Колесные тракторы и дорожные машины", "Гусеничные тракторы", "Прицепы и полуприцепы к грузовым автомобилям и тракторам",
                "Квадрициклы, мотоколяски, мотоциклы, мотороллеры и мопеды", "Автобусы", "Автобусы в регулярном сообщении", "Троллейбусы, трамваи"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, typeAdapter);
        spinnerType.setAdapter(adapter);

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        hideS7S12();
                        break;
                    case 1:
                        hideS7S12();
                        typeAvto = "1";
                        createSpinnerEngineCapacityAvtoOsgo();
                        break;
                    case 2:
                        hideS7S12();
                        typeAvto = "2";
                        createSpinnerEngineCapacityAvtoOsgo();
                        break;
                    case 3:
                        hideS7S12();
                        type = "A6 ";
                        createSpinnerK1Osgo();
                        break;
                    case 4:
                        hideS7S12();
                        createSpinnerElektroOrHybridOsgo();
                        break;
                    case 5:
                        hideS7S12();
                        createSpinnerTypeLighTrailerOsgo();
                        break;
                    case 6:
                        hideS7S12();
                        createSpinnerLoadCargoOsgo();
                        break;
                    case 7:
                        hideS7S12();
                        type = "D  ";
                        createSpinnerK1Osgo();
                        break;
                    case 8:
                        hideS7S12();
                        createSpinnerTraktorCapacityOsgo();
                        break;
                    case 9:
                        hideS7S12();
                        type = "M  ";
                        createSpinnerK1Osgo();
                        break;
                    case 10:
                        hideS7S12();
                        createSpinnerLoadTrailerOsgo();
                        break;

                    case 11:
                        hideS7S12();
                        createSpinnerMotoCapacityOsgo();
                        break;
                    case 12:
                        hideS7S12();
                        createSpinnerAutoBusOsgo();
                        break;
                    case 13:
                        hideS7S12();
                        type = "L4 ";
                        createSpinnerK1Osgo();
                        break;
                    case 14:
                        hideS7S12();
                        type = "W  ";
                        createSpinnerK1Osgo();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    public void createSpinnerK1Osgo() {
        spinnerK1 = (Spinner) findViewById(R.id.spinner8);
        spinnerK1.setVisibility(View.VISIBLE);
        final String[] k1Adapter = new String[]{
                "Место нахождения", "Город Минск, Минский район", "Города Брест, Витебск, Гомель, Гродно, Могилев", "Города >50 тыс. чел.(кроме Минского района)", "Прочие населенные пункты(кроме Минского района)"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, k1Adapter);
        spinnerK1.setAdapter(adapter);

        spinnerK1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        hideS9S12();
                        break;
                    case 1:
                        hideS9S12();
                        k1 = "1.5";
                        createSpinnerK2Osgo();
                        break;
                    case 2:
                        hideS9S12();
                        k1 = "1.2";
                        createSpinnerK2Osgo();
                        break;
                    case 3:
                        hideS9S12();
                        k1 = "1.0";
                        createSpinnerK2Osgo();
                        break;
                    case 4:
                        hideS9S12();
                        k1 = "0.8";

                        createSpinnerK2Osgo();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void createSpinnerK3Osgo() {
        spinnerK3 = (Spinner) findViewById(R.id.spinner11);
        spinnerK3.setVisibility(View.VISIBLE);
        final String[] k3Adapter = new String[]{
                "Возраст и стаж вождения", "До 25 лет вкл. со стажем вождения до 2 лет вкл.", "До 25 лет вкл. со стажем вождения свыше 2 лет", "Старше 25 лет со стажем вождения до 2 лет вкл.", "Старше 25 лет со стажем вождения свыше 2 лет"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, k3Adapter);
        spinnerK3.setAdapter(adapter);

        spinnerK3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        hideS12();
                        break;
                    case 1:
                        hideS12();
                        k3 = "1.3";
                        createSpinnerLgotyOsgo();
                        break;
                    case 2:
                        hideS12();
                        k3 = "1.1";
                        createSpinnerLgotyOsgo();
                        break;
                    case 3:
                        hideS12();
                        k3 = "1.2";
                        createSpinnerLgotyOsgo();
                        break;
                    case 4:
                        hideS12();
                        k3 = "1.0";
                        createSpinnerLgotyOsgo();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void createSpinnerK2Osgo() {
        spinnerK2 = (Spinner) findViewById(R.id.spinner9);
        spinnerK2.setVisibility(View.VISIBLE);
        final String[] k2Adapter = new String[]{
                "Класс аварийности по последнему годовому договору, оплаченному в полном объеме (коэф. К2)", "Заключается впервые", "2.0", "1.5", "1.2", "1.0", "0.9", "0.8", "0.7", "0.6", "0.5"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, k2Adapter);
        spinnerK2.setAdapter(adapter);

        spinnerK2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        hideS10S12();
                        break;
                    case 1:
                        hideS10S12();
                        k2 = "Впервые";
                        dtp = "0";
                        createSpinnerK3Osgo();
                        break;
                    case 2:
                        hideS10S12();
                        k2 = "2";
                        createSpinnerDtpOsgo();
                        break;
                    case 3:
                        hideS10S12();
                        k2 = "1.5";
                        createSpinnerDtpOsgo();
                        break;
                    case 4:
                        hideS10S12();
                        k2 = "1.2";
                        createSpinnerDtpOsgo();
                        break;
                    case 5:
                        hideS10S12();
                        k2 = "1";
                        createSpinnerDtpOsgo();
                        break;
                    case 6:
                        hideS10S12();
                        k2 = "0.9";
                        createSpinnerDtpOsgo();
                        break;
                    case 7:
                        hideS10S12();
                        k2 = "0.8";
                        createSpinnerDtpOsgo();
                        break;
                    case 8:
                        hideS10S12();
                        k2 = "0.7";
                        createSpinnerDtpOsgo();
                        break;
                    case 9:
                        hideS10S12();
                        k2 = "0.6";
                        createSpinnerDtpOsgo();
                        break;
                    case 10:
                        hideS10S12();
                        k2 = "0.5";
                        createSpinnerDtpOsgo();
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void createSpinnerDtpOsgo() {
        spinnerDtp = (Spinner) findViewById(R.id.spinner10);
        spinnerDtp.setVisibility(View.VISIBLE);
        final String[] DtpAdapter = new String[]{
                "Количество ДТП в предшествующий год", "0", "1", "2 и более"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DtpAdapter);
        spinnerDtp.setAdapter(adapter);

        spinnerDtp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        hideS11S12();

                        break;
                    case 1:
                        hideS11S12();
                        dtp = "0";
                        createSpinnerK3Osgo();
                        break;
                    case 2:
                        hideS11S12();
                        dtp = "1";
                        createSpinnerK3Osgo();
                        break;
                    case 3:
                        hideS11S12();
                        dtp = "2";
                        createSpinnerK3Osgo();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void createSpinnerLgotyOsgo() {
        spinnerLgoty = (Spinner) findViewById(R.id.spinner12);
        spinnerLgoty.setVisibility(View.VISIBLE);
        final String[] LgotyAdapter = new String[]{
                "Наличие иных льгот", "Да", "Нет"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, LgotyAdapter);
        spinnerLgoty.setAdapter(adapter);

        spinnerLgoty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        hideText();
                        break;
                    case 1:
                        hideText();
                        lgoty = "Да";
                        calculate();
                        break;
                    case 2:
                        hideText();
                        lgoty = "Нет";
                        calculate();
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void createSpinnerEngineCapacityAvtoOsgo() {
        spinnerEngineCapacityAvto = (Spinner) findViewById(R.id.spinner7);
        spinnerEngineCapacityAvto.setVisibility(View.VISIBLE);
        final String[] EngineCapacityAvtoAdapter = new String[]{
                "Объем двигателя", "До 1200 куб.см. вкл.", "От 1200 см.куб. до 1800 см.куб. вкл.", "От 1800 см.куб. до 2500 см.куб. вкл.", "От 2500 см.куб. до 3500 см.куб. вкл.", "Свыше 3500 см.куб."
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, EngineCapacityAvtoAdapter);
        spinnerEngineCapacityAvto.setAdapter(adapter);

        spinnerEngineCapacityAvto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        hideS8S12();
                        break;
                    case 1:
                        hideS8S12();
                        if (typeAvto.equals("1")) type = "N1 ";
                        else type = "A1 ";
                        createSpinnerK1Osgo();
                        break;
                    case 2:
                        hideS8S12();
                        if (typeAvto.equals("1")) type = "N2 ";
                        else type = "A2 ";
                        createSpinnerK1Osgo();
                        break;
                    case 3:
                        hideS8S12();
                        if (typeAvto.equals("1")) type = "N3 ";
                        else type = "A3 ";
                        createSpinnerK1Osgo();
                        break;
                    case 4:
                        hideS8S12();
                        if (typeAvto.equals("1")) type = "N4 ";
                        else type = "A4 ";
                        createSpinnerK1Osgo();
                        break;
                    case 5:
                        hideS8S12();
                        if (typeAvto.equals("1")) type = "N5 ";
                        else type = "A5 ";
                        createSpinnerK1Osgo();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void createSpinnerElektroOrHybridOsgo() {
        spinnerElektroOrHybrid = (Spinner) findViewById(R.id.spinner7);
        spinnerElektroOrHybrid.setVisibility(View.VISIBLE);
        final String[] ElektroOrHybridAdapter = new String[]{
                "Тип авто", "Электромобиль", "Гибридный автомобиль"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ElektroOrHybridAdapter);
        spinnerElektroOrHybrid.setAdapter(adapter);

        spinnerElektroOrHybrid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        hideS8S12();

                        break;
                    case 1:
                        hideS8S12();
                        type = "P1 ";
                        createSpinnerK1Osgo();
                        break;
                    case 2:
                        hideS8S12();
                        type = "P2 ";
                        createSpinnerK1Osgo();
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void createSpinnerTypeLighTrailerOsgo() {
        spinnerTypeLightTrailer = (Spinner) findViewById(R.id.spinner7);
        spinnerTypeLightTrailer.setVisibility(View.VISIBLE);
        final String[] TypeLighTrailerAdapter = new String[]{
                "Тип прицепа", "Грузовые и складные жилые", "Прицеп-дача (караван)"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, TypeLighTrailerAdapter);
        spinnerTypeLightTrailer.setAdapter(adapter);

        spinnerTypeLightTrailer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        hideS8S12();
                        break;
                    case 1:
                        hideS8S12();
                        type = "B1 ";
                        createSpinnerK1Osgo();
                        break;
                    case 2:
                        hideS8S12();
                        type = "B2 ";
                        createSpinnerK1Osgo();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void createSpinnerLoadCargoOsgo() {
        spinnerLoadCargo = (Spinner) findViewById(R.id.spinner7);
        spinnerLoadCargo.setVisibility(View.VISIBLE);
        final String[] LoadCargoAdapter = new String[]{
                "Грузоподъемность", "До 1т. вкл.", "От 1т. до 2т. вкл.", "От 2т. до 8т. вкл.", "От 8т. до 15 вкл.", "От 15т. до 25т. вкл.", "Свыше 25т."
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, LoadCargoAdapter);
        spinnerLoadCargo.setAdapter(adapter);

        spinnerLoadCargo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        hideS8S12();

                        break;
                    case 1:
                        hideS8S12();
                        type = "C0 ";
                        createSpinnerK1Osgo();
                        break;
                    case 2:
                        hideS8S12();
                        type = "C1";
                        createSpinnerK1Osgo();
                        break;
                    case 3:
                        hideS8S12();
                        type = "C2 ";
                        createSpinnerK1Osgo();
                        break;
                    case 4:
                        hideS8S12();
                        type = "C3 ";
                        createSpinnerK1Osgo();
                        break;
                    case 5:
                        hideS8S12();
                        type = "C4 ";
                        createSpinnerK1Osgo();
                        break;
                    case 6:
                        hideS8S12();
                        type = "C5 ";
                        createSpinnerK1Osgo();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void createSpinnerTraktorCapacityOsgo() {
        spinnerTraktorCapacity = (Spinner) findViewById(R.id.spinner7);
        spinnerTraktorCapacity.setVisibility(View.VISIBLE);
        final String[] TraktorCapacityAdapter = new String[]{
                "Мощность двигателя", "До 50 л.с. вкл.", "От 50 л.с. до 200 л.с. вкл.", "Свыше 200 л.с."
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, TraktorCapacityAdapter);
        spinnerTraktorCapacity.setAdapter(adapter);

        spinnerTraktorCapacity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        hideS8S12();

                        break;
                    case 1:
                        hideS8S12();
                        type = "V1 ";
                        createSpinnerK1Osgo();
                        break;
                    case 2:
                        hideS8S12();
                        type = "V2";
                        createSpinnerK1Osgo();
                        break;
                    case 3:
                        hideS8S12();
                        type = "V3 ";
                        createSpinnerK1Osgo();
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void createSpinnerLoadTrailerOsgo() {
        spinnerLoadTrailer = (Spinner) findViewById(R.id.spinner7);
        spinnerLoadTrailer.setVisibility(View.VISIBLE);
        final String[] LoadTrailerAdapter = new String[]{
                "Грузоподъемность", "До 5 т. вкл.", "От 5 т. до 10 т. вкл.", "От 10 т. до 20 т. вкл.", "Свыше 20 т."
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, LoadTrailerAdapter);
        spinnerLoadTrailer.setAdapter(adapter);

        spinnerLoadTrailer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        hideS8S12();

                        break;
                    case 1:
                        hideS8S12();
                        type = "E0 ";
                        createSpinnerK1Osgo();
                        break;
                    case 2:
                        hideS8S12();
                        type = "E1";
                        createSpinnerK1Osgo();
                        break;
                    case 3:
                        hideS8S12();
                        type = "E2 ";
                        createSpinnerK1Osgo();
                        break;
                    case 4:
                        hideS8S12();
                        type = "E3 ";
                        createSpinnerK1Osgo();
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void createSpinnerMotoCapacityOsgo() {
        spinnerMotoCapacity = (Spinner) findViewById(R.id.spinner7);
        spinnerMotoCapacity.setVisibility(View.VISIBLE);
        final String[] MotoCapacityAdapter = new String[]{
                "Объем двигателя", "До 150 куб.см. вкл.", "От 150 куб.см. до 750 куб.см. вкл.", "Свыше 750 куб.см."
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MotoCapacityAdapter);
        spinnerMotoCapacity.setAdapter(adapter);

        spinnerMotoCapacity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        hideS8S12();

                        break;
                    case 1:
                        hideS8S12();
                        type = "F1 ";
                        createSpinnerK1Osgo();
                        break;
                    case 2:
                        hideS8S12();
                        type = "F2";
                        createSpinnerK1Osgo();
                        break;
                    case 3:
                        hideS8S12();
                        type = "F3 ";
                        createSpinnerK1Osgo();
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void createSpinnerAutoBusOsgo() {
        spinnerAutoBus = (Spinner) findViewById(R.id.spinner7);
        spinnerAutoBus.setVisibility(View.VISIBLE);
        final String[] AutoBusAdapter = new String[]{
                "Число мест", "Число мест для сиденья до 20 вкл.", "Число мест для сиденья от 20 до 40 вкл.", "Число мест для сиденья свыше 40"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, AutoBusAdapter);
        spinnerAutoBus.setAdapter(adapter);

        spinnerAutoBus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        hideS8S12();

                        break;
                    case 1:
                        hideS8S12();
                        type = "L1 ";
                        createSpinnerK1Osgo();
                        break;
                    case 2:
                        hideS8S12();
                        type = "L2";
                        createSpinnerK1Osgo();
                        break;
                    case 3:
                        hideS8S12();
                        type = "L3 ";
                        createSpinnerK1Osgo();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void calculate() {
        textView = (TextView) findViewById(R.id.textView7);
        textView.setVisibility(View.VISIBLE);
        textView1 = (TextView) findViewById(R.id.textView10);
        textView1.setVisibility(View.VISIBLE);
        textView1.setTextColor(Color.RED);

        String tarif = "";

        if (osgoOrKomplex.equals("osgo"))
            tarif = Tables.getElement(type, period, Tables.osgoResident);
        else
            tarif = Tables.getElement(type, period, Tables.komplexResident);

        double itogKofFiz;
        double vznos;
        double endK2;
        double vznosRBFiz;
        double vznosRBYur;

        if (k2.equals("Впервые") || (!period.equals("1 год") && dtp.equals("0") && (!k2.equals("1.2")) && (!k2.equals("1")) && (!k2.equals("1.5")) && (!k2.equals("2"))))
            endK2 = 0;
        else if (!period.equals("1 год") && dtp.equals("0") && ((k2.equals("1.2")) || (k2.equals("1.5")) || (k2.equals("2")) || (k2.equals("1"))))
            endK2 = Double.valueOf(k2) - 1;
        else
            endK2 = Double.valueOf(Tables.getBonusMalus(dtp, k2, Tables.bonusMalus)) - 1;

        itogKofFiz = Double.valueOf(k1) - 1 + endK2 + Double.valueOf(k3) - 1 + 1;

        if (lgoty.equals("Да")) {
            itogKofFiz = itogKofFiz - 0.5;
            if (itogKofFiz < 0.3) itogKofFiz = 0.3;
        }

        double x = itogKofFiz * Double.valueOf(tarif);
        double y = Math.round(x * 100);
        vznos = y / 100;

        if (Course.getCourse(Course.EUR).equals(Course.error)) {
            textView.setText("Тариф: " + tarif + " евро\n" + "Взнос: " + vznos+" евро\n");
            textView1.setText("Для того, чтобы узнать страховой взнос в белорусских рублях, подключите устройство к интернету!");
        } else {
            textView1.setVisibility(View.INVISIBLE);
            vznosRBFiz = Math.round(vznos * Double.valueOf(Course.getCourse(Course.EUR))*100 ) ;

            textView.setText("Тариф: " + tarif + " евро\n" + "Взнос: " + vznos + " евро\nПодлежит уплате: " + vznosRBFiz/100  + " бел.руб.");
        }
    }
}