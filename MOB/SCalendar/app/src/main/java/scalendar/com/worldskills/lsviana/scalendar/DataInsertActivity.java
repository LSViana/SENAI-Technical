package scalendar.com.worldskills.lsviana.scalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import scalendar.com.worldskills.lsviana.scalendar.data.StudentDAO;
import scalendar.com.worldskills.lsviana.scalendar.model.Student;
import scalendar.com.worldskills.lsviana.scalendar.utils.StudentDataHelper;

public class DataInsertActivity extends AppCompatActivity {

    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_insert);
        final StudentDAO studentDAO = new StudentDAO();
        final StudentDataHelper helper = new StudentDataHelper(this);
        //
        saveButton = findViewById(R.id.btnSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = helper.getStudent();
                studentDAO.insert(student);
                Toast.makeText(getApplicationContext(), "Student successfully inserted!", Toast.LENGTH_LONG).show();
            }
        });
    }

}
