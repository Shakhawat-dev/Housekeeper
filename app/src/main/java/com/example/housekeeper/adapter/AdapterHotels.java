package com.example.housekeeper.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.housekeeper.DashboardActivity;
import com.example.housekeeper.R;
import com.example.housekeeper.TaskListActivity;
import com.example.housekeeper.model.ModelHotels;

import java.util.List;

public class AdapterHotels extends RecyclerView.Adapter<AdapterHotels.ViewHolder> {

    private Context ctx;
    private List<ModelHotels> modelHotelsList;

    public AdapterHotels (Context context, List hotelList) {

        this.ctx = context;
        this.modelHotelsList = hotelList;

    }

    @NonNull
    @Override
    public AdapterHotels.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_hotels, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHotels.ViewHolder holder, int position) {

        ModelHotels hotels = modelHotelsList.get(position);

        holder.name.setText(hotels.getName());
        holder.address.setText(hotels.getAdsress());

    }

    @Override
    public int getItemCount() {
        return modelHotelsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name;
        public TextView address;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            name = (TextView) itemView.findViewById(R.id.hotel_name_tv);
            address = (TextView) itemView.findViewById(R.id.hotel_address_tv);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();

            ModelHotels hotelsList = modelHotelsList.get(position);

            Intent intent = new Intent(ctx, DashboardActivity.class);

            intent.putExtra("Name", hotelsList.getName());
            intent.putExtra("Address", hotelsList.getAdsress());

            ctx.startActivity(intent);


        }
    }
}
