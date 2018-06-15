package sstorage.mobile.senai.com.sstorage.view;

import android.content.Context;
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
    private RecyclerView rvEnvironments;
    private ArrayList<sstorage.mobile.senai.com.sstorage.model.Environment> environmentList;
    private EnvironmentAdapter environmentAdapter;
    private SharedPreferences sharedPreferences;
    private ApiContext apiContext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainadmin);
        getComponents();
        setUserData();
        addLogout();
        addRecyclerView();
    }

    private void addRecyclerView() {
        // Configuring variables
        LinearLayoutManager llm = new LinearLayoutManager(this);
        environmentList = new ArrayList<>();
        environmentAdapter = new EnvironmentAdapter(environmentList, getApplicationContext());
        rvEnvironments.setLayoutManager(llm);
        rvEnvironments.setAdapter(environmentAdapter);
        listEnvironments();
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
        rvEnvironments = findViewById(R.id.rvEnvironments);
        sharedPreferences = getSharedPreferences(AppUtils.SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    private void listEnvironments() {
        String token = sharedPreferences.getString(AppUtils.SP_TOKEN, null);
        if(token != null) {
            // There is a token available
            apiContext = new RetrofitConfig(token)
                    .getApiContext();
            Call<sstorage.mobile.senai.com.sstorage.model.Environment[]> call = apiContext.getEnvironments();
            call.enqueue(new Callback<sstorage.mobile.senai.com.sstorage.model.Environment[]>() {
                @Override
                public void onResponse(Call<sstorage.mobile.senai.com.sstorage.model.Environment[]> call, Response<sstorage.mobile.senai.com.sstorage.model.Environment[]> response) {
                    if(response.isSuccessful()) {
                        sstorage.mobile.senai.com.sstorage.model.Environment[] envArray = response.body();
                        for(sstorage.mobile.senai.com.sstorage.model.Environment env : envArray) {
                            environmentList.add(env);
                        }
                        environmentAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<sstorage.mobile.senai.com.sstorage.model.Environment[]> call, Throwable t) {
                    Toast.makeText(MainAdminActivity.this, getString(R.string.error_servercontact), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
