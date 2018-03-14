package sp.senai.stdevelopment.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sp.senai.stdevelopment.R;
import sp.senai.stdevelopment.data.ContactDAO;
import sp.senai.stdevelopment.model.Contact;

public class EditContactActivity extends AppCompatActivity {

    private final ContactDAO dao;

    public EditContactActivity() {
        dao = new ContactDAO(this);
    }

    private Button btnSaveContact;
    private EditText etUsername;
    private EditText etMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        //
        etUsername = findViewById(R.id.etUsernameEdit);
        etMessage = findViewById(R.id.etMessageEdit);
        btnSaveContact = findViewById(R.id.btnSaveContact);
        //
        Intent intent = getIntent();
        final Long cId = intent.getLongExtra("id", -1);
        String cName = intent.getStringExtra("username");
        String cMessage = intent.getStringExtra("message");
        etUsername.setText(cName);
        etMessage.setText(cMessage);
        //
        btnSaveContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etUsername.getText().toString();
                String message = etMessage.getText().toString();
                //
                if(name.length() < 1) {
                    Toast.makeText(EditContactActivity.this, "Fill a valid name!", Toast.LENGTH_SHORT).show();
                }
                else if(message.length() < 1) {
                    Toast.makeText(EditContactActivity.this, "Fill a valid message!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Contact c = new Contact(cId, name, message);
                    dao.update(c);
                    Toast.makeText(EditContactActivity.this, "Update successfully done!", Toast.LENGTH_SHORT).show();
                    //
                    finish();
                }
            }
        });
    }
}
