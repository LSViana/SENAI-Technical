package sp.senai.stdevelopment.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import sp.senai.stdevelopment.R;

public class HistoryActivity extends AppCompatActivity {

    private FloatingActionButton fabBackHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        //
        fabBackHistory = findViewById(R.id.fabBackHistory);
        fabBackHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
