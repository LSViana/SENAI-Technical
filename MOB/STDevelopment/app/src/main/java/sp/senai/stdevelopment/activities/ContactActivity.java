package sp.senai.stdevelopment.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sp.senai.stdevelopment.R;
import sp.senai.stdevelopment.data.ContactDAO;
import sp.senai.stdevelopment.model.Contact;

public class ContactActivity extends AppCompatActivity {

    private ContactDAO dao;

    public ContactActivity() {
        dao = new ContactDAO(this);
    }

    private EditText etUsernameContact;
    private EditText etMessage;
    private Button btnSendContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        //
        etUsernameContact = findViewById(R.id.etUsernameContact);
        etMessage = findViewById(R.id.etMessage);
        btnSendContact = findViewById(R.id.btnSendContact);
        //
        btnSendContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact c = new Contact();
                c.setName(etUsernameContact.getText().toString());
                c.setMessage(etMessage.getText().toString());
                //
                if(c.getName().length() < 1) {
                    Toast.makeText(ContactActivity.this, "Fill a valid name!", Toast.LENGTH_SHORT).show();
                } else if(c.getMessage().length() < 1) {
                    Toast.makeText(ContactActivity.this, "Fill a valid message!", Toast.LENGTH_SHORT).show();
                } else {
                    dao.insert(c);
                    Toast.makeText(ContactActivity.this, "Contact successfully added!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
