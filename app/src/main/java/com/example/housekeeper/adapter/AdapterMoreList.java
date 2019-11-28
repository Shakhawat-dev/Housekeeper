package com.example.housekeeper.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.housekeeper.MainActivity;
import com.example.housekeeper.R;
import com.example.housekeeper.model.ModelMoreList;

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

                SharedPreferences pref;
                pref = ctx.getSharedPreferences(LOGIN_KEY, 0);

                SharedPreferences.Editor editor = pref.edit();
                editor.remove("Key");
                editor.commit();

                Toast.makeText(v.getContext(), "Sign Out Tapped", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(ctx, MainActivity.class);
                ctx.startActivity(intent);

            }


        }
    }
}
