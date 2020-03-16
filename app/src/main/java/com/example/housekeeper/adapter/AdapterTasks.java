package com.example.housekeeper.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.housekeeper.R;
import com.example.housekeeper.activity.TaskDetailsActivity;
import com.example.housekeeper.model.ModelTasks;
import com.example.housekeeper.utils.Data;

import java.util.List;

public class AdapterTasks extends RecyclerView.Adapter<AdapterTasks.ViewHolder> {

    private Context ctx;
    private List<ModelTasks> modelTasksList;

    public AdapterTasks (Context context, List tasklist) {
        this.ctx = context;
        this.modelTasksList = tasklist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_tasks, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ModelTasks tasks = modelTasksList.get(position);

        holder.title.setText(tasks.getTitle());
        holder.status.setText(tasks.getStatus());
        holder.room.setText(tasks.getRoom());
        holder.date.setText(tasks.getDate());
        holder.priorityRating.setRating(tasks.getPriorityRating());

        if (tasks.getStatus().equals("Completed")) {
            holder.mTaskImage.setImageResource(R.drawable.complete_icon);
            holder.mTaskImage.setBackgroundResource(R.drawable.shape_round_green);
            holder.status.setBackgroundResource(R.drawable.text_back_green);
        } else if (tasks.getStatus().equals("InPrgress")) {
            holder.mTaskImage.setImageResource(R.drawable.progress_icon);
            holder.mTaskImage.setBackgroundResource(R.drawable.shape_round_yellow);
            holder.status.setBackgroundResource(R.drawable.text_back_yellow);
        } else {
            holder.mTaskImage.setImageResource(R.drawable.void_icon);
            holder.mTaskImage.setBackgroundResource(R.drawable.shape_round_red);
            holder.status.setBackgroundResource(R.drawable.text_back_red);
        }

    }

    @Override
    public int getItemCount() {
        return modelTasksList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView mTaskImage;
        public TextView title;
        public TextView status;
        public TextView room;
        public TextView date;
        public RatingBar priorityRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            mTaskImage = itemView.findViewById(R.id.task_status_image);
            title = itemView.findViewById(R.id.title_tv);
            status = itemView.findViewById(R.id.status_tv);
            room = itemView.findViewById(R.id.room_no_tv);
            date = itemView.findViewById(R.id.date_tv);
            priorityRating = itemView.findViewById(R.id.priority_rating);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            ModelTasks task = modelTasksList.get(position);
            Data.task = task;
            Intent intent = new Intent(ctx, TaskDetailsActivity.class);

            ctx.startActivity(intent);
            //  ((Activity)ctx).finish();
        }
    }
}
