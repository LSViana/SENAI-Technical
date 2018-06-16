package sstorage.mobile.senai.com.sstorage.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sstorage.mobile.senai.com.sstorage.R;
import sstorage.mobile.senai.com.sstorage.config.RetrofitConfig;
import sstorage.mobile.senai.com.sstorage.data.ApiContext;
import sstorage.mobile.senai.com.sstorage.model.Environment;
import sstorage.mobile.senai.com.sstorage.utils.AppUtils;
import sstorage.mobile.senai.com.sstorage.view.adapter.EnvironmentAdapter;

public class EnvironmentsActivity extends AppCompatActivity {

    private RecyclerView rvEnvironments;
    private ArrayList<sstorage.mobile.senai.com.sstorage.model.Environment> environmentList;
    private EnvironmentAdapter environmentAdapter;
    private SharedPreferences sharedPreferences;
    private ApiContext apiContext;
    private Button btnSend;
    private Button btnClear;
    private TextInputLayout tilEnvName;
    private Environment environment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environments);
        getComponents();
        clearEnvironment();
        addClear();
        addEnvSend();
        addRecyclerView();
        listEnvironments();
    }

    private void addEnvSend() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEnvironmentFromUI();
                if(environment.getId() == null) {
                    // Add operation
                    Call<Environment> environmentCall = apiContext.createEnvironment(environment);
                    environmentCall.enqueue(new Callback<Environment>() {
                        @Override
                        public void onResponse(Call<Environment> call, Response<Environment> response) {
                            if(response.isSuccessful()) {
                                listEnvironments();
                                clearEnvironment();
                            } else if(response.code() == 422) {
                                Toast.makeText(EnvironmentsActivity.this, R.string.message_validation_error, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(EnvironmentsActivity.this, "Error on Request: " + response.code(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Environment> call, Throwable t) {
                            Toast.makeText(EnvironmentsActivity.this, R.string.error_server_contact, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    // Update operation
                    if(environment == null || environment.getId() == null) {
                        Toast.makeText(EnvironmentsActivity.this, R.string.message_validation_error, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Call<Environment> environmentCall = apiContext.updateEnvironment(environment.getId(), environment);
                    environmentCall.enqueue(new Callback<Environment>() {
                        @Override
                        public void onResponse(Call<Environment> call, Response<Environment> response) {
                            if(response.isSuccessful()) {
                                listEnvironments();
                                clearEnvironment();
                            } else if(response.code() == 422) {
                                Toast.makeText(EnvironmentsActivity.this, R.string.message_validation_error, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(EnvironmentsActivity.this, "Error on Request: " + response.code(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Environment> call, Throwable t) {
                            Toast.makeText(EnvironmentsActivity.this, R.string.error_server_contact, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void addClear() {
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearEnvironment();
                btnSend.setText(R.string.action_add);
            }
        });
    }

    private Environment updateEnvironmentFromUI() {
        if(environment == null)
            return null;
        environment.setName(tilEnvName.getEditText().getText().toString());
        return environment;
    }

    private void clearEnvironment() {
        environment = new Environment();
        // UI
        tilEnvName.getEditText().setText("");
        btnSend.setText(R.string.action_add);
    }

    private void fillEnvironment(Environment environment) {
        this.environment = environment;
        // UI
        tilEnvName.getEditText().setText(environment.getName());
        btnSend.setText(R.string.action_update);
    }

    private void addRecyclerView() {
        // Configuring variables
        LinearLayoutManager llm = new LinearLayoutManager(this);
        environmentList = new ArrayList<>();
        environmentAdapter = new EnvironmentAdapter(environmentList, getApplicationContext());
        rvEnvironments.setLayoutManager(llm);
        rvEnvironments.setAdapter(environmentAdapter);
        rvEnvironments.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View view = rv.findChildViewUnder(e.getX(), e.getY());
                int index = rv.getChildLayoutPosition(view);
                if(index == -1)
                    return false;
                Environment environment = environmentAdapter.getEnvironmentList().get(index);
                fillEnvironment(environment);
                return true;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                // Nothing here
            }
        });
        listEnvironments();
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
                        environmentList.clear();
                        sstorage.mobile.senai.com.sstorage.model.Environment[] envArray = response.body();
                        for(sstorage.mobile.senai.com.sstorage.model.Environment env : envArray) {
                            environmentList.add(env);
                        }
                        environmentAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<sstorage.mobile.senai.com.sstorage.model.Environment[]> call, Throwable t) {
                    Toast.makeText(EnvironmentsActivity.this, getString(R.string.error_server_contact), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void getComponents() {
        btnSend = findViewById(R.id.btnSend);
        btnClear = findViewById(R.id.btnClear);
        tilEnvName = findViewById(R.id.tilEnvName);
        rvEnvironments = findViewById(R.id.rvEnvironments);
        // Getting API Context using Token
        sharedPreferences = getSharedPreferences(AppUtils.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(AppUtils.SP_TOKEN, null);
        apiContext = new RetrofitConfig().getApiContext();
    }

}
