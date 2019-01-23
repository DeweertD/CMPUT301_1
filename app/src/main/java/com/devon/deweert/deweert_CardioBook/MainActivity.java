package com.devon.deweert.deweert_CardioBook;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mainRecyclerView;
    private MainRecyclerViewAdapter mainRecycleViewAdapter;
    private RecyclerView.LayoutManager mainLayoutManager;
    private ArrayList<MyHealthStats> myDataset = new ArrayList<MyHealthStats>();

    private FloatingActionButton addRouteButton;
    private SwipeController swipeController;
    private ItemTouchHelper itemTouchhelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainRecyclerView = (RecyclerView) findViewById(R.id.MainRecyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mainRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mainLayoutManager = new LinearLayoutManager(this);
        mainRecyclerView.setLayoutManager(mainLayoutManager);

        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                MyHealthStats clickedStats = mainRecycleViewAdapter.getItem(position);
                startHealthStatsViewer(position, clickedStats);
//                Toast toast = Toast.makeText(getBaseContext(), ((Integer)position).toString(), Toast.LENGTH_LONG);
//                toast.show();
            }
        };

        // specify an adapter (see also next example)
        mainRecycleViewAdapter = new MainRecyclerViewAdapter(listener);
        mainRecyclerView.setAdapter(mainRecycleViewAdapter);

        swipeController = new SwipeController(mainRecycleViewAdapter);
        itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(mainRecyclerView);

        addRouteButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        addRouteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startHealthStatsAdder();
            }
        });
    }

    public void startHealthStatsAdder(){
        Intent addHealthStatsIntent = new Intent(this, AddHealthStats.class);
        startActivityForResult(addHealthStatsIntent, MyHealthStats.ADD_KEY);
    }

    public void startHealthStatsViewer(int position, MyHealthStats dataPack){
        Intent viewHealthStatsIntent = new Intent(this, ViewHealthStats.class);
        String myHealthDataAsJson = new Gson().toJson(dataPack);
        viewHealthStatsIntent.putExtra(MyHealthStats.HEALTH_DATA,  myHealthDataAsJson);
        viewHealthStatsIntent.putExtra(MyHealthStats.INT_DATA, position);
        startActivityForResult(viewHealthStatsIntent, MyHealthStats.VIEW_KEY);
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(resultCode == RESULT_OK) {
            String myHealthDataAsJson = intent.getStringExtra(MyHealthStats.HEALTH_DATA);

            if (requestCode == MyHealthStats.ADD_KEY) {
                Type type = new TypeToken<ArrayList<MyHealthStats>>() {
                }.getType();
                ArrayList<MyHealthStats> newHealthStats = new Gson().fromJson(myHealthDataAsJson, type);
                mainRecycleViewAdapter.addItems(newHealthStats);
            } else if (requestCode == MyHealthStats.VIEW_KEY) {
                Type type = new TypeToken<MyHealthStats>() {}.getType();
                MyHealthStats editedItem = new Gson().fromJson(myHealthDataAsJson, type);
                int position = intent.getIntExtra(MyHealthStats.INT_DATA, 0);
                mainRecycleViewAdapter.replaceItem(position, editedItem);
            }
        }
    }
}
