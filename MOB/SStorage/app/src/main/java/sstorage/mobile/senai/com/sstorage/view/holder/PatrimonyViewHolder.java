package sstorage.mobile.senai.com.sstorage.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import sstorage.mobile.senai.com.sstorage.R;
import sstorage.mobile.senai.com.sstorage.model.Patrimony;
import sstorage.mobile.senai.com.sstorage.view.adapter.PatrimonyAdapter;

public class PatrimonyViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvItemName;

    public PatrimonyViewHolder(View itemView) {
        super(itemView);
        //
        tvItemName = itemView.findViewById(R.id.tvItemName);
    }

    public void fill(Patrimony patrimony) {
        tvItemName.setText(patrimony.getName());
    }

}
