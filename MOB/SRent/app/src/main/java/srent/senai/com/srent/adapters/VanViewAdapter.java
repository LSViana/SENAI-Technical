package srent.senai.com.srent.adapters;

import android.graphics.BitmapFactory;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.LogPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import srent.senai.com.srent.R;
import srent.senai.com.srent.models.Van;
import srent.senai.com.srent.models.Vehicle;

public class VanViewAdapter extends RecyclerView.Adapter<VanViewAdapter.VehicleViewHolder> {

    private View.OnClickListener onClickListener;
    public List<Van> vehicles;
    private Boolean useFullDescription;

    public VanViewAdapter(View.OnClickListener onClickListener, List<Van> vehicles, Boolean useFullDescription) {
        this.onClickListener = onClickListener;
        this.vehicles = vehicles;
        this.useFullDescription = useFullDescription;
    }

    public static class VehicleViewHolder extends RecyclerView.ViewHolder {

        public final ImageView imageView;
        public final TextView tvName;
        public final TextView tvDescription;

        public VehicleViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.vehicle_card_ivMain);
            tvName = itemView.findViewById(R.id.vehicle_card_tvName);
            tvDescription = itemView.findViewById(R.id.vehicle_card_tvDescription);
        }

        public void fill(Van v) {
            imageView.setImageBitmap(BitmapFactory.decodeResource(itemView.getResources(), v.getImageResId()));
            tvName.setText(v.getName());
            tvDescription.setText(String.format("US$ %.02f", v.getPrice()));
        }
    }

    @Override
    public VehicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Creating the View
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(useFullDescription ? R.layout.vehicle_desc_card_layout : R.layout.vehicle_card_layout, parent, false);
        // Passing it to the View Holder
        v.setOnClickListener(onClickListener);
        VehicleViewHolder vh = new VehicleViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(VehicleViewHolder holder, int position) {
        VehicleViewHolder vh = (VehicleViewHolder) holder;
        vh.fill(vehicles.get(position));
    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }

    public List<Van> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Van> vehicles) {
        this.vehicles = vehicles;
    }
}
