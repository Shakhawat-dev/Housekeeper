package com.example.housekeeper.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.housekeeper.R;
import com.example.housekeeper.activity.ChecklistActivity;
import com.example.housekeeper.custom.DefectDialog;
import com.example.housekeeper.model.ModelChecklist;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AdapterChecklist extends RecyclerView.Adapter<AdapterChecklist.ViewHolder> {
    private Context ctx;
    private List<ModelChecklist> modelChecklists;
    private ModelChecklist checklist;

    public AdapterChecklist(Context context, List checkList) {
        this.ctx = context;
        this.modelChecklists = checkList;
    }

    @NonNull
    @Override
    public AdapterChecklist.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_check_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterChecklist.ViewHolder holder, final int position) {
        checklist = modelChecklists.get(position);
        holder.checkListName.setText(checklist.getCaption());
        holder.checkListName.setChecked(checklist.getTaskStatus());
    }

    @Override
    public int getItemCount() {
        return modelChecklists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CheckBox checkListName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            checkListName = itemView.findViewById(R.id.checklist_item_ctv);
            checkListName.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            modelChecklists.get(position).setTaskStatus(checkListName.isChecked());
            Log.d(TAG, "onClick: " + modelChecklists.get(position).getCaption() + "is " + modelChecklists.get(position).getTaskStatus());

            DefectDialog defectDialog = new DefectDialog();
            defectDialog.show(((ChecklistActivity) ctx).getSupportFragmentManager(), "Defect Dialog");
        }
    }
}
