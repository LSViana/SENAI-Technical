package sstorage.mobile.senai.com.sstorage.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sstorage.mobile.senai.com.sstorage.R;
import sstorage.mobile.senai.com.sstorage.config.RetrofitConfig;
import sstorage.mobile.senai.com.sstorage.data.ApiContext;
import sstorage.mobile.senai.com.sstorage.model.Patrimony;
import sstorage.mobile.senai.com.sstorage.utils.AppUtils;
import sstorage.mobile.senai.com.sstorage.view.adapter.PatrimonyAdapter;

public class PatrimoniesActivity extends AppCompatActivity {

    private Boolean listenerSet = false;
    private RecyclerView rvPatrimonies;
    private List<Patrimony> patrimonyList;
    private PatrimonyAdapter patrimonyAdapter;
    private SharedPreferences sharedPreferences;
    private ApiContext apiContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patrimonies);
        //
        getComponents();
        addRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //
        listEnvironments();
        // Restarting Movement Screen Flag
        itemsOpen = false;
    }

    private void listEnvironments() {
        Call<Patrimony[]> call = apiContext.getPatrimonies();
        call.enqueue(new Callback<Patrimony[]>() {
            @Override
            public void onResponse(Call<Patrimony[]> call, Response<Patrimony[]> response) {
                if(response.isSuccessful()) {
                    patrimonyList.clear();
                    Patrimony[] patrimonyArray = response.body();
                    for(Patrimony patrimony : patrimonyArray) {
                        patrimonyList.add(patrimony);
                    }
                    patrimonyAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Patrimony[]> call, Throwable t) {
                Toast.makeText(PatrimoniesActivity.this, getString(R.string.error_server_contact), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Boolean itemsOpen = false;

    private void addRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        patrimonyList = new ArrayList<>();
        patrimonyAdapter = new PatrimonyAdapter(patrimonyList, getApplicationContext());
        // Setting RV
        rvPatrimonies.setLayoutManager(llm);
        rvPatrimonies.setAdapter(patrimonyAdapter);
        // Adding callback to list Patrimony Items when an item is clicked
        rvPatrimonies.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                if(itemsOpen)
                    return true;
                itemsOpen = true;
                View childClicked = rv.findChildViewUnder(e.getX(), e.getY());
                int index = rv.getChildLayoutPosition(childClicked);
                if(index == -1)
                    return true;
                Patrimony patrimony = patrimonyList.get(index);
                // Creating Intent to the other Activity
                Intent patItemIntent = new Intent(PatrimoniesActivity.this, PatrimonyItemsActivity.class);
                // Inserting extras to perform the list operation at other Activity
                patItemIntent.putExtra(AppUtils.INT_PATNAME, patrimony.getName());
                patItemIntent.putExtra(AppUtils.INT_PATID, patrimony.getId());
                startActivity(patItemIntent);
                return true;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    private void getComponents() {
        rvPatrimonies = findViewById(R.id.rvPatrimonies);
        sharedPreferences = getSharedPreferences(AppUtils.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(AppUtils.SP_TOKEN, null);
        apiContext = new RetrofitConfig(token).getApiContext();
    }

}
