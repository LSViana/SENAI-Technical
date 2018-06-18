package sstorage.mobile.senai.com.sstorage.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sstorage.mobile.senai.com.sstorage.R;
import sstorage.mobile.senai.com.sstorage.config.RetrofitConfig;
import sstorage.mobile.senai.com.sstorage.data.ApiContext;
import sstorage.mobile.senai.com.sstorage.model.Patrimony;
import sstorage.mobile.senai.com.sstorage.model.PatrimonyItem;
import sstorage.mobile.senai.com.sstorage.model.PatrimonyItemState;
import sstorage.mobile.senai.com.sstorage.utils.AppUtils;
import sstorage.mobile.senai.com.sstorage.view.adapter.PatrimonyItemAdapter;

public class PatrimonyItemsActivity extends AppCompatActivity {

    private Object patrimonyData;
    private String patrimonyName;
    private Long patrimonyId;
    private SharedPreferences sharedPreferences;
    private ApiContext apiContext;
    private RecyclerView rvPatrimonyItems;
    private ArrayList<PatrimonyItem> patrimonyItemList;
    private PatrimonyItemAdapter patrimonyItemAdapter;
    private Integer activeStateInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patrimony_items);
        //
        getComponents();
        addRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //
        listPatrimonyItems();
        // Reset flag of Movements Screen Open
        moveOpen = false;
    }

    private void listPatrimonyItems() {
        setFilterBy(PatrimonyItemState.ACTIVE);
        Call<PatrimonyItem[]> call = apiContext.getPatrimonyItemsByPatrimony(patrimonyId);
        call.enqueue(new Callback<PatrimonyItem[]>() {
            @Override
            public void onResponse(Call<PatrimonyItem[]> call, Response<PatrimonyItem[]> response) {
                if(response.isSuccessful()) {
                    patrimonyItemList.clear();
                    PatrimonyItem[] patrimonyItemArray = response.body();
                    for(PatrimonyItem patrimonyItem : patrimonyItemArray) {
                        if(patrimonyItem.getState() != activeStateInt)
                            continue;
                        patrimonyItemList.add(patrimonyItem);
                    }
                    patrimonyItemAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PatrimonyItem[]> call, Throwable t) {
                Toast.makeText(PatrimonyItemsActivity.this, getString(R.string.error_server_contact), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setFilterBy(PatrimonyItemState stateFilter) {
        PatrimonyItemState[] states = PatrimonyItemState.values();
        activeStateInt = 0;
        for(PatrimonyItemState state : states)
        {
            if(state == stateFilter)
                break;
            activeStateInt++;
        }
    }

    private Boolean moveOpen = false;

    private void addRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        patrimonyItemList = new ArrayList<>();
        patrimonyItemAdapter = new PatrimonyItemAdapter(patrimonyItemList, getApplicationContext());
        // Setting components to RecyclerView
        rvPatrimonyItems.setLayoutManager(llm);
        rvPatrimonyItems.setAdapter(patrimonyItemAdapter);
        // Adding Listener to Move Item
        rvPatrimonyItems.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                if(moveOpen)
                    return false;
                moveOpen = true;
                //
                View childClicked = rvPatrimonyItems.findChildViewUnder(e.getX(), e.getY());
                int index = rvPatrimonyItems.getChildLayoutPosition(childClicked);
                if(index == -1)
                    return true;
                PatrimonyItem patrimonyItem = patrimonyItemList.get(index);
                Intent intent = new Intent(PatrimonyItemsActivity.this, MovePatrimonyItemActivity.class);
                intent.putExtra(AppUtils.INT_PATITEMID, patrimonyItem.getId());
                intent.putExtra(AppUtils.INT_ENVID, patrimonyItem.getEnvironmentId());
                startActivity(intent);
                //
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

    public void getComponents() {
        // Get Patrimony Data
        Intent intent = getIntent();
        patrimonyName = intent.getStringExtra(AppUtils.INT_PATNAME);
        patrimonyId = intent.getLongExtra(AppUtils.INT_PATID, -1);
        if(patrimonyId == -1) {
            finish();
        }
        // Setting values to the Top Bar
        setTitle(String.format(getString(R.string.activity_patrimony_items_title), patrimonyName));
        // Components from UI
        rvPatrimonyItems = findViewById(R.id.rvPatrimonyItems);
        // Get API Context
        sharedPreferences = getSharedPreferences(AppUtils.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(AppUtils.SP_TOKEN, null);
        apiContext = new RetrofitConfig(token).getApiContext();
    }
}
