package com.irozon.sneakersample;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.irozon.sneaker.Sneaker;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btShowWarning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btShowWarning = findViewById(R.id.btShowWarning);

        btShowWarning.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btShowWarning:
                Sneaker.with(this)
                        .setMessage("\uD83E\uDD17 buraya mesaj gir", R.color.white)
                        .setDuration(4000)
                        .setDismissOnTouch(false)
                        .autoHide(true)
                        .setIcon(R.drawable.ic_warning, R.color.white, false)
                        .setTextSize(18)
                        .setCornerRadius(8, 8)
                        .sneak(R.color.colorAccent);
                break;
        }
    }
}