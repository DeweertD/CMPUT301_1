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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MainRecyclerViewAdapter mainRecycleViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Local Variables for activity creation, functionality, and data storage
        ArrayList<MyHealthStats> myDataSet = loadFromFile();

        ItemTouchHelper itemTouchhelper;
        SwipeController swipeController;
        FloatingActionButton addHealthStatsButton;
        RecyclerView.LayoutManager mainLayoutManager;
        RecyclerView mainRecyclerView;


        //Setting up the main page recyclerView using findViewById
        mainRecyclerView = (RecyclerView) findViewById(R.id.MainRecyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mainRecyclerView.setHasFixedSize(true);

        // use a linear layout manager because the list is easy to display in
        //linear fashion
        mainLayoutManager = new LinearLayoutManager(this);
        mainRecyclerView.setLayoutManager(mainLayoutManager);

        //create a click listener that calls back to here, allows us to
        // create new activities from 'THIS' context without passing 'THIS'
        //into the recyclerView directly.
        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                MyHealthStats clickedStats = mainRecycleViewAdapter.getItem(position);
                startHealthStatsViewer(position, clickedStats);

            }
        };

        //create the adapter to manage the data and the recyclerView,
        //give it the above listener
        mainRecycleViewAdapter = new MainRecyclerViewAdapter(listener);
        mainRecyclerView.setAdapter(mainRecycleViewAdapter);

        //interactivity helpers (touch for edit, swipe for delete)
        swipeController = new SwipeController(mainRecycleViewAdapter);
        itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(mainRecyclerView);

        //Add a button click listener so the user can track new data
        addHealthStatsButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        addHealthStatsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startHealthStatsAdder();
            }
        });

        //If we got any data from file, add it to the
        //(now finished with setup) recyclerViewAdapter
        mainRecycleViewAdapter.addItems(myDataSet);
    }

    //Start a new activity to add health stats
    //Called by pressing the floating button on the main activity page
    public void startHealthStatsAdder(){
        Intent addHealthStatsIntent = new Intent(this, AddHealthStats.class);
        startActivityForResult(addHealthStatsIntent, MyHealthStats.ADD_KEY);
    }

    //Start a new activity to view and edit previous health stats
    //Called from a touch activity on the recyclerView list of stats
    public void startHealthStatsViewer(int position, MyHealthStats dataPack){
        Intent viewHealthStatsIntent = new Intent(this, ViewHealthStats.class);
        String myHealthDataAsJson = new Gson().toJson(dataPack);
        viewHealthStatsIntent.putExtra(MyHealthStats.HEALTH_DATA,  myHealthDataAsJson);
        viewHealthStatsIntent.putExtra(MyHealthStats.INT_DATA, position);
        startActivityForResult(viewHealthStatsIntent, MyHealthStats.VIEW_KEY);
    }

    //Whenever one of the other activities returns we check to see if good
    //data was entered and then add the good data to the adapter data list
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(resultCode == RESULT_OK) {
            String myHealthDataAsJson = intent.getStringExtra(MyHealthStats.HEALTH_DATA);

            if (requestCode == MyHealthStats.ADD_KEY) {
                Type type = new TypeToken<MyHealthStats>() {}.getType();
                MyHealthStats newHealthStats = new Gson().fromJson(myHealthDataAsJson, type);
                mainRecycleViewAdapter.addItem(newHealthStats);
            } else if (requestCode == MyHealthStats.VIEW_KEY) {
                Type type = new TypeToken<MyHealthStats>() {}.getType();
                MyHealthStats editedItem = new Gson().fromJson(myHealthDataAsJson, type);
                int position = intent.getIntExtra(MyHealthStats.INT_DATA, 0);
                mainRecycleViewAdapter.replaceItem(position, editedItem);
            }
        }
    }

    private ArrayList<MyHealthStats> loadFromFile() {
        ArrayList<MyHealthStats> myDataSet = new ArrayList<MyHealthStats>();

        try {
            FileReader in = new FileReader(new File(getFilesDir(), MyHealthStats.FILENAME));
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<MyHealthStats>>(){}.getType();
            myDataSet = gson.fromJson(in, type);
            in.close();
        } catch (FileNotFoundException e) {
            // TO DO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TO DO Auto-generated catch block
            e.printStackTrace();
        }
        return myDataSet;
    }

    private void saveInFile() {
        ArrayList<MyHealthStats> myDataset = mainRecycleViewAdapter.getMainDataSet();

        try {
            FileWriter out = new FileWriter(new File(getFilesDir(), MyHealthStats.FILENAME));
            Gson gson = new Gson();
            gson.toJson(myDataset, out);
            out.close();
        } catch (FileNotFoundException e) {
            // TO DO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TO DO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    public void onStop(){
        saveInFile();
        super.onStop();
    }
}
