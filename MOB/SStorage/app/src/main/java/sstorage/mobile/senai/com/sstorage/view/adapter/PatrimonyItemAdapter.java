package sstorage.mobile.senai.com.sstorage.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import sstorage.mobile.senai.com.sstorage.R;
import sstorage.mobile.senai.com.sstorage.model.PatrimonyItem;
import sstorage.mobile.senai.com.sstorage.view.holder.PatrimonyItemViewHolder;

public class PatrimonyItemAdapter extends RecyclerView.Adapter<PatrimonyItemViewHolder> {

    private List<PatrimonyItem> patrimonyItems;
    private Context context;

    public PatrimonyItemAdapter(List<PatrimonyItem> patrimonyItems, Context context) {
        this.patrimonyItems = patrimonyItems;
        this.context = context;
    }

    @NonNull
    @Override
    public PatrimonyItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rvitem_simpleitem_font_small, parent, false);
        PatrimonyItemViewHolder patrimonyItemViewHolder = new PatrimonyItemViewHolder(view);
        return patrimonyItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PatrimonyItemViewHolder holder, int position) {
        PatrimonyItem patrimonyItem = patrimonyItems.get(position);
        holder.fill(patrimonyItem);
    }

    @Override
    public int getItemCount() {
        return patrimonyItems.size();
    }
}
