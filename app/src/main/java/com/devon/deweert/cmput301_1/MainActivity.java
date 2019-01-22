package com.devon.deweert.cmput301_1;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

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



        // specify an adapter (see also next example)
        mainRecycleViewAdapter = new MainRecyclerViewAdapter();
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
        Intent addRouteIntent = new Intent(this, AddHealthStats.class);
        startActivity(addRouteIntent);
    }

    public void startHealthStatsViewer(){
        Intent addRouteIntent = new Intent(this, ViewHealthStats.class);
        startActivity(addRouteIntent);
    }
}
