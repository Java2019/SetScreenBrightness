package com.samples.window.setscreenbright;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ScreenBrightnessActivity extends AppCompatActivity
        implements View.OnClickListener, SeekBar.OnSeekBarChangeListener{

    private TextView text;
    private SeekBar seekBar;
    private Button bSetBright;

    private int brightness = 128;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_brightness);

        text = (TextView)findViewById(R.id.text);
        seekBar = (SeekBar)findViewById(R.id.seek);
        bSetBright = (Button)findViewById(R.id.bSetBright);

        bSetBright.setOnClickListener(this);

        try {
            brightness = android.provider.Settings.System.getInt(
                    getContentResolver(),
                    android.provider.Settings.System.SCREEN_BRIGHTNESS
            );
        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
