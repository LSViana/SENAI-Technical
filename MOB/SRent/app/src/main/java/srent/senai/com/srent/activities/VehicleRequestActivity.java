package srent.senai.com.srent.activities;

import android.app.DatePickerDialog;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import srent.senai.com.srent.R;
import srent.senai.com.srent.data.BusDAO;
import srent.senai.com.srent.data.DAO;
import srent.senai.com.srent.data.VanDAO;
import srent.senai.com.srent.data.VehicleRequestDAO;
import srent.senai.com.srent.models.Vehicle;
import srent.senai.com.srent.models.VehicleRequest;
import srent.senai.com.srent.models.VehicleType;

public class VehicleRequestActivity extends AppCompatActivity {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private VehicleType vehicleType;
    private Long vehicleId;
    private ImageView imageView;
    private TextView tvName;
    private TextView tvPrice;
    private FloatingActionButton btnStartDate;
    private TextView tvStartDate;
    private FloatingActionButton btnEndDate;
    private TextView tvEndDate;
    private CheckBox cbIncludeDriver;
    private EditText etPassengerAmount;
    private EditText etOriginAddress;
    private EditText etDestinyAddress;
    private Button btnRequestVehicle;
    private VehicleRequest currentVehicleRequest;
    private GregorianCalendar startCalendar = (GregorianCalendar)GregorianCalendar.getInstance();
    private GregorianCalendar endCalendar = (GregorianCalendar)GregorianCalendar.getInstance();
    private Vehicle vehicle;
    private VehicleRequestDAO vehicleRequestDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_request);
        // Main Object
        currentVehicleRequest = new VehicleRequest();
        // Getting Components
        imageView = findViewById(R.id.request_card_ivMain);
        tvName = findViewById(R.id.request_card_tvName);
        tvPrice = findViewById(R.id.request_card_tvPrice);
        tvStartDate = findViewById(R.id.request_card_tvStartDate);
        btnStartDate = findViewById(R.id.request_card_btnStartDate);
        tvEndDate = findViewById(R.id.request_card_tvEndDate);
        btnEndDate = findViewById(R.id.request_card_btnEndDate);
        cbIncludeDriver = findViewById(R.id.request_card_cbIncludeDriver);
        etPassengerAmount = findViewById(R.id.request_card_etPassengerAmount);
        etOriginAddress = findViewById(R.id.request_card_etOriginAddress);
        etDestinyAddress = findViewById(R.id.request_card_etDestinyAddress);
        btnRequestVehicle = findViewById(R.id.request_card_btnRequestVehicle);
        // Standard Values
        vehicleRequestDAO = new VehicleRequestDAO(getBaseContext());
        startCalendar = (GregorianCalendar)GregorianCalendar.getInstance();
        endCalendar = (GregorianCalendar)GregorianCalendar.getInstance();
        setStartDate();
        setEndDate();
        // Setting Listeners
        btnStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dpg = new DatePickerDialog(VehicleRequestActivity.this, R.style.AppDialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        GregorianCalendar startCalendar = new GregorianCalendar(year, month, dayOfMonth);
                        if(startCalendar.after(endCalendar)) {
                            Toast.makeText(VehicleRequestActivity.this, R.string.errorStartDateOutOfBoundsMessage, Toast.LENGTH_SHORT).show();
                        } else {
                            VehicleRequestActivity.this.startCalendar = startCalendar;
                            setStartDate();
                        }
                    }
                }, startCalendar.get(Calendar.YEAR), startCalendar.get(Calendar.MONTH), startCalendar.get(Calendar.DAY_OF_MONTH));
                dpg.show();
            }
        });
        btnEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dpg = new DatePickerDialog(VehicleRequestActivity.this, R.style.AppDialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        GregorianCalendar endCalendar = new GregorianCalendar(year, month, dayOfMonth);
                        if(endCalendar.before(startCalendar)) {
                            Toast.makeText(VehicleRequestActivity.this, R.string.errorEndDateOutOfBoundsMessage, Toast.LENGTH_SHORT).show();
                        } else {
                            VehicleRequestActivity.this.endCalendar = endCalendar;
                            setEndDate();
                        }
                    }
                }, endCalendar.get(Calendar.YEAR), endCalendar.get(Calendar.MONTH), endCalendar.get(Calendar.DAY_OF_MONTH));
                dpg.show();
            }
        });
        btnRequestVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get All Data
                String originAddress = etOriginAddress.getText().toString();
                String destinyAddress = etDestinyAddress.getText().toString();
                String passengerAmountStr = etPassengerAmount.getText().toString();
                Boolean includeDriver = cbIncludeDriver.isChecked();
                // Validate
                if(passengerAmountStr.length() == 0) {
                    Toast.makeText(VehicleRequestActivity.this, "You must insert a value to Passenger Amount", Toast.LENGTH_SHORT).show();
                    return;
                }
                Integer passengerAmount = Integer.parseInt(etPassengerAmount.getText().toString());
                // Set All Data
                currentVehicleRequest.setVehicleType(vehicleType);
                currentVehicleRequest.setVehicle(vehicle);
                currentVehicleRequest.setStartDate(startCalendar.getTime());
                currentVehicleRequest.setEndDate(endCalendar.getTime());
                currentVehicleRequest.setPassengerAmount(passengerAmount);
                currentVehicleRequest.setOriginAddress(originAddress);
                currentVehicleRequest.setDestinyAddress(destinyAddress);
                currentVehicleRequest.setIncludesDriver(includeDriver);
                // Save
                vehicleRequestDAO.insert(currentVehicleRequest);
                // User Feedback
                Toast.makeText(VehicleRequestActivity.this, String.format(getString(R.string.requestSuccessText), currentVehicleRequest.calculatePrice()), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        // Getting Vehicle Data
        vehicleType = VehicleType.valueOf(getIntent().getStringExtra(VehiclesActivity.VEHICLE_TYPE_CODE));
        vehicleId = getIntent().getLongExtra(VehiclesActivity.VEHICLE_ID_CODE, -1);
        if(vehicleId == -1) {
            Toast.makeText(this, R.string.error_01_novehiclefound, Toast.LENGTH_SHORT).show();
            finish();
        }
        // Creating DAO and querying for the vehicle data
        switch (vehicleType) {
            case BUS:
                loadVehicle(new BusDAO(getBaseContext()));
                break;
            case VAN:
                loadVehicle(new VanDAO(getBaseContext()));
                break;
        }
    }

    private void setEndDate() {
        tvEndDate.setText(sdf.format(endCalendar.getTime()));
        currentVehicleRequest.setEndDate(endCalendar.getTime());
    }

    private void setStartDate() {
        tvStartDate.setText(sdf.format(startCalendar.getTime()));
        currentVehicleRequest.setStartDate(startCalendar.getTime());
    }

    private void loadVehicle(DAO dao) {
        vehicle = (Vehicle) dao.search(vehicleId);
        //
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), vehicle.getImageResId()));
        tvName.setText(String.format("%s: %s", vehicle.getName(), vehicle.getDescription()));
        tvPrice.setText(String.format("US$ %.02f", vehicle.getPrice()));
    }
}
