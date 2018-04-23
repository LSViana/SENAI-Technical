package br.com.senai.meuslivros;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.security.PublicKey;

public class ColorsActivity extends AppCompatActivity {

    private RadioGroup rbGroup1;
    private RadioButton chosenRadioButton;
    private Button btnCadastrar;
    private static final String PREFERENCES_FILE = "preferences_file";
    private static String SELECTED_COLOR = "selected_color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);
        //
        rbGroup1 = findViewById(R.id.rbGroup1);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        //
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int chosenRadioButtonId = rbGroup1.getCheckedRadioButtonId();
                SharedPreferences preferences = getSharedPreferences(PREFERENCES_FILE, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                //
                if(chosenRadioButtonId > 0) {
                    chosenRadioButton = findViewById(chosenRadioButtonId);
                    String selectedValue = chosenRadioButton.getText().toString();
                    //
                    editor.putString(SELECTED_COLOR, selectedValue);
                    editor.apply();
                }
            }
        });
    }
}
