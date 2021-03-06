package com.devon.deweert.deweert_CardioBook;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;

import static android.support.v7.widget.helper.ItemTouchHelper.*;


//Swipe controller made with help from
//https://codeburst.io/android-swipe-menu-with-recyclerview-8f28a235ff28
public class SwipeController extends Callback {
    private MainRecyclerViewAdapter myAdapter;

    public SwipeController(MainRecyclerViewAdapter mainRecyclerViewAdapter){
        super();
        myAdapter = mainRecyclerViewAdapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //myAdapter.updateItems();
        return makeMovementFlags(0, LEFT | RIGHT);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //myAdapter.updateItems();
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Integer position = viewHolder.getAdapterPosition();
        //Log.d("SwipeController", position.toString());
        myAdapter.deleteItem(position);

    }
}
