package by.oleg.vasilevskiy.insurancerb;

import android.os.StrictMode;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Course {
    final static String DATE_FORMAT = "MM/dd/yyyy";
    final static String TEXT_START = "<Rate>";
    final static String TEXT_END = "</Rate>";
    final static int NUMBER_DIGITS_COURSE = 6;
    static String course;
    final static String USD = "145";
    final static String EUR = "292";
    final static String PLN = "293";
    final static String RUB = "298";
    final static String error = "Error";
    static String courseEUR = "";
    static String courseUSD = "";
    static String coursePLN = "";
    static String courseRUB = "";
    static String dateCourse = "";

// Получить ф-лу: курс евро/курс валюты* тариф (для Зеленой карты)

    public static String getFee(String code, String tarifan) {
        if (!Course.getCourse(code).equals(error) && !Course.getCourse(EUR).equals(error)) {

            double codeCourse = Double.valueOf(Course.getCourse(code));
            double euroCourse = Double.valueOf(Course.getCourse(EUR));
            double tarif = Double.valueOf(tarifan);
            double result = 0;

            if (code.equals("293")) result=Math.round(euroCourse / codeCourse * tarif*10);
            if (code.equals("298")) result=Math.round(euroCourse / codeCourse * tarif*100);
            if (code.equals("145")) result=Math.round(euroCourse / codeCourse * tarif);

            return String.valueOf((int) result);
        } else return "?";
    }

    // Получить курс валюты по коду валюты
    public static String getValueFromCode(String code) {
        if (code.equals(EUR)) return courseEUR;
        else if (code.equals(USD)) return courseUSD;
        else if (code.equals(RUB)) return courseRUB;
        else if (code.equals(PLN)) return coursePLN;
        else return null;
    }
    // Получить курс валюты

    public static String getCourse(String code) {
        // Разрешение на интернет
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String dateStr = String.valueOf(dateFormat.format(date));

        if (dateCourse.equals(dateStr) && !getValueFromCode(code).equals(""))
            return getValueFromCode(code);
        else {
            try {
                // Получаем поток их XML файла и записываем его в строку
                URL url = new URL("http://www.nbrb.by/Services/XmlExRatesDyn.aspx?curId=" + code + "&fromDate=" + dateStr + "&toDate=" + dateStr);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                int i = -1;
                String buffer = "";
                while ((i = in.read()) != -1) {
                    buffer = buffer + String.valueOf((char) i);
                }
                int indexStart = buffer.indexOf(TEXT_START)  + NUMBER_DIGITS_COURSE;
                int indexEnd = buffer.indexOf(TEXT_END);

                course = buffer.substring(indexStart, indexEnd);
                dateCourse = dateStr;

                if (code.equals(EUR)) courseEUR = course;
                if (code.equals(USD)) courseUSD = course;
                if (code.equals(RUB)) courseRUB = course;
                if (code.equals(PLN)) coursePLN = course;

                return course;
            } catch (IOException e) {
                return error;
            }
        }
    }
}