package com.example.nikolai.eventodense.components.EventView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nikolai.eventodense.R;
import com.example.nikolai.eventodense.models.Event.Event;

import java.util.List;

/**
 * Created by lennartolsen on 29/11/2016.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private static final String TAG = "EVENT_ADAPTER";
    private List<Event> mDataset;
    private RecyclerView mRecyclerView;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class EventViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name, start_time, description;
        public EventViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            start_time = (TextView) v.findViewById(R.id.start_time);
            description = (TextView) v.findViewById(R.id.description);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public EventAdapter(List<Event> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_list_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new EventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        Event event = mDataset.get(position);
        holder.name.setText(event.getName());
        holder.start_time.setText((event.getStartTimeAsDate()));
        holder.description.setText(event.getDescription());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
