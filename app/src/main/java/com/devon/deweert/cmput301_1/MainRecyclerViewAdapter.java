package com.devon.deweert.cmput301_1;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.devon.deweert.cmput301_1.MyHealthStats.HEALTH_STAT_ID_EXTRA;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolder> {

    private ArrayList<MyHealthStats> mainDataset = new ArrayList<MyHealthStats>();

    private RecyclerViewClickListener listener = new RecyclerViewClickListener() {
        @Override
        public void onClick(View view, int position) {
            //Do stuff

        }
    };

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
    public MainRecyclerViewAdapter() {

    }

    // Create new views (invoked by the layout manager)
    @Override
    public MainRecyclerViewAdapter.MainViewHolder onCreateViewHolder(final ViewGroup parent,
                                                                     int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);

        MainViewHolder vh = new MainViewHolder(v, listener);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MainViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        ((TextView) holder.mainTextView.findViewById(R.id.Recycler_View_Systolic_Pressure)).setText(mainDataset.get(position).getSystolicPressure().toString());
        ((TextView) holder.mainTextView.findViewById(R.id.Recycler_View_Date_of_Measurement)).setText(mainDataset.get(position).getMyDateFormat());
        ((TextView) holder.mainTextView.findViewById(R.id.Recycler_View_Diastolic_Pressure)).setText(mainDataset.get(position).getDiastolicPressure().toString());
        ((TextView) holder.mainTextView.findViewById(R.id.Recycler_View_HeartRate)).setText(mainDataset.get(position).getHeartRate().toString());

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
    }

    public void addItem(MyHealthStats newItem){
        mainDataset.add(0, newItem);
        this.notifyDataSetChanged();
    }

    public void addItems(ArrayList<MyHealthStats> newItems){
        mainDataset.addAll(0, newItems);
        this.notifyDataSetChanged();
    }

    public void updateItems(){
        this.notifyDataSetChanged();
    }
}

