package srent.senai.com.srent.adapters;

import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import srent.senai.com.srent.R;
import srent.senai.com.srent.models.Bus;

public class BusViewAdapter extends RecyclerView.Adapter<BusViewAdapter.VehicleViewHolder> {

    private View.OnClickListener onClickListener;
    public List<Bus> vehicles;
    private Boolean useFullDescription;

    public BusViewAdapter(View.OnClickListener onClickListener, List<Bus> vehicles, Boolean useFullDescription) {
        this.onClickListener = onClickListener;
        this.vehicles = vehicles;
        this.useFullDescription = useFullDescription;
    }

    public static class VehicleViewHolder extends RecyclerView.ViewHolder {

        public final View parentLayout;
        public final ImageView imageView;
        public final TextView tvName;
        public final TextView tvDescription;

        public VehicleViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.vehicle_card_ivMain);
            tvName = itemView.findViewById(R.id.vehicle_card_tvName);
            tvDescription = itemView.findViewById(R.id.vehicle_card_tvPrice);
            parentLayout = itemView.findViewById(R.id.vehicle_card_parent);
        }

        public void fill(Bus v) {
            imageView.setImageBitmap(v.getBitmap());
            tvName.setText(v.getName());
            tvDescription.setText(String.format("US$ %.02f", v.getPrice()));
        }
    }

    @Override
    public VehicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Creating the View
        final View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(useFullDescription ? R.layout.vehicle_desc_card_layout : R.layout.vehicle_card_layout, parent, false);
        v.setOnClickListener(onClickListener);
        // Passing it to the View Holder
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

    public List<Bus> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Bus> vehicles) {
        this.vehicles = vehicles;
    }
}
