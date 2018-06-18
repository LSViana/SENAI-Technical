package sstorage.mobile.senai.com.sstorage.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sstorage.mobile.senai.com.sstorage.R;
import sstorage.mobile.senai.com.sstorage.config.RetrofitConfig;
import sstorage.mobile.senai.com.sstorage.data.ApiContext;
import sstorage.mobile.senai.com.sstorage.model.Environment;
import sstorage.mobile.senai.com.sstorage.model.Movement;
import sstorage.mobile.senai.com.sstorage.utils.AppUtils;

public class MovePatrimonyItemActivity extends AppCompatActivity {

    private Long patrimonyItemId;
    private Long environmentId;
    private SharedPreferences sharedPreferences;
    private ApiContext apiContext;
    private Spinner spDestinyEnvironments;
    private Button btnMove;
    private Environment[] environments;
    private Environment currentEnvironment = null;
    private List<String> environmentNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_patrimony_item);
        //
        getComponents();
        getPatrimonyItemData();
        addMove();
    }

    private void addMove() {
        // Changing the Current Environment when User Selects
        spDestinyEnvironments.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String envName = environmentNames.get(position);
                for (Environment env : environments) {
                    if(env.getName().equalsIgnoreCase(envName)) {
                        currentEnvironment = env;
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // Button Callback
        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentEnvironment != null) {
                    Call<Movement> call = apiContext.movePatrimonyItem(patrimonyItemId, currentEnvironment.getId());
                    call.enqueue(new Callback<Movement>() {
                        @Override
                        public void onResponse(Call<Movement> call, Response<Movement> response) {
                            Toast.makeText(MovePatrimonyItemActivity.this, String.format(getString(R.string.message_item_movedto), currentEnvironment.getName()), Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Movement> call, Throwable t) {
                            Toast.makeText(MovePatrimonyItemActivity.this, R.string.couldnt_perform_action, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(MovePatrimonyItemActivity.this, R.string.message_must_select_environment, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //
        fillSpinnerWithDestinies();
    }

    private void fillSpinnerWithDestinies() {
        Call<Environment[]> call = apiContext.getEnvironments();
        call.enqueue(new Callback<Environment[]>() {
            @Override
            public void onResponse(Call<Environment[]> call, Response<Environment[]> response) {
                if(response.isSuccessful()) {
                    environments = response.body();
                    environmentNames = new ArrayList<>();
                    for(Environment environment : environments)
                        if(environment.getId() != environmentId)
                            environmentNames.add(environment.getName());
                    // Array Adapter to put stuff at Spinner
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MovePatrimonyItemActivity.this, android.R.layout.simple_spinner_dropdown_item, environmentNames);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spDestinyEnvironments.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onFailure(Call <Environment[]> call, Throwable t) {
                Toast.makeText(MovePatrimonyItemActivity.this, getString(R.string.error_server_contact), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPatrimonyItemData() {
        Intent intent = getIntent();
        //
        patrimonyItemId = intent.getLongExtra(AppUtils.INT_PATITEMID, -1);
        environmentId = intent.getLongExtra(AppUtils.INT_ENVID, -1);
    }

    public void getComponents() {
        sharedPreferences = getSharedPreferences(AppUtils.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(AppUtils.SP_TOKEN, null);
        apiContext = new RetrofitConfig(token).getApiContext();
        // UI
        spDestinyEnvironments = findViewById(R.id.spDestinyEnvironments);
        btnMove = findViewById(R.id.btnMove);
    }
}
