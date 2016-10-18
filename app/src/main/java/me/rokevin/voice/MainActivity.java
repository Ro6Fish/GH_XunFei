package me.rokevin.voice;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import me.rokevin.lib.voice.VoiceUtil;

public class MainActivity extends Activity {

    EditText etContent;
    Button btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etContent = (EditText) findViewById(R.id.et_content);
        btnPlay = (Button) findViewById(R.id.btn_play);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                VoiceUtil.play(MainActivity.this, etContent.getText().toString());
            }
        });
    }
}
