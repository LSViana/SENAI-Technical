package srent.senai.com.srent.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import srent.senai.com.srent.R;
import srent.senai.com.srent.adapters.VehicleRequestViewAdapter;
import srent.senai.com.srent.data.VehicleRequestDAO;
import srent.senai.com.srent.models.VehicleRequest;

public class VehicleRequestListActivity extends AppCompatActivity {

    private VehicleRequestOnClickListener vehicleRequestOnClickListener = new VehicleRequestOnClickListener();
    private VehicleRequestDAO vehicleRequestDAO;
    private RecyclerView rvVehicleRequests;
    private VehicleRequestViewAdapter vehicleRequestAdapter;
    private List<VehicleRequest> currentVehicleRequestList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_request_list);
        // DAO
        vehicleRequestDAO = new VehicleRequestDAO(this);
        //
        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        // Getting RecyclerView objects
        rvVehicleRequests = findViewById(R.id.rvVehiclesRequests);
        // Adding Layout Manager
        rvVehicleRequests.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        // Using a custom adapter
        vehicleRequestAdapter = new VehicleRequestViewAdapter(vehicleRequestOnClickListener , vehicleRequestDAO.searchAll());
        // Setting Adapter to RecyclerView
        rvVehicleRequests.setAdapter(vehicleRequestAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //
        if(vehicleRequestAdapter == null) {
            vehicleRequestAdapter = new VehicleRequestViewAdapter(vehicleRequestOnClickListener, vehicleRequestDAO.searchAll());
        }
        //
        currentVehicleRequestList = vehicleRequestDAO.searchAll();
        vehicleRequestAdapter.setVehicleRequests(currentVehicleRequestList);
        //
        vehicleRequestAdapter.notifyDataSetChanged();
    }

    public class VehicleRequestOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            // Nothing here
        }
    }
}
