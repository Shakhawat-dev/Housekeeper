package com.example.housekeeper.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.housekeeper.R;
import com.example.housekeeper.activity.TaskDetailsActivity;
import com.example.housekeeper.model.ModelTasks;

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

    }

    @Override
    public int getItemCount() {
        return modelTasksList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView title;
        public TextView status;
        public TextView room;
        public TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            title = itemView.findViewById(R.id.title_tv);
            status = itemView.findViewById(R.id.status_tv);
            room = itemView.findViewById(R.id.room_no_tv);
            date = itemView.findViewById(R.id.date_tv);

        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();

            ModelTasks tasksList = modelTasksList.get(position);

            Intent intent = new Intent(ctx, TaskDetailsActivity.class);

            ctx.startActivity(intent);
            //  ((Activity)ctx).finish();


        }
    }
}
