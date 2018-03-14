package sp.senai.stdevelopment.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.List;

import sp.senai.stdevelopment.R;
import sp.senai.stdevelopment.data.ContactDAO;
import sp.senai.stdevelopment.model.Contact;

public class ListActivity extends AppCompatActivity {

    private ContactDAO dao;
    private ListView lvContacts;
    private List<Contact> contacts;

    public ListActivity() {
        dao = new ContactDAO(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //
        lvContacts = findViewById(R.id.lvContacts);
        registerForContextMenu(lvContacts);
        lvContacts.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                openContextMenu(view);
                return true;
            }
        });
        //
        fillList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(R.string.edit);
        menu.add(R.string.remove);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String title = item.getTitle().toString();
        Contact c = contacts.get(item.getItemId());
        //
        switch(title) {
            case "Edit":
                Intent intent = new Intent(this, EditContactActivity.class);
                //
                intent.putExtra("id", c.getId());
                intent.putExtra("username", c.getName());
                intent.putExtra("message", c.getMessage());
                //
                startActivity(intent);
                break;
            case "Remove":
                dao.delete(c.getId());
                Toast.makeText(this, "Removed Contact " + c.getName(), Toast.LENGTH_SHORT).show();
                fillList();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void fillList() {
        contacts = dao.searchAll();
        ArrayAdapter<Contact> adapter = new ArrayAdapter<Contact>(getBaseContext(), android.R.layout.simple_list_item_1, contacts);
        lvContacts.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //
        fillList();
    }
}
