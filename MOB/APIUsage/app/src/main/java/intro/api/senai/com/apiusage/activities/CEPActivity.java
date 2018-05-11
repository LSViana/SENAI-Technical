package intro.api.senai.com.apiusage.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import intro.api.senai.com.apiusage.R;
import intro.api.senai.com.apiusage.models.CEP;
import intro.api.senai.com.apiusage.retrofit.config.RetrofitConfig;
import intro.api.senai.com.apiusage.utils.AppUtils;
import intro.api.senai.com.apiusage.utils.CEPRestService;
import intro.api.senai.com.apiusage.utils.HttpService;

public class CEPActivity extends AppCompatActivity {

    private TextView tvResult;
    private EditText etCEP;
    private Button btnQuery;
    private HttpService httpService;
    private RetrofitConfig retroFitConfig;
    private CEPRestService cepRestService;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        // Starting fields
        retroFitConfig = new RetrofitConfig();
        cepRestService = retroFitConfig.getCEPRest();

        // Getting Components
        etCEP = findViewById(R.id.etCEP);
        btnQuery = findViewById(R.id.btnQuery);
        tvResult = findViewById(R.id.tvResult);
        // Adding Event Handlers
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CEP cepFromAPI = httpService.getObject(AppUtils.API_CEP_URL + "/" + etCEP.getText(), CEP.class);
                if(cepFromAPI == null) {
                    Toast.makeText(CEPActivity.this, R.string.cepNotFound, Toast.LENGTH_SHORT).show();
                } else {
                    tvResult.setText(cepFromAPI.toString());
                }
            }
        });
    }
}
