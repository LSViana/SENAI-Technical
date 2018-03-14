package sp.senai.stdevelopment.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import sp.senai.stdevelopment.R;

public class InformativeActivity extends AppCompatActivity {

    private String username;
    private Button btnHistory;
    private Button btnContact;
    private Button btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informative);
        //
        final Intent intent = getIntent();
        username = intent.getStringExtra("username");
        Toast.makeText(this, "Welcome, " + username, Toast.LENGTH_SHORT).show();
        //
        btnHistory = findViewById(R.id.btnHistory);
        btnContact = findViewById(R.id.btnContact);
        btnList = findViewById(R.id.btnList);
        //
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent subIntent = new Intent(getBaseContext(), HistoryActivity.class);
                startActivity(subIntent);
            }
        });
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent subIntent = new Intent(getBaseContext(), ContactActivity.class);
                startActivity(subIntent);
            }
        });
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent subIntent = new Intent(getBaseContext(), ListActivity.class);
                startActivity(subIntent);
            }
        });
    }
}