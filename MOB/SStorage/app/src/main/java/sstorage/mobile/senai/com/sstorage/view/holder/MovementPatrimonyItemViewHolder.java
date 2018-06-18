package sstorage.mobile.senai.com.sstorage.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import sstorage.mobile.senai.com.sstorage.R;
import sstorage.mobile.senai.com.sstorage.model.Environment;
import sstorage.mobile.senai.com.sstorage.model.Movement;

public class MovementPatrimonyItemViewHolder extends RecyclerView.ViewHolder {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final TextView tvMovFrom;
    private final TextView tvMovTo;
    private final TextView tvMovDate;

    public MovementPatrimonyItemViewHolder(View itemView) {
        super(itemView);
        // UI
        tvMovFrom = itemView.findViewById(R.id.tvMovFrom);
        tvMovTo = itemView.findViewById(R.id.tvMovTo);
        tvMovDate = itemView.findViewById(R.id.tvMovDate);

    }

    public void fill(Movement movement, Environment origin, Environment destiny) {
        tvMovDate.setText(simpleDateFormat.format(movement.getDateTime()));
        tvMovFrom.setText(origin.getName());
        tvMovTo.setText(destiny.getName());
    }

}
