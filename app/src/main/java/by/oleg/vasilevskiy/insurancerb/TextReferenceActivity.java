package by.oleg.vasilevskiy.insurancerb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class TextReferenceActivity extends AppCompatActivity {
    public static String option;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_reference);
        setText();
    }
    // Выбор текста для справки в зависимости от кнопки
    void setText() {
        text = (TextView) findViewById(R.id.textView8);
        text.setMovementMethod(new ScrollingMovementMethod());
        text.setText(option);
        switch (option) {
            case "1":
                text.setText(R.string.textreferencedtp);
                break;
            case "2":
                text.setText(R.string.surcharge);
                break;
            case "3":
                text.setText(R.string.komplex);
                break;
            case "4":
                text.setText(R.string.izveschenie);
                break;
        }
    }
}
