package sstorage.mobile.senai.com.sstorage.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import sstorage.mobile.senai.com.sstorage.R;
import sstorage.mobile.senai.com.sstorage.model.Patrimony;
import sstorage.mobile.senai.com.sstorage.view.holder.PatrimonyViewHolder;

public class PatrimonyAdapter extends RecyclerView.Adapter<PatrimonyViewHolder> {

    private List<Patrimony> patrimonies;
    private Context context;

    public PatrimonyAdapter(List<Patrimony> patrimonies, Context context) {
        this.patrimonies = patrimonies;
        this.context = context;
    }


    @NonNull
    @Override
    public PatrimonyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rvitem_simpleitem, parent, false);
        PatrimonyViewHolder pvh = new PatrimonyViewHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PatrimonyViewHolder holder, int position) {
        PatrimonyViewHolder pvh = (PatrimonyViewHolder) holder;
        Patrimony patrimony = patrimonies.get(position);
        pvh.fill(patrimony);
    }

    @Override
    public int getItemCount() {
        return patrimonies.size();
    }

    public List<Patrimony> getPatrimonies() {
        return patrimonies;
    }

    public Context getContext() {
        return context;
    }
}
