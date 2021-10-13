package com.example.pam_lab_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder>
{
    public interface OnClickListener
    {
        void OnItemClick(int position);
    }

    ArrayList<Event> eventArrayList;
    Context context;
    OnClickListener item_listener;

    public EventAdapter(ArrayList<Event> eventArrayList, Context context, OnClickListener i) {
        this.eventArrayList = eventArrayList;
        this.context = context;
        this.item_listener = i;
    }

    @NonNull
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.ViewHolder holder, int position) {
        Event modal = eventArrayList.get(position);
        holder.startTime.setText(modal.getTimeStart());
        holder.endTime.setText(modal.getTimeEnd());
        holder.title.setText(modal.getTitle());
    }

    @Override
    public int getItemCount() {
        return eventArrayList == null ? 0 : eventArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView startTime, endTime, title;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            startTime = itemView.findViewById(R.id.idStartTime);
            endTime = itemView.findViewById(R.id.idEndTime);
            title = itemView.findViewById(R.id.idTitle);
        }

        @Override
        public void onClick(View view) {
            item_listener.OnItemClick(getAdapterPosition());
        }
    }
}

