package dtpfeatures.senai.com.datetimepickerfeatures;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private TextView tvEnterDate;
    private TextView tvExitDate;
    private TextView tvResultCaption;
    private TextView tvResult;
    private Button btnCalculate;
    private DatePickerDialog.OnDateSetListener dateListenerIn, dateListenerOut;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    // Dates
    private Date enterDate = null, exitDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting components
        tvEnterDate = findViewById(R.id.tvEnterDate);
        tvExitDate = findViewById(R.id.tvExitDate);
        tvResultCaption = findViewById(R.id.tvResultCaption);
        tvResult = findViewById(R.id.tvResult);
        btnCalculate = findViewById(R.id.btnCalculate);

        // Adding Event Listeners
        tvEnterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int
                        year = calendar.get(Calendar.YEAR),
                        month = calendar.get(Calendar.MONTH),
                        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                // Opening DatePickerDialog to Enter Date
                DatePickerDialog dp = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateListenerIn,
                        year,
                        month,
                        dayOfMonth
                );

                dp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dp.show();
            }
        });

        tvExitDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int
                        year = calendar.get(Calendar.YEAR),
                        month = calendar.get(Calendar.MONTH),
                        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                // Opening DatePickerDialog to Enter Date
                DatePickerDialog dp = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateListenerOut,
                        year,
                        month,
                        dayOfMonth
                );

                dp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dp.show();
            }
        });
        
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(enterDate == null || exitDate == null) {
                    Toast.makeText(MainActivity.this, "You must select both dates before perform a calculation!", Toast.LENGTH_SHORT).show();
                } else {
                    long diff = exitDate.getTime() - enterDate.getTime();
                    long
                        totalSeconds = diff / 1000, totalMinutes = (diff / 1000) / 60, totalHours = ((diff / 1000) / 60) / 60;
                    long minutes = totalMinutes % 60, seconds = totalSeconds % 60;
                    String formattedResult = String.format("%02dh %02dmin %02ds", totalHours, minutes, seconds);
                    tvResult.setText(formattedResult);
                    Toast.makeText(MainActivity.this, "Date successfully selected!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dateListenerIn = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int _year, int _month, int _dayOfMonth) {
                enterDate = new GregorianCalendar(_year, _month, _dayOfMonth).getTime();
                String formattedDate = sdf.format(enterDate);
                tvEnterDate.setText(formattedDate);
            }
        };

        dateListenerOut = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int _year, int _month, int _dayOfMonth) {
                exitDate = new GregorianCalendar(_year, _month, _dayOfMonth).getTime();
                String formattedDate = sdf.format(exitDate);
                tvExitDate.setText(formattedDate);
            }
        };
    }
}
