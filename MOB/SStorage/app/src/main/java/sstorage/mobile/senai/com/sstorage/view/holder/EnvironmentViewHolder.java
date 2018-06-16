package sstorage.mobile.senai.com.sstorage.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import sstorage.mobile.senai.com.sstorage.R;
import sstorage.mobile.senai.com.sstorage.model.Environment;
import sstorage.mobile.senai.com.sstorage.view.adapter.EnvironmentAdapter;

public class EnvironmentViewHolder extends RecyclerView.ViewHolder {
    private final TextView tvItemName;
    private EnvironmentAdapter environmentAdapter;
    private Long id;

    public EnvironmentViewHolder(View itemView, EnvironmentAdapter environmentAdapter) {
        super(itemView);
        this.environmentAdapter = environmentAdapter;
        // Getting components
        tvItemName = itemView.findViewById(R.id.tvItemName);
    }

    public void fill(Environment environment) {
        id = environment.getId();
        tvItemName.setText(environment.getName());
    }    
}
