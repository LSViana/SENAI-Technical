package scalendar.com.worldskills.lsviana.scalendar.utils;

import android.widget.EditText;
import android.widget.SeekBar;

import scalendar.com.worldskills.lsviana.scalendar.DataInsertActivity;
import scalendar.com.worldskills.lsviana.scalendar.R;
import scalendar.com.worldskills.lsviana.scalendar.model.Student;

/**
 * Created by Lucas Viana on 2/21/2018.
 */

public class StudentDataHelper {

    private final DataInsertActivity dia;
    private final EditText etName;
    private final EditText etAddress;
    private final EditText etEmail;
    private final EditText etTelephone;
    private final SeekBar sbClassification;

    public StudentDataHelper(DataInsertActivity dia) {
        this.dia = dia;
        //
        this.etName = dia.findViewById(R.id.etName);
        this.etAddress = dia.findViewById(R.id.etAddress);
        this.etEmail = dia.findViewById(R.id.etEmail);
        this.etTelephone = dia.findViewById(R.id.etTelephone);
        this.sbClassification = dia.findViewById(R.id.sbClassification);
    }

    public Student getStudent() {
        //public Student(String name, String address, String email, String telephone, Integer classification) {
        return new Student(
                etName.getText().toString(),
                etAddress.getText().toString(),
                etEmail.getText().toString(),
                etTelephone.getText().toString(),
                sbClassification.getProgress()
        );
    }
}
