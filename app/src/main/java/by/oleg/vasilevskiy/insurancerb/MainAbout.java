package by.oleg.vasilevskiy.insurancerb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * Created by Yulya on 24.10.2015.
 */
public class MainAbout extends AppCompatActivity {
    final static String urlMarket="market://details?id=by.oleg.vasilevskiy.insurancerb";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
   // Страница в PlayMarket для лайка
    public void activeFingerActivity(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(urlMarket));
        startActivity(intent);
    }
}
