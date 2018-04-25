package srent.senai.com.srent.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import srent.senai.com.srent.R;
import srent.senai.com.srent.adapters.BusViewAdapter;
import srent.senai.com.srent.adapters.VanViewAdapter;
import srent.senai.com.srent.data.BusDAO;
import srent.senai.com.srent.data.VanDAO;
import srent.senai.com.srent.models.Bus;
import srent.senai.com.srent.models.Van;

public class VehiclesActivity extends AppCompatActivity {

    private RecyclerView rvBus;
    private RecyclerView rvVan;
    private LinearLayoutManager lmBus;
    private LinearLayoutManager lmVan;
    private BusViewAdapter busAdapter;
    private VanViewAdapter vanAdapter;
    private BusDAO busDAO;
    private VanDAO vanDAO;
    private VanOnClickListener vanListener;
    private BusOnClickListener busListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles);
        // DAO
        busDAO = new BusDAO(this);
        vanDAO = new VanDAO(this);
        //
        //pretendDataInsertion();
        //
        initializeRecyclerView();
    }

    private void pretendDataInsertion() {
        // Bus
        busDAO.insert(new Bus(1l, "Blue Bus", "Air-conditioning and three lateral doors", R.drawable.blue_bus, 239.99));
        busDAO.insert(new Bus(2l, "Dark Red Bus", "Air-conditioning, three parts, four doors at each side and extra-long body", R.drawable.darkred_bus, 499.99));
        busDAO.insert(new Bus(3l, "Green Bus", "Three and two doors scheme and long body", R.drawable.green_bus, 329.99));
        busDAO.insert(new Bus(4l, "Orange Bus", "Only two right-side doors and medium body", R.drawable.orange_bus, 239.99));
        busDAO.insert(new Bus(5l, "Silver Bus", "Experimental electric vehicle, only three right-side doors, no pollution and long body", R.drawable.silver_bus, 699.99));
        // Van
        vanDAO.insert(new Van(1l, "Blue Van", "Air-conditioning and two lateral doors", R.drawable.blue_van, 219.99));
        vanDAO.insert(new Van(2l, "Orange Van", "Air-conditioning and two lateral doors", R.drawable.orange_van, 219.99));
        vanDAO.insert(new Van(3l, "Red Van", "Air-conditioning and two lateral doors", R.drawable.red_van, 219.99));
        vanDAO.insert(new Van(4l, "White Van", "Air-conditioning, never used before and two lateral doors", R.drawable.white_van, 299.99));
    }

    private void initializeRecyclerView() {
        // OnClick Listeners
        vanListener = new VanOnClickListener();
        busListener = new BusOnClickListener();
        // Getting Components
        rvBus = findViewById(R.id.rvBus);
        rvVan = findViewById(R.id.rvVan);
        // Configuration RecyclerView
        rvBus.setHasFixedSize(true);
        rvVan.setHasFixedSize(true);
        // Using a Linear Layout Manager
        lmBus = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        lmVan = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvBus.setLayoutManager(lmBus);
        rvVan.setLayoutManager(lmVan);
        // Using a custom Adapter
        busAdapter = new BusViewAdapter(vanListener, busDAO.searchAll(), false);
        vanAdapter = new VanViewAdapter(busListener, vanDAO.searchAll(), false);
        // Setting Adapter to RecyclerView
        rvBus.setAdapter(busAdapter);
        rvVan.setAdapter(vanAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //
        if(busAdapter == null || vanAdapter == null) {
            busAdapter = new BusViewAdapter(vanListener, busDAO.searchAll(), false);
            vanAdapter = new VanViewAdapter(busListener, vanDAO.searchAll(), false);
        }
        busAdapter.setVehicles(busDAO.searchAll());
        vanAdapter.setVehicles(vanDAO.searchAll());
        //
        busAdapter.notifyDataSetChanged();
        vanAdapter.notifyDataSetChanged();
    }

    public class BusOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

        }
    }

    public class VanOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

        }
    }
}
