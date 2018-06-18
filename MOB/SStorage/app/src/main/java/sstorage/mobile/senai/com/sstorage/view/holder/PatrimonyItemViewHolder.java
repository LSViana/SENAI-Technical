package sstorage.mobile.senai.com.sstorage.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import sstorage.mobile.senai.com.sstorage.R;
import sstorage.mobile.senai.com.sstorage.model.PatrimonyItem;
import sstorage.mobile.senai.com.sstorage.model.PatrimonyItemState;

public class PatrimonyItemViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvItemName;

    public PatrimonyItemViewHolder(View itemView) {
        super(itemView);
        //
        tvItemName = itemView.findViewById(R.id.tvItemName);
    }

    public void fill(PatrimonyItem patrimonyItem) {
        PatrimonyItemState state = PatrimonyItemState.values()[patrimonyItem.getState()];
        String stateStr = state.toString().toLowerCase();
        if(stateStr.length() > 1)
            stateStr = Character.toUpperCase(stateStr.charAt(0)) + stateStr.substring(1);

        tvItemName.setText(String.format("%d - %s – %s", patrimonyItem.getId(), stateStr, patrimonyItem.getEnvironment().getName()));
    }

}
