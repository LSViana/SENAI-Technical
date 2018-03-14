package sp.senai.stdevelopment.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sp.senai.stdevelopment.R;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText etUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        btnLogin = findViewById(R.id.btnLogin);
        etUsername = findViewById(R.id.etUsername);
        //
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                if(username.length() < 1) {
                    Toast.makeText(MainActivity.this, "You can't make login with an empty name!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getBaseContext(), InformativeActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }
            }
        });
    }
}
