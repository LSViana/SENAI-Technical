package srent.senai.com.srent.adapters;

import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

import srent.senai.com.srent.R;
import srent.senai.com.srent.data.VehicleRequestDAO;
import srent.senai.com.srent.models.Bus;
import srent.senai.com.srent.models.VehicleRequest;

public class VehicleRequestViewAdapter extends RecyclerView.Adapter<VehicleRequestViewAdapter.VehicleRequestViewHolder> {

    private static SimpleDateFormat sdf = new SimpleDateFormat(VehicleRequestDAO.DATE_PATTERN);

    private View.OnClickListener onClickListener;
    public List<VehicleRequest> vehicleRequests;

    public VehicleRequestViewAdapter(View.OnClickListener onClickListener, List<VehicleRequest> vehicleRequest) {
        this.onClickListener = onClickListener;
        this.vehicleRequests = vehicleRequest;
    }

    public static class VehicleRequestViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView tvName;
        private final TextView tvPrice;
        private final TextView tvStartDate;
        private final TextView tvEndDate;

        public VehicleRequestViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.vr_card_ivMain);
            tvName = itemView.findViewById(R.id.vr_card_tvName);
            tvPrice = itemView.findViewById(R.id.vr_card_tvPrice);
            tvStartDate = itemView.findViewById(R.id.vr_card_tvStartDate);
            tvEndDate = itemView.findViewById(R.id.vr_card_tvEndDate);
        }

        public void fill(VehicleRequest vr) {
            imageView.setImageBitmap(BitmapFactory.decodeResource(itemView.getResources(), vr.getVehicle().getImageResId()));
            tvName.setText(vr.getVehicle().getImageResId());
            tvPrice.setText(String.format("US$ %.02f", vr.calculatePrice()));
            tvStartDate.setText(sdf.format(vr.getStartDate()));
            tvEndDate.setText(sdf.format(vr.getEndDate()));
        }
    }

    @Override
    public VehicleRequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Creating the View
        final View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vehiclerequest_card_layout, parent, false);
        v.setOnClickListener(onClickListener);
        // Passing it to the View Holder
        VehicleRequestViewHolder vh = new VehicleRequestViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(VehicleRequestViewHolder holder, int position) {
        VehicleRequestViewHolder vh = (VehicleRequestViewHolder) holder;
        vh.fill(vehicleRequests.get(position));
    }

    @Override
    public int getItemCount() {
        return vehicleRequests.size();
    }

    public List<VehicleRequest> getVehicleRequests() {
        return vehicleRequests;
    }

    public void setVehicleRequests(List<VehicleRequest> vehicleRequests) {
        this.vehicleRequests = vehicleRequests;
    }
}
