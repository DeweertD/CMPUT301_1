package com.devon.deweert.deweert_CardioBook;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolder> {

    private ArrayList<MyHealthStats> mainDataset;

    private RecyclerViewClickListener myListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MainViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        // each data item is just a string in this case
        public LinearLayout mainTextView;
        private RecyclerViewClickListener myListener;


        MainViewHolder(LinearLayout v, RecyclerViewClickListener listener) {
            super(v);
            myListener = listener;
            mainTextView = v;

            mainTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            myListener.onClick(v, getAdapterPosition());
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MainRecyclerViewAdapter(RecyclerViewClickListener listener) {
        myListener = listener;
        mainDataset  = new ArrayList<MyHealthStats>();
    }


    // Create new views (invoked by the layout manager)
    @Override
    public MainRecyclerViewAdapter.MainViewHolder onCreateViewHolder(final ViewGroup parent,
                                                                     int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);

        MainViewHolder vh = new MainViewHolder(v, myListener);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MainViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        TextView systolicView = (TextView) holder.mainTextView.findViewById(R.id.Recycler_View_Systolic_Pressure);
        TextView dateView = (TextView) holder.mainTextView.findViewById(R.id.Recycler_View_Date_of_Measurement);
        TextView diastolicView = (TextView) holder.mainTextView.findViewById(R.id.Recycler_View_Diastolic_Pressure);
        TextView heartView = (TextView) holder.mainTextView.findViewById(R.id.Recycler_View_HeartRate);

        Integer systolicPressure = mainDataset.get(position).getSystolicPressure();
        Integer diastolicPressure = mainDataset.get(position).getDiastolicPressure();
        Integer heartRate = mainDataset.get(position).getHeartRate();

        (systolicView).setText(String.valueOf(systolicPressure));
        (dateView).setText(mainDataset.get(position).getDateMeasured());
        (diastolicView).setText(String.valueOf(diastolicPressure));
        (heartView).setText(String.valueOf(heartRate));

        ((ImageView) holder.mainTextView.findViewById(R.id.Recycler_View_Image)).setImageResource(R.drawable.greencheck);

        if((systolicPressure.compareTo(140) > 0) || (systolicPressure.compareTo(90) < 0 ) ||
                (diastolicPressure.compareTo(90) > 0) || (diastolicPressure.compareTo(60) < 0)){
            ((ImageView) holder.mainTextView.findViewById(R.id.Recycler_View_Image)).setImageResource(R.drawable.redx);
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mainDataset.size();
    }

    public void deleteItem(int position){
        mainDataset.remove(position);
        this.notifyItemRemoved(position);    //notifies the RecyclerView Adapter that data in adapter has been removed at a particular position.
        this.notifyItemRangeChanged(position, this.getItemCount());
        updateItems();
    }

    public void addItem(MyHealthStats newItem){
        mainDataset.add( newItem);
        updateItems();
    }

    //depreciated...
    public void addItems(ArrayList<MyHealthStats> newItems){
        mainDataset.addAll( newItems);
        updateItems();
    }

    private void updateItems(){
        this.notifyDataSetChanged();
    }

    public MyHealthStats getItem(int position){
        return mainDataset.get(position);
    }

    public void replaceItem(int position, MyHealthStats newItem){
        deleteItem(position);
        mainDataset.add(position, newItem);
        updateItems();
    }

    public ArrayList<MyHealthStats> getMainDataset(){
        return mainDataset;
    }


}

