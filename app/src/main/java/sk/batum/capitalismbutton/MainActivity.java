package sk.batum.capitalismbutton;

import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    MediaPlayer mp;
    private AdView mAdView;
    int x = 0;

    public void ideme (View view) {
        int a;
        x += 1;
        Random rnd = new Random();
        a = rnd.nextInt(2);
        if (a == 0) {
            mp = MediaPlayer.create(this, R.raw.trump1);
        } else {
            mp = MediaPlayer.create(this, R.raw.trump2);
        }
        mp.start();
        if (x == 5) {
            Toast.makeText(MainActivity.this, "You've pressed the button 5 times. Now go get a life.", Toast.LENGTH_LONG).show();
        }
        if (x == 20) {
            Toast.makeText(MainActivity.this, "You've pressed the button 20 times now. " +
                    "I did not expect anyone to get so far. " +
                    "Keep tapping and you may get a surprise.", Toast.LENGTH_LONG).show();
        }
        if  (x > 0 && x%100 == 0)
        {
            Toast.makeText(MainActivity.this, String.valueOf(x) + ", nice!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.tlacitko);
        MobileAds.initialize(this,"ca-app-pub-8301997028694986~9553725813");
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img.setImageResource(R.drawable.btn_btc_pressed);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    img.setImageResource(R.drawable.btn_btc);
                }
                return false;
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

}
