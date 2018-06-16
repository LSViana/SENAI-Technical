package sstorage.mobile.senai.com.sstorage.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sstorage.mobile.senai.com.sstorage.R;
import sstorage.mobile.senai.com.sstorage.config.RetrofitConfig;
import sstorage.mobile.senai.com.sstorage.data.ApiContext;
import sstorage.mobile.senai.com.sstorage.model.UserType;
import sstorage.mobile.senai.com.sstorage.model.ViewModel.Authentication;
import sstorage.mobile.senai.com.sstorage.model.ViewModel.Login;
import sstorage.mobile.senai.com.sstorage.utils.AppUtils;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout tilEmail;
    private TextInputLayout tilPassword;
    private Button btnLogin;
    private ApiContext apiContext;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        useRetrofit();
        getComponents();
        addLogin();
    }

    private void useRetrofit() {
        apiContext = new RetrofitConfig().getApiContext();
    }

    private void addLogin() {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        String token = sharedPreferences.getString(AppUtils.SP_TOKEN, null);
        if(token != null) {
            // Already logged in
            openMainActivity();
        }
        tilEmail.getEditText().setText("admin@email.com");
        tilPassword.getEditText().setText("admin1");
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Getting values from UI
                String email = tilEmail.getEditText().getText().toString();
                String password = tilPassword.getEditText().getText().toString();
                // Creating the Login object to send at the body
                Login login = new Login();
                login.setEmail(email);
                login.setPassword(password);
                //
                Call<Authentication> call = apiContext.requestToken(login);
                call.enqueue(new Callback<Authentication>() {
                    @Override
                    public void onResponse(Call<Authentication> call, Response<Authentication> response) {
                        if(response.isSuccessful()) {
                            Authentication auth = response.body();
                            // Saving stuff at Authentication
                            AppUtils.login(editor, auth);
                            // Starting the main screen
                            openMainActivity();
                        } else if(response.code() == 404) {
                            Toast.makeText(LoginActivity.this, getString(R.string.error_invalid_credentials), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, R.string.message_fallback_login, Toast.LENGTH_SHORT).show();
                            AppUtils.logout(editor);
                        }
                    }

                    @Override
                    public void onFailure(Call<Authentication> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, getString(R.string.error_server_contact), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void openMainActivity() {
        Integer userTypeInt = sharedPreferences.getInt(AppUtils.SP_USERTYPE, -1);
        if(userTypeInt == -1) {
            Toast.makeText(this, R.string.message_reinstall, Toast.LENGTH_SHORT).show();
            return;
        }
        UserType userType = UserType.values()[userTypeInt];
        if(userType == UserType.ADMINISTRATOR) {
            Intent intent = new Intent(LoginActivity.this, MainAdminActivity.class);
            startActivity(intent);
        }
        else {
            // Start Intent to Regular Users
        }
        finish();
    }

    private void getComponents() {
        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        btnLogin = findViewById(R.id.btnLogin);
        sharedPreferences = getSharedPreferences(AppUtils.SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }
}
