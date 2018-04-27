package srent.senai.com.srent.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import srent.senai.com.srent.R;
import srent.senai.com.srent.data.BusDAO;
import srent.senai.com.srent.data.VanDAO;
import srent.senai.com.srent.models.Bus;
import srent.senai.com.srent.models.Van;
import srent.senai.com.srent.models.VehicleType;

public class VehicleRegisterActivity extends AppCompatActivity {

    public static final int IMAGE_SELECT_REQUESTCODE = 1;
    private BusDAO busDAO;
    private VanDAO vanDAO;
    private EditText etName;
    private EditText etDescription;
    private EditText etPrice;
    private EditText etCapacity;
    private Button btnSelectImage;
    private RadioButton rbVan;
    private RadioButton rbBus;
    private Button btnInsertVehicle;
    private Bitmap currentImage;
    private ImageView ivVehiclePreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_register);
        // Data Layer
        busDAO = new BusDAO(this);
        vanDAO = new VanDAO(this);
        // Getting Components
        // Vehicle Type Selector
        rbBus = findViewById(R.id.rbBusVehicle);
        rbVan = findViewById(R.id.rbVanVehicle);
        rbBus.setChecked(true);
        // Others
        etName = findViewById(R.id.register_etName);
        etDescription = findViewById(R.id.register_etDescription);
        etPrice = findViewById(R.id.register_etPrice);
        etCapacity = findViewById(R.id.register_etCapacity);
        btnSelectImage = findViewById(R.id.register_btnSelectImage);
        btnInsertVehicle = findViewById(R.id.register_btnInsertVehicle);
        ivVehiclePreview = findViewById(R.id.ivVehiclePreview);
        // Listeners
        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_GET_CONTENT);
                photoPicker.setType("image/*");
                startActivityForResult(Intent.createChooser(photoPicker, "Select Picture"), IMAGE_SELECT_REQUESTCODE);
            }
        });
        btnInsertVehicle.setOnClickListener(new VehicleInsertClickListener());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Verifying Image Request Code
        if(requestCode == IMAGE_SELECT_REQUESTCODE) {
            if(resultCode == RESULT_OK) {
                Uri imageUri = data.getData();
                try {
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    currentImage = BitmapFactory.decodeStream(is);
                    ivVehiclePreview.setImageBitmap(currentImage);
                    Toast.makeText(this, R.string.imageSuccessfullyAddedText, Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, R.string.error_02_imageuploaderror, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public class VehicleInsertClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            // Get Data
            VehicleType vt = rbBus.isChecked() ? VehicleType.BUS : VehicleType.VAN;
            String name = etName.getText().toString();
            String description = etDescription.getText().toString();
            // Validate
            if(name.length() < 3) {
                Toast.makeText(VehicleRegisterActivity.this, "Insert a valid vehicle name!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(description.length() < 3) {
                Toast.makeText(VehicleRegisterActivity.this, "Insert a valid vehicle description!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(etPrice.getText().toString().length() == 0) {
                Toast.makeText(VehicleRegisterActivity.this, "Insert a valid vehicle price!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(etCapacity.getText().toString().length() == 0) {
                Toast.makeText(VehicleRegisterActivity.this, "Insert a valid vehicle capacity!", Toast.LENGTH_SHORT).show();
                return;
            }
            Double price = Double.parseDouble(etPrice.getText().toString());
            Integer capacity = Integer.parseInt(etCapacity.getText().toString());
            if(currentImage == null) {
                Toast.makeText(VehicleRegisterActivity.this, "Select a valid image to the vehicle!", Toast.LENGTH_SHORT).show();
                return;
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            currentImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageBytes = baos.toByteArray();
            // Save
            switch(vt) {
                case BUS:
                    Bus bus = new Bus(0l, name, description, imageBytes, price, capacity);
                    busDAO.insert(bus);
                    break;
                case VAN:
                    Van van = new Van(0l, name, description, imageBytes, price, capacity);
                    vanDAO.insert(van);
                    break;
            }
            Toast.makeText(VehicleRegisterActivity.this, String.format(getString(R.string.vehicleSuccessfullyInsertedText), name), Toast.LENGTH_SHORT).show();
            finish();
            // User Feedback
        }
    }
}
