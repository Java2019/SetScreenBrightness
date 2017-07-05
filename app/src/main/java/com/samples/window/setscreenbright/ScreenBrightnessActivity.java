package com.samples.window.setscreenbright;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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

        int percBrigh = brightness * 100 / 255;
        seekBar.setProgress(percBrigh);

        // Выводим в текстовое поле значение яркости экрана в %
        text.setText(percBrigh + "%");

        seekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        // Cохраняем значение яркости экрана в системных настройках
        android.provider.Settings.System.putInt(
                getContentResolver(),
                android.provider.Settings.System.SCREEN_BRIGHTNESS,
                brightness);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int arg1, boolean b) {
        text.setText(arg1 + "%");
        // Получаем параметр, определяющий яркость экрана
        WindowManager.LayoutParams params = getWindow().getAttributes();
        // Устанавливаем значение поля screenBrightness,
        // преобразуя значение яркости в % (0...100) в диапазон
        // значений поля screenBrightness (0...1)
        params.screenBrightness = (float)arg1 / 100;
        // Устанавливаем яркость экрана
        getWindow().setAttributes(params);
        brightness = arg1 * 255 / 100;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
