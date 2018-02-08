package br.com.senai.screenelements;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Toast;

public class ConstraintLayout extends AppCompatActivity {

    private RadioButton rbPodcasts;
    private RadioButton rbVideos;
    private Button btnVote;
    private AlertDialog.Builder dialog;
    private SeekBar sbRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constraint_layout);
        rbPodcasts = findViewById(R.id.rbPodcasts);
        rbVideos = findViewById(R.id.rbVideos);
        btnVote = findViewById(R.id.btnVote);
        sbRate = findViewById(R.id.sbRate);
        // Standard Checked RadioButton
        rbPodcasts.setChecked(true);

        // Listeners
        rbPodcasts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rbVideos.setChecked(rbPodcasts.isChecked() ^ true);
            }
        });
        rbVideos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rbPodcasts.setChecked(rbVideos.isChecked() ^ true);
            }
        });
        btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String resultMessage = getString(R.string.voted_text);
                String userOption = null;
                Integer rateAttributed = sbRate.getProgress();
                if(rbPodcasts.isChecked()) {
                    userOption = getString(R.string.podcasts);
                }
                else {
                    userOption = getString(R.string.videos);
                }
                resultMessage = String.format(resultMessage, userOption);
                // Showing result on Dialog
                dialog.setTitle(R.string.warning);
                dialog.setMessage(String.format(String.format("%s\n%s", getString(R.string.are_you_sure), getString(R.string.rate_attributed)), userOption, rateAttributed));
                dialog.show();
            }
        });
        // Dialogs
        dialog = new AlertDialog.Builder(this);
        dialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), R.string.vote_success, Toast.LENGTH_LONG).show();
            }
        });
        dialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), R.string.vote_failed, Toast.LENGTH_LONG).show();
            }
        });
        dialog.create();
    }
}
