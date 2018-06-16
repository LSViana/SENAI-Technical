package sstorage.mobile.senai.com.sstorage.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sstorage.mobile.senai.com.sstorage.R;
import sstorage.mobile.senai.com.sstorage.config.RetrofitConfig;
import sstorage.mobile.senai.com.sstorage.data.ApiContext;
import sstorage.mobile.senai.com.sstorage.utils.AppUtils;
import sstorage.mobile.senai.com.sstorage.view.adapter.EnvironmentAdapter;

public class MainAdminActivity extends AppCompatActivity {

    private Button btnLogout;
    private TextView tvUserName;
    private Button btnManageEnvironments;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainadmin);
        getComponents();
        setUserData();
        addLogout();
        addManageIntents();
    }

    private void addManageIntents() {
        // Environments
        btnManageEnvironments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAdminActivity.this, EnvironmentsActivity.class);
                startActivity(intent);
            }
        });
        // Movements
        // Patrimonies
        // Access Control
    }

    private void addLogout() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SharedPreferences.Editor editor = getSharedPreferences(AppUtils.SHARED_PREFERENCES, Context.MODE_PRIVATE).edit();
                AppUtils.logout(editor);
                // Going back to the Login Activity
                finish();
            }
        });

    }

    private void setUserData() {
        String userName = getSharedPreferences(AppUtils.SHARED_PREFERENCES, Context.MODE_PRIVATE).getString(AppUtils.SP_USERNAME, null);
        if(userName != null)
            tvUserName.setText(userName);
    }

    private void getComponents() {
        btnLogout = findViewById(R.id.btnLogout);
        tvUserName = findViewById(R.id.tvUserName);
        // Getting Manage buttons
        btnManageEnvironments = findViewById(R.id.btnManageEnvironments);
    }

}
