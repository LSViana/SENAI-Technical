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
import intro.api.senai.com.apiusage.models.CNPJ;
import intro.api.senai.com.apiusage.utils.AppUtils;
import intro.api.senai.com.apiusage.utils.HttpService;

public class CNPJActivity extends AppCompatActivity {

    private HttpService httpService;
    private EditText etCNPJ;
    private Button btnQuery;
    private TextView tvCNPJResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cnpj);
        // Starting fields
        httpService = new HttpService();
        // Getting Components
        etCNPJ = findViewById(R.id.etCNPJ);
        btnQuery = findViewById(R.id.btnQuery);
        tvCNPJResult = findViewById(R.id.tvCNPJResult);
        // Adding Event Handlers
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CNPJ cnpjFromAPI = httpService.getObject(AppUtils.API_CNPJ_URL + "/" + etCNPJ.getText(), CNPJ.class);
                if(cnpjFromAPI == null) {
                    Toast.makeText(CNPJActivity.this, R.string.cnpjNotFound, Toast.LENGTH_SHORT).show();
                } else {
                    tvCNPJResult.setText(cnpjFromAPI.toString());
                }
            }
        });
    }
}
