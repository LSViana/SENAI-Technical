package sstorage.mobile.senai.com.sstorage.view;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import sstorage.mobile.senai.com.sstorage.R;
import sstorage.mobile.senai.com.sstorage.config.RetrofitConfig;
import sstorage.mobile.senai.com.sstorage.model.ViewModel.Authentication;
import sstorage.mobile.senai.com.sstorage.model.ViewModel.Login;
import sstorage.mobile.senai.com.sstorage.utils.AppUtils;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout tilEmail;
    private TextInputLayout tilPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getComponents();
        addLogin();
    }

    private void addLogin() {
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
            }
        });
    }

    private void getComponents() {
        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }
}
