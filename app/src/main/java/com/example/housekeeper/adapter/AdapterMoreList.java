package com.example.housekeeper.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.housekeeper.activity.GetNumberActivity;
import com.example.housekeeper.activity.MainActivity;
import com.example.housekeeper.R;
import com.example.housekeeper.model.ModelMoreList;
import com.example.housekeeper.sharedPrefManager.SharedPrefManager;

import java.util.List;

public class AdapterMoreList extends RecyclerView.Adapter<AdapterMoreList.ViewHolder> {

    private Context ctx;
    private List<ModelMoreList> modelMoreList;

    private static final String LOGIN_KEY = "loginKey";

    public AdapterMoreList(Context ctx, List<ModelMoreList> modelMoreList) {
        this.ctx = ctx;
        this.modelMoreList = modelMoreList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_more_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ModelMoreList moreList = modelMoreList.get(position);

        holder.name.setText(moreList.getName());

    }

    @Override
    public int getItemCount() {
        return modelMoreList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            name = (TextView) itemView.findViewById(R.id.list_title);

        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();

            ModelMoreList moreList = modelMoreList.get(position);

            if (moreList.getName().equals("Sign Out")) {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                SharedPrefManager.getInstance(ctx).logout();

                                Intent intent = new Intent(ctx, GetNumberActivity.class);
                                ctx.startActivity(intent);
                                ((Activity)ctx).finish();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();

                Toast.makeText(v.getContext(), "Sign Out Tapped", Toast.LENGTH_LONG).show();



            }


        }
    }
}
